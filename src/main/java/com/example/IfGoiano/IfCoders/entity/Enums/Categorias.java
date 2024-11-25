package com.example.IfGoiano.IfCoders.entity.Enums;

public enum Categorias {
    REDES("Redes"),
    BANCO_DE_DADOS("Banco de Dados"),
    PROGRAMACAO("Programação"),
    WEB("Web"),
    ESTRUTURA_DE_DADOS("Estrutura de Dados"),
    ARQUITETURA_DE_COMPUTADORES("Arquitetura de Computadores");

    private final String descricao;

    Categorias(String descricao) {
        this.descricao =
                descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}