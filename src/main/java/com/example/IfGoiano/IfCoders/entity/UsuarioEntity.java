package com.example.IfGoiano.IfCoders.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@Data
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String nome;
    @NotNull    @Column(nullable = false)
    private String login;
    @NotNull    @Column(nullable = false)
    private String senha;
    @NotNull    @Column(nullable = false)
    private Long matricula;
    private String biografia;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;


    @OneToOne
    private ConfigAcessibilidadeEntity configAcessibilidadeEntity;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ComentarioEntity> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<PublicacaoEntity> publicacaoEntities = new ArrayList<>();

    @ManyToMany(mappedBy = "sugeriu", cascade = {CascadeType.MERGE})
    private List<LibrasEntity> librasEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEnvia", cascade = CascadeType.ALL)
    private List<MessageEntity> userEnvia = new ArrayList<>();

    @OneToMany(mappedBy = "userRecebe", cascade = CascadeType.ALL)
    private List<MessageEntity> userRecebe = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "usuario_likes_publicacao",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns =@JoinColumn(name = "publicacao_id") )
    private List<PublicacaoEntity> likes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "usuario_useful_comentario",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "comentario_id")
    )
    private List<ComentarioEntity> useful = new ArrayList<>();


}