package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioResponseDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ComentarioMapper;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.Enums.Ordenacao;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.repository.ComentarioRepository;
import com.example.IfGoiano.IfCoders.repository.PublicacaoRepositoy;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final PublicacaoRepositoy publicacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ComentarioMapper comentarioMapper;

    @Transactional
    public ComentarioResponseDTO adicionarComentario(Long publicacaoId, ComentarioRequestDTO dto, String username) {
        UsuarioEntity usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + username));

        PublicacaoEntity publicacao = publicacaoRepository.findById(publicacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Publicação não encontrada"));

        ComentarioEntity comentario = comentarioMapper.toEntity(dto);
        comentario.setUsuario(usuario);
        comentario.setPublicacao(publicacao);


        if (dto.getParentId() != null) {
            ComentarioEntity parent = comentarioRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new EntityNotFoundException("Comentário pai não encontrado"));
            comentario.setParent(parent);
        }
        if (dto.getUsuarioMencionadoId() != null) {
            usuarioRepository.findById(dto.getUsuarioMencionadoId()).ifPresent(comentario::setUsuarioMencionado);
        }

        ComentarioEntity salvo = comentarioRepository.save(comentario);
        return comentarioMapper.toResponseDTO(salvo, usuario);
    }

    @Transactional
    public ComentarioResponseDTO editarComentario(Long comentarioId, ComentarioRequestDTO dto, String username) {
        ComentarioEntity comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new EntityNotFoundException("Comentário não encontrado"));

        if (!comentario.getUsuario().getLogin().equals(username)) {
            throw new SecurityException("Você não pode editar um comentário de outro usuário.");
        }

        comentario.setTexto(dto.getTexto());
        ComentarioEntity atualizado = comentarioRepository.save(comentario);
        UsuarioEntity usuario = comentario.getUsuario();

        return comentarioMapper.toResponseDTO(atualizado, usuario);
    }


    @Transactional
    public void excluirComentario(Long comentarioId, String username) {
        Optional<ComentarioEntity> optionalComentario = comentarioRepository.findById(comentarioId);
        if (optionalComentario.isEmpty()) {
            return;
        }
        ComentarioEntity comentario = optionalComentario.get();
        if (!comentario.getUsuario().getLogin().equals(username)) {
            throw new SecurityException("Você não pode excluir o comentário de outro usuário.");
        }

        comentarioRepository.delete(comentario);
    }


    @Transactional
    public ComentarioResponseDTO toggleCurtir(Long comentarioId, String username) {
        ComentarioEntity comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new EntityNotFoundException("Comentário não encontrado"));

        UsuarioEntity usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + username));

        boolean jaCurtiu = comentario.getLikeBy().stream()
                .anyMatch(u -> u.getId().equals(usuario.getId()));

        if (jaCurtiu) {
            comentario.getLikeBy().remove(usuario);
            usuario.getLikesComentarios().remove(comentario);
        } else {
            comentario.getLikeBy().add(usuario);
            usuario.getLikesComentarios().add(comentario);
        }

        ComentarioEntity atualizado = comentarioRepository.save(comentario);
        return comentarioMapper.toResponseDTO(atualizado, usuario);
    }

    @Transactional(readOnly = true)
    public Page<ComentarioResponseDTO> listarComentariosPublicacao(Long publicacaoId, Ordenacao ordenacao, Pageable pageable, String userName) {
        final UsuarioEntity usuarioLogado = (userName != null)
                ? usuarioRepository.findByLogin(userName).orElse(null)
                : null;

        Page<ComentarioEntity> comentarios;

        if (ordenacao == Ordenacao.RELEVANCIA) {
            comentarios = comentarioRepository.findByPublicacaoIdAndParentIsNullOrderByRelevancia(publicacaoId, pageable);
        } else {
            comentarios = comentarioRepository.findByPublicacaoIdAndParentIsNullOrderByDataCriacaoDesc(publicacaoId, pageable);
        }

        return comentarios.map(entity -> comentarioMapper.toResponseDTO(entity, usuarioLogado));
    }
    @Transactional(readOnly = true)
    public Page<ComentarioResponseDTO> listarRespostasComentario(Long parentId, Pageable pageable, String userName) {
        final UsuarioEntity usuarioLogado = (userName != null)
                ? usuarioRepository.findByLogin(userName).orElse(null)
                : null;
        return comentarioRepository.findByParentIdOrderByDataCriacaoAsc(parentId, pageable)
                .map(entity -> comentarioMapper.toResponseDTO(entity, usuarioLogado));
    }
}
