package com.example.IfGoiano.IfCoders.entity.Enums;

public enum TemaCSS {
    TEMA1(1),
    TEMA2(2),
    TEMA3(3);

    private final int tema;

    TemaCSS(int tema) {
        this.tema = tema;
    }

    public int getTema() {
        return tema;
    }
}
