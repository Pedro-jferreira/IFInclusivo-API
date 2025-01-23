package com.example.IfGoiano.IfCoders.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Publications")
@Data
public class PublicacaoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String text;
    private String urlVideo;
    private String urlFoto;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "publicacao",cascade = CascadeType.ALL)
    private List<ComentarioEntity> comentarios = new ArrayList<>();

    @ManyToMany(mappedBy = "likes")
    private List<UsuarioEntity> likeBy = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "publicacaoEntities")
    private List<TopicoEntity> topicoEntities = new ArrayList<>();

}
