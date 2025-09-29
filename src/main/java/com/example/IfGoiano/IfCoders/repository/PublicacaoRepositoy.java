package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PublicacaoRepositoy extends JpaRepository<PublicacaoEntity, Long>,
        JpaSpecificationExecutor<PublicacaoEntity> {
}