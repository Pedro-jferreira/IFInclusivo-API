package com.example.IfGoiano.IfCoders.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private Long matricula;
    private String biografia;
    //private ConfigAce confiAcessibilidade;


    @OneToMany(mappedBy = "id.usuario",cascade = CascadeType.ALL)
    private List<ResolveuProblema> resolveuProblemas;

    @OneToMany(mappedBy = "id.usuario",cascade = CascadeType.ALL)
    private List<Comentario> comentarios;
    @OneToMany(mappedBy = "id.usuario",cascade = CascadeType.ALL)
    private List<Like> likes;
    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
    private List<Publicacao> publicacaos;
    public Usuario() {
    }

    public Usuario( String nome, String login, String senha, Long matricula, String biografia) {

        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.matricula = matricula;
        this.biografia = biografia;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}
