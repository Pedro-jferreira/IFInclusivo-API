package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.AlunoNapneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoNapneRepository extends JpaRepository<AlunoNapneEntity,Long> {

}
