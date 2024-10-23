package com.example.IfGoiano.IfCoders.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topics")
@Data
public class TopicoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professor;
    @NotNull
    private String tema;
    @NotNull
    private String descripcion;
    @NotNull
    private String categoria;
    @OneToMany(mappedBy = "topico")
    private List<PublicacaoEntity> publicacoes = new ArrayList<>();

}
