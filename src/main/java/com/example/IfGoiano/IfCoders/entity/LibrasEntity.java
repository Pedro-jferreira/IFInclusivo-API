package com.example.IfGoiano.IfCoders.entity;


import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "libras")
@Data
public class LibrasEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String palavra;
    private String descricao;
    private String url;
    private String video;
    private String foto;
    private String justificativa;
    private Status status;


    @ManyToMany
    private List<InterpreteEntity> interpreteAnalise = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity sugeriu;

}
