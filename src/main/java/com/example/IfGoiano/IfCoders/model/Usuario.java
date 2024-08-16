package com.example.IfGoiano.IfCoders.model;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
    private ConfigAcessibilidade configAcessibilidade;

    @OneToMany(mappedBy = "id.usuario",cascade = CascadeType.ALL)
    private List<ResolveuProblema> resolveuProblemas;

    @OneToMany(mappedBy = "id.usuario",cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "id.usuario",cascade = CascadeType.ALL)
    private List<Like> likes;

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL)
    private List<Publicacao> publicacaos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ConfigAcessibilidade> config;

    public Usuario () {

    }

    public Usuario(String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidade configAcessibilidade) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.matricula = matricula;
        this.biografia = biografia;
        this.configAcessibilidade = configAcessibilidade;
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

    public ConfigAcessibilidade getConfigAcessibilidade() {
        return configAcessibilidade;
    }

    public void setConfigAcessibilidade(ConfigAcessibilidade configAcessibilidade) {
        this.configAcessibilidade = configAcessibilidade;
    }

    public List<ResolveuProblema> getResolveuProblemas() {
        return resolveuProblemas;
    }

    public void setResolveuProblemas(List<ResolveuProblema> resolveuProblemas) {
        this.resolveuProblemas = resolveuProblemas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Publicacao> getPublicacaos() {
        return publicacaos;
    }

    public void setPublicacaos(List<Publicacao> publicacaos) {
        this.publicacaos = publicacaos;
    }

    public List<ConfigAcessibilidade> getConfig() {
        return config;
    }

    public void setConfig(List<ConfigAcessibilidade> config) {
        this.config = config;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome) && Objects.equals(login, usuario.login) && Objects.equals(senha, usuario.senha) && Objects.equals(matricula, usuario.matricula) && Objects.equals(biografia, usuario.biografia) && Objects.equals(configAcessibilidade, usuario.configAcessibilidade) && Objects.equals(resolveuProblemas, usuario.resolveuProblemas) && Objects.equals(comentarios, usuario.comentarios) && Objects.equals(likes, usuario.likes) && Objects.equals(publicacaos, usuario.publicacaos) && Objects.equals(config, usuario.config);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, login, senha, matricula, biografia, configAcessibilidade, resolveuProblemas, comentarios, likes, publicacaos, config);
    }
}
