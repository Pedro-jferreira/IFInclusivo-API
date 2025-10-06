package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoCompletaDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoDetalhadaDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.PublicacaoMapper;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Ordenacao;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.PublicacaoRepositoy;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import com.example.IfGoiano.IfCoders.service.PublicacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor // Usando injeção de dependência via construtor com Lombok
public class PublicacaoServiceImpl implements PublicacaoService {

    private final PublicacaoRepositoy publicacaoRepository;
    private final UsuarioRepository usuarioRepository; // Assumindo que você tenha um serviço para buscar usuários
    private final PublicacaoMapper publicacaoMapper;

    @Override
    @Transactional
    public PublicacaoDetalhadaDTO save(PublicacaoRequestDTO dto, String username) {
        UsuarioEntity usuarioAutenticado = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o username: " + username));

        if (dto.getParentId() == null && !StringUtils.hasText(dto.getTitulo())) {
            throw new IllegalArgumentException("O título é obrigatório para uma nova publicação.");
        }

        PublicacaoEntity novaPublicacao = publicacaoMapper.toEntity(dto);
        novaPublicacao.setUsuario(usuarioAutenticado);

        if (dto.getParentId() != null) {
            PublicacaoEntity parent = publicacaoRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Publicação pai não encontrada com o ID: " + dto.getParentId()));
            novaPublicacao.setParent(parent);

        }

        PublicacaoEntity publicacaoSalva = publicacaoRepository.save(novaPublicacao);
        return publicacaoMapper.toDetalhadaDTO(publicacaoSalva,usuarioAutenticado);
    }

    @Override
    public Page<PublicacaoDetalhadaDTO> findAll(
            Set<Categorias> categorias,
            Ordenacao ordenarPor,
            Pageable pageable,
            String username) {

        final UsuarioEntity usuarioLogado = (username != null)
                ? usuarioRepository.findByLogin(username).orElse(null)
                : null;

        Specification<PublicacaoEntity> spec = Specification.where(null);
        spec = spec.and((root, query, cb) -> cb.isNull(root.get("parent")));

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

        Page<PublicacaoEntity> publicacoesPage = publicacaoRepository.findAll(spec, pageable);

        return getPublicacaoDetalhadaDTOS(ordenarPor, pageable, usuarioLogado, publicacoesPage);
    }

    private Page<PublicacaoDetalhadaDTO> getPublicacaoDetalhadaDTOS(Ordenacao ordenarPor, Pageable pageable, UsuarioEntity usuarioLogado, Page<PublicacaoEntity> publicacoesPage) {
        if (ordenarPor == Ordenacao.RELEVANCIA) {
            List<PublicacaoEntity> sorted = publicacoesPage.stream()
                    .sorted(Comparator.comparingInt(this::calcularRelevancia).reversed())
                    .toList();

            return new PageImpl<>(
                    sorted.stream()
                            .map(p -> publicacaoMapper.toDetalhadaDTO(p, usuarioLogado))
                            .toList(),
                    pageable,
                    publicacoesPage.getTotalElements()
            );
        }

        return publicacoesPage.map(p -> publicacaoMapper.toDetalhadaDTO(p, usuarioLogado));
    }

    private int calcularRelevancia(PublicacaoEntity p) {
        int likes = p.getLikeBy() != null ? p.getLikeBy().size() : 0;
        int comentarios = p.getRespostas() != null ? p.getRespostas().size() : 0;
        boolean resolvido = p.getRespostaEscolhida() != null;

        return (likes * 2) + (comentarios * 1) + (!resolvido ? 3 : 0);
    }


    @Override
    @Transactional()
    public PublicacaoCompletaDTO findById(Long id, String username) {
        final UsuarioEntity usuarioLogado = (username != null)
                ? usuarioRepository.findByLogin(username).orElse(null)
                : null;

        PublicacaoEntity entity = publicacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicação não encontrada com o ID: " + id));

        PublicacaoDetalhadaDTO atual = publicacaoMapper.toDetalhadaDTO(entity, usuarioLogado);

        List<PublicacaoDetalhadaDTO> pais = new ArrayList<>();
        PublicacaoEntity pai = entity.getParent();
        while (pai != null) {
            pais.add(publicacaoMapper.toDetalhadaDTO(pai, usuarioLogado));
            pai = pai.getParent();
        }

        PublicacaoCompletaDTO dto = new PublicacaoCompletaDTO();
        dto.setAtual(atual);
        dto.setPais(pais);
        return dto;
    }

    @Override
    @Transactional
    public PublicacaoDetalhadaDTO update(Long id, PublicacaoRequestDTO publicacaoDetails, String username) {
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
        if (!publicacao.getParent().getId().equals(publicacaoDetails.getParentId())) {
            PublicacaoEntity parent = publicacaoRepository.findById(publicacaoDetails.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Publicação pai não encontrada com o ID: " + publicacaoDetails.getParentId()));
            publicacao.setParent(parent);
        }
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

    @Transactional()
    @Override
    public Page<PublicacaoDetalhadaDTO> findFilhosById(
            Long publicacaoId,
            Ordenacao ordenarPor,
            Pageable pageable,
            String username) {

        final UsuarioEntity usuarioLogado = (username != null)
                ? usuarioRepository.findByLogin(username).orElse(null)
                : null;

        PublicacaoEntity parent = publicacaoRepository.findById(publicacaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicação não encontrada com ID: " + publicacaoId));

        Page<PublicacaoEntity> respostasPage = publicacaoRepository.findAll(
                (root, query, cb) -> cb.equal(root.get("parent"), parent),
                pageable
        );

        return getPublicacaoDetalhadaDTOS(ordenarPor, pageable, usuarioLogado, respostasPage);
    }

}