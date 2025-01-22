package com.example.IfGoiano.IfCoders.entity;

import com.example.IfGoiano.IfCoders.entity.Enums.TemaCSS;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;


@Entity
@Data
public class ConfigAcessibilidadeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String audicao;
    private TemaCSS tema;
    private String zoom;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", unique = true)
    private UsuarioEntity usuario;
}
