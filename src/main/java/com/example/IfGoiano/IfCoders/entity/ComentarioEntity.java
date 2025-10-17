package com.example.IfGoiano.IfCoders.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "comentarios")
@Data
@EqualsAndHashCode(of = "id")
public class ComentarioEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String texto;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    /** Comentário pai (para respostas em árvore) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ComentarioEntity parent;

    /** Respostas deste comentário */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("dataCriacao ASC")
    private List<ComentarioEntity> respostas = new ArrayList<>();

    /** Publicação à qual esse comentário pertence */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacao_id", nullable = false)
    private PublicacaoEntity publicacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_mencionado_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UsuarioEntity usuarioMencionado;

    @ManyToMany(mappedBy = "likesComentarios")
    private Set<UsuarioEntity> likeBy = new HashSet<>();
}
