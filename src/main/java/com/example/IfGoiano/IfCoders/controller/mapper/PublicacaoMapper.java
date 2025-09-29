package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoDetalhadaDTO;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class}) // `uses` delega a conversão de usuário para outro mapper
public interface PublicacaoMapper {

    /**
     * Converte um DTO de requisição para uma Entidade.
     * Note que o 'parentId' não é mapeado aqui; o serviço irá buscar a entidade pai e associá-la.
     */
    PublicacaoEntity toEntity(PublicacaoRequestDTO dto);

    /**
     * Converte uma Entidade para um DTO Detalhado.
     * Mapeia campos adicionais como 'texto', 'categorias' e o ID da resposta escolhida.
     */
    @Mapping(target = "totalLikes", expression = "java(entity.getLikeBy() != null ? entity.getLikeBy().size() : 0)")
    @Mapping(target = "totalRespostas", expression = "java(entity.getRespostas() != null ? entity.getRespostas().size() : 0)")
    @Mapping(target = "respostaEscolhidaId", source = "respostaEscolhida.id") // Mapeia o ID do objeto aninhado
    PublicacaoDetalhadaDTO toDetalhadaDTO(PublicacaoEntity entity, @Context UsuarioEntity usuarioLogado);

    /**
     * O MapStruct gera automaticamente a implementação para converter uma lista de entidades
     * para uma lista de DTOs Detalhados, reutilizando o método acima.
     * Você fez um ajuste no DTO para que respostas também sejam detalhadas.
     */
    List<PublicacaoDetalhadaDTO> toDetalhadaDTOList(List<PublicacaoEntity> entities, @Context UsuarioEntity usuarioLogado);



    /**
     * Método auxiliar para calcular o campo 'curtidoPeloUsuario' para o DTO Detalhado.
     */
    @AfterMapping
    default void setCurtidoPeloUsuario(PublicacaoEntity entity, @MappingTarget PublicacaoDetalhadaDTO dto, @Context UsuarioEntity usuarioLogado) {
        if (usuarioLogado == null || entity.getLikeBy() == null) {
            dto.setCurtidoPeloUsuario(false);
            return;
        }
        boolean curtido = entity.getLikeBy().stream()
                .anyMatch(usuario -> usuario.getId().equals(usuarioLogado.getId()));
        dto.setCurtidoPeloUsuario(curtido);
    }
}