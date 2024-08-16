package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.model.Like;
import com.example.IfGoiano.IfCoders.model.PK.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, LikeId> {
}
