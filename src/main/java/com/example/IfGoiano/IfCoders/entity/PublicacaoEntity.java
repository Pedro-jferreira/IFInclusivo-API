package com.example.IfGoiano.IfCoders.entity;

import lombok.Data;

import jakarta.persistence.*;
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
    private LocalDateTime dataCriacao = LocalDateTime.now();


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private TopicoEntity topico;

    @OneToMany(mappedBy = "publicacao",cascade = CascadeType.ALL)
    private List<ComentarioEntity> comentarios = new ArrayList<>();

    @ManyToMany(mappedBy = "likes")
    private List<UsuarioEntity> likeBy = new ArrayList<>();

}
