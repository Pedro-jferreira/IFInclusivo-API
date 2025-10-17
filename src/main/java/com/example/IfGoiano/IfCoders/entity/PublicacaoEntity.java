package com.example.IfGoiano.IfCoders.entity;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.StatusPublicacao;
import com.example.IfGoiano.IfCoders.entity.Enums.TipoPublicacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "publicacoes")
@Data
@EqualsAndHashCode(of = "id")
public class PublicacaoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String texto;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    @ElementCollection(targetClass = Categorias.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "publicacao_categorias",
            joinColumns = @JoinColumn(name = "publicacao_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private Set<Categorias> categorias = new HashSet<>();

    /** Tipo da publicação: dúvida, dica, mentoria, etc. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPublicacao tipo;

    /** Status só é usado quando for uma dúvida */
    @Enumerated(EnumType.STRING)
    private StatusPublicacao status;

    /** Comentário marcado como resposta da dúvida */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comentario_escolhido_id")
    private ComentarioEntity comentarioEscolhido;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToMany(mappedBy = "likes")
    private Set<UsuarioEntity> likeBy = new HashSet<>();

    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("dataCriacao ASC")
    private List<ComentarioEntity> comentarios = new ArrayList<>();
}