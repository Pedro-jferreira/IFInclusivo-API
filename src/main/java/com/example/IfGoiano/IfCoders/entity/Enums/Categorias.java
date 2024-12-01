package com.example.IfGoiano.IfCoders.entity.Enums;

public enum Categorias {
    REDES(1),
    BANCO_DE_DADOS(2),
    PROGRAMACAO(3),
    WEB(4),
    ESTRUTURA_DE_DADOS(5),
    ARQUITETURA_DE_COMPUTADORES(6);

    private final int categoria;

    Categorias(int categoria) {
        this.categoria = categoria;
    }

    public int getCategoria() {
        return categoria;
    }
}