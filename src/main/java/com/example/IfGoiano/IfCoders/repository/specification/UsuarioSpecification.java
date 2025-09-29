package com.example.IfGoiano.IfCoders.repository.specification;

import com.example.IfGoiano.IfCoders.entity.Enums.Role;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class UsuarioSpecification {

    public static Specification<UsuarioEntity> nameContains(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<UsuarioEntity> hasRole(Role role) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join("roles", JoinType.INNER).get("nome"), role);
        };
    }
}