package com.example.IfGoiano.IfCoders.entity;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.StatusPublicacao;
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
    @Column(nullable = false)
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
    @Enumerated(EnumType.STRING) // salva como texto, não número
    @Column(name = "categoria", nullable = false)
    private Set<Categorias> categorias = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private StatusPublicacao status = StatusPublicacao.PENDENTE;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resposta_escolhida_id", referencedColumnName = "id")
    private PublicacaoEntity respostaEscolhida;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private PublicacaoEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("dataCriacao ASC")
    private List<PublicacaoEntity> respostas = new ArrayList<>();

    @ManyToMany(mappedBy = "likes")
    private Set<UsuarioEntity> likeBy = new HashSet<>();
}