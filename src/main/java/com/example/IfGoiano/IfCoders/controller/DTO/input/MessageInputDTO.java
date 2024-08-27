package com.example.IfGoiano.IfCoders.controller.DTO.input;

import java.sql.Timestamp;
import java.util.Objects;

public class MessageInputDTO {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String text;
    private Timestamp dateTime;
    private Boolean video;
    private UsuarioInputDTO userEnvia;
    private UsuarioInputDTO userRecebe;

    public MessageInputDTO() {    }

    public MessageInputDTO(Long id, String text , UsuarioInputDTO userEnvia, UsuarioInputDTO userRecebe) {
        this.id = id;
        this.text = text;
        this.video = false;
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

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean view) {
        this.video = view;
    }

    public UsuarioInputDTO getUserEnvia() {
        return userEnvia;
    }

    public void setUserEnvia(UsuarioInputDTO userEnvia) {
        this.userEnvia = userEnvia;
    }

    public UsuarioInputDTO getUserRecebe() {
        return userRecebe;
    }

    public void setUserRecebe(UsuarioInputDTO userRecebe) {
        this.userRecebe = userRecebe;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageInputDTO that = (MessageInputDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(text, that.text)
                && Objects.equals(dateTime, that.dateTime) && Objects.equals(video, that.video)
                && Objects.equals(userEnvia, that.userEnvia) && Objects.equals(userRecebe, that.userRecebe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, dateTime, video, userEnvia, userRecebe);
    }
}
