package com.example.IfGoiano.IfCoders.entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "message")
public class MessageEntity implements Serializable {

    @Serial
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
    @ManyToOne
    private UserEntity userEnvia;

    @NotNull
    @ManyToOne
    private UserEntity userRecebe;

    public MessageEntity() {
        super();
    }

    public MessageEntity(Long id, String text , UserEntity userEnvia, UserEntity userRecebe) {
        this.id = id;
        this.text = text;
        this.dateTime = new Timestamp(System.currentTimeMillis());
        this.view = false;
        this.userEnvia = userEnvia;
        this.userRecebe = userRecebe;
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

    public UserEntity getUserEnvia() {
        return userEnvia;
    }

    public void setUserEnvia(UserEntity userEnvia) {
        this.userEnvia = userEnvia;
    }

    public UserEntity getUserRecebe() {
        return userRecebe;
    }

    public void setUserRecebe(UserEntity userRecebe) {
        this.userRecebe = userRecebe;
    }
}