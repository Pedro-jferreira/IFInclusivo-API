package com.example.IfGoiano.IfCoders.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "message")
public class MessageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull()
    private String text;

    @NotNull
    private Timestamp dateTime;
    @NotNull
    private Boolean view;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private UsuarioEntity userEnvia;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private UsuarioEntity userRecebe;

    public MessageEntity() {
        super();
    }

    public MessageEntity(Long id, String text , UsuarioEntity userEnvia, UsuarioEntity userRecebe) {
        this.id = id;
        this.text = text;
        this.view = false;
        this.userEnvia = userEnvia;
        this.userRecebe = userRecebe;
        this.dateTime = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getView() {
        return view;
    }

    public void setView(Boolean view) {
        this.view = view;
    }

    public UsuarioEntity getUserEnvia() {
        return userEnvia;
    }

    public void setUserEnvia(UsuarioEntity userEnvia) {
        this.userEnvia = userEnvia;
    }

    public UsuarioEntity getUserRecebe() {
        return userRecebe;
    }

    public void setUserRecebe(UsuarioEntity userRecebe) {
        this.userRecebe = userRecebe;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageEntity that = (MessageEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(text, that.text) &&
                Objects.equals(dateTime, that.dateTime) && Objects.equals(view, that.view) &&
                Objects.equals(userEnvia, that.userEnvia) && Objects.equals(userRecebe, that.userRecebe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, dateTime, view, userEnvia, userRecebe);
    }
}