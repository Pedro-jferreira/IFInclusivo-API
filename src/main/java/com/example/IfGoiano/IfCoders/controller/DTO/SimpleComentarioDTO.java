package com.example.IfGoiano.IfCoders.controller.DTO;

import java.util.Objects;

public class SimpleComentarioDTO {
    private Long id;
    private String content;

    public SimpleComentarioDTO() {
    }

    public SimpleComentarioDTO(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleComentarioDTO that = (SimpleComentarioDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }
}
