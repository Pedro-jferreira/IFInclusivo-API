package com.example.IfGoiano.IfCoders.entity.Enums;

public enum Status {
    APROVADO(1),
    REPROVADO(2),
    EMANALISE(3);

    private final int status;
    Status(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
