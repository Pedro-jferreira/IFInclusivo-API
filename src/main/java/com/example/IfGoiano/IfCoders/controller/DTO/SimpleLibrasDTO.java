package com.example.IfGoiano.IfCoders.controller.DTO;

import java.util.Objects;

public class SimpleLibrasDTO {
    private Long id;
    private String palavra;

    public SimpleLibrasDTO() {
    }

    public SimpleLibrasDTO(Long id, String palavra) {
        this.id = id;
        this.palavra = palavra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleLibrasDTO that = (SimpleLibrasDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(palavra, that.palavra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, palavra);
    }
}
