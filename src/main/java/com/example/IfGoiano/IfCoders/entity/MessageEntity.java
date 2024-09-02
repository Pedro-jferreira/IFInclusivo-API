package com.example.IfGoiano.IfCoders.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;



@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "message")
@Data
public class MessageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull()
    private String text;

    @NotNull
    private Timestamp dateTime;
    @NotNull
    private Boolean visualizado;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private UsuarioEntity userEnvia;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private UsuarioEntity userRecebe;

}