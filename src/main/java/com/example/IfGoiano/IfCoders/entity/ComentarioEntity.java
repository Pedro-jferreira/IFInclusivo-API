package com.example.IfGoiano.IfCoders.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Comments")
@Data
public class ComentarioEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "publicacao_id", nullable = false)
    private PublicacaoEntity publicacao;

    @NotNull
    private String content;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "comentario_id")
    private ComentarioEntity comentarioPai;

    @OneToMany(mappedBy = "comentarioPai", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioEntity> comentariosFilhos = new ArrayList<>();

    @ManyToMany(mappedBy = "useful")
    private List<UsuarioEntity> usefulBy  = new ArrayList<>();


}