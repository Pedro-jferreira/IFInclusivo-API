package com.example.IfGoiano.IfCoders.controller.DTO;

import java.sql.Timestamp;
import java.util.Objects;

public class SimpleMessageDTO {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String text;
    private Timestamp dateTime;
    private SimpleUsuarioDTO userEnvia;
    private SimpleUsuarioDTO userRecebe;

    public SimpleMessageDTO() {    }

    public SimpleMessageDTO(Long id, String text , SimpleUsuarioDTO userEnvia, SimpleUsuarioDTO userRecebe) {
        this.id = id;
        this.text = text;
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

    public SimpleUsuarioDTO getUserEnvia() {
        return userEnvia;
    }

    public void setUserEnvia(SimpleUsuarioDTO userEnvia) {
        this.userEnvia = userEnvia;
    }

    public SimpleUsuarioDTO getUserRecebe() {
        return userRecebe;
    }

    public void setUserRecebe(SimpleUsuarioDTO userRecebe) {
        this.userRecebe = userRecebe;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleMessageDTO that = (SimpleMessageDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(text, that.text) &&
                Objects.equals(dateTime, that.dateTime) && Objects.equals(userEnvia, that.userEnvia) &&
                Objects.equals(userRecebe, that.userRecebe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, dateTime, userEnvia, userRecebe);
    }
}
