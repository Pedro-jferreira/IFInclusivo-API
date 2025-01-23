package com.example.IfGoiano.IfCoders.entity;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


import java.io.Serializable;
import java.time.LocalDateTime;
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
    @NotNull
    private String titulo;
    @NotNull
    private String descricao;
    @NotNull
    private Categorias categoria;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professor;

    @ManyToMany
    @JoinTable(
            name = "topico_publicacao",
            joinColumns = @JoinColumn(name = "topico_id"),
            inverseJoinColumns =@JoinColumn(name = "publicacao_id") )
    private List<PublicacaoEntity> publicacaoEntities = new ArrayList<>();

}
