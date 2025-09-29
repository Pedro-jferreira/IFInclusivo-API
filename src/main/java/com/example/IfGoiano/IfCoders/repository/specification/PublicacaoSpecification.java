package com.example.IfGoiano.IfCoders.repository.specification;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class PublicacaoSpecification {

    /**
     * Retorna apenas publicações que são "pais" (não são respostas).
     */
    public static Specification<PublicacaoEntity> isParent() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get("parent"));
    }

    /**
     * Filtra publicações que contenham QUALQUER uma das categorias fornecidas.
     */
    public static Specification<PublicacaoEntity> hasCategorias(Set<Categorias> categorias) {
        return (root, query, criteriaBuilder) -> {
            // Faz o JOIN com a tabela de categorias e aplica o filtro
            Join<PublicacaoEntity, Categorias> join = root.join("categorias");
            // Garante que não haja resultados duplicados
            query.distinct(true);
            return join.in(categorias);
        };
    }
}