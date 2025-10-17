package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoResponseDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.PublicacaoMapper;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Ordenacao;
import com.example.IfGoiano.IfCoders.entity.Enums.StatusPublicacao;
import com.example.IfGoiano.IfCoders.entity.Enums.TipoPublicacao;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.PublicacaoRepositoy;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import com.example.IfGoiano.IfCoders.service.PublicacaoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PublicacaoServiceImpl implements PublicacaoService {
    private final PublicacaoRepositoy publicacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PublicacaoMapper publicacaoMapper;
    @Override
    @Transactional
    public PublicacaoResponseDTO save(PublicacaoRequestDTO dto, String username) {
        UsuarioEntity usuarioAutenticado = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o username: " + username));

        if (!StringUtils.hasText(dto.getTitulo())) {
            throw new IllegalArgumentException("O título é obrigatório para uma nova publicação.");
        }

        PublicacaoEntity novaPublicacao = publicacaoMapper.toEntity(dto);
        novaPublicacao.setUsuario(usuarioAutenticado);

        PublicacaoEntity publicacaoSalva = publicacaoRepository.save(novaPublicacao);
        return publicacaoMapper.toDetalhadaDTO(publicacaoSalva,usuarioAutenticado);
    }
    @Override
    public Page<PublicacaoResponseDTO> findAll(
            Set<Categorias> categorias,
            Ordenacao ordenarPor,
            Pageable pageable,
            String username) {

        final UsuarioEntity usuarioLogado = (username != null)
                ? usuarioRepository.findByLogin(username).orElse(null)
                : null;

        Specification<PublicacaoEntity> spec = Specification.where(null);

        if (categorias != null && !categorias.isEmpty()) {
            spec = spec.and((root, query, cb) -> root.join("categorias").in(categorias));
        }

        if (ordenarPor == Ordenacao.MAIS_RECENTE) {
            pageable = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(Sort.Direction.DESC, "dataCriacao")
            );
        }

        Page<PublicacaoEntity> page = publicacaoRepository.findAll(spec, pageable);

        if (ordenarPor == Ordenacao.RELEVANCIA) {
            List<PublicacaoEntity> ordenadas = page.getContent().stream()
                    .sorted(Comparator.comparingDouble(this::calcularRelevancia).reversed())
                    .toList();

            Page<PublicacaoEntity> ordenadasPage = new PageImpl<>(
                    ordenadas,
                    pageable,
                    page.getTotalElements()
            );

            return ordenadasPage.map(entity -> publicacaoMapper.toDetalhadaDTO(entity, usuarioLogado));
        }

        return page.map(entity -> publicacaoMapper.toDetalhadaDTO(entity, usuarioLogado));
    }

    private double calcularRelevancia(PublicacaoEntity p) {
        double score = 0.0;
        if (p.getTipo() == TipoPublicacao.DUVIDA && p.getStatus() == StatusPublicacao.PENDENTE) {
            score += 50;
        }
        int likes = (p.getLikeBy() != null) ? p.getLikeBy().size() : 0;
        int comentarios = (p.getComentarios() != null) ? p.getComentarios().size() : 0;
        score += (likes * 2) + (comentarios * 1.5);
        if (p.getDataCriacao() != null) {
            long horasDesdeCriacao = java.time.Duration.between(p.getDataCriacao(), java.time.LocalDateTime.now()).toHours();
            double fatorTempo = Math.max(0, 48 - horasDesdeCriacao); // 48h = bônus máximo
            score += fatorTempo * 0.5;
        }
        return score;
    }


    @Override
    @Transactional()
    public PublicacaoResponseDTO findById(Long id, String username) {
        final UsuarioEntity usuarioLogado = (username != null)
                ? usuarioRepository.findByLogin(username).orElse(null)
                : null;

        PublicacaoEntity entity = publicacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicação não encontrada com o ID: " + id));

        return publicacaoMapper.toDetalhadaDTO(entity, usuarioLogado);
    }

    @Override
    @Transactional
    public PublicacaoResponseDTO update(Long id, PublicacaoRequestDTO publicacaoDetails, String username) {
        PublicacaoEntity publicacao = publicacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicação não encontrada com ID: " + id));

        UsuarioEntity usuarioLogado = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + username));

        if (!publicacao.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Você não tem permissão para editar esta publicação");
        }
        publicacao.setTitulo(publicacaoDetails.getTitulo());
        publicacao.setTexto(publicacaoDetails.getTexto());
        publicacao.setCategorias(publicacaoDetails.getCategorias());
        PublicacaoEntity saved = publicacaoRepository.save(publicacao);

        return publicacaoMapper.toDetalhadaDTO(saved,usuarioLogado);
    }


    @Transactional
    @Override
    public void delete(Long id, String username) {
        PublicacaoEntity publicacao = publicacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicação não encontrada com ID: " + id));

        UsuarioEntity usuarioLogado = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + username));

        if (!publicacao.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Você não tem permissão para excluir esta publicação");
        }

        publicacaoRepository.delete(publicacao);
    }

}