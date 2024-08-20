package com.example.IfGoiano.IfCoders.model;

import com.example.IfGoiano.IfCoders.model.PK.ResolveuProblemaId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "SolvedProblems")
public class ResolveuProblema implements Serializable {
    @EmbeddedId
    private ResolveuProblemaId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @MapsId("comentarioId")
    @JoinColumn(name = "comentario_id", nullable = false)
    private Comentario comentario;

    private LocalDateTime dataHoraVoto = LocalDateTime.now();

    public ResolveuProblema() {
    }

    public ResolveuProblema(ResolveuProblemaId id, Usuario usuario, Comentario comentario) {
        this.id = id;
        this.usuario = usuario;
        this.comentario = comentario;
    }

    public ResolveuProblema(ResolveuProblemaId id, Usuario usuario, Comentario comentario, LocalDateTime dataHoraVoto) {
        this.id = id;
        this.usuario = usuario;
        this.comentario = comentario;
        this.dataHoraVoto = dataHoraVoto;
    }

    public ResolveuProblemaId getId() {
        return id;
    }

    public void setId(ResolveuProblemaId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataHoraVoto() {
        return dataHoraVoto;
    }

    public void setDataHoraVoto(LocalDateTime dataHoraVoto) {
        this.dataHoraVoto = dataHoraVoto;
    }
}
