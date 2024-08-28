package com.example.IfGoiano.IfCoders.controller.DTO.input;

import java.util.Objects;

public class MessageInputDTO {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String text;
    private Boolean video;

    public MessageInputDTO() {    }

    public MessageInputDTO(Long id, String text) {
        this.id = id;
        this.text = text;
        this.video = false;
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

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean view) {
        this.video = view;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageInputDTO that = (MessageInputDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(text, that.text) && Objects.equals(video, that.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, video);
    }
}
