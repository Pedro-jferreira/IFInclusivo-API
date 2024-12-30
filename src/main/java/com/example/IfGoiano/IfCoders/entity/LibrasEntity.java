package com.example.IfGoiano.IfCoders.entity;


import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
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
    @Column(length = 800)
    private String descricao;
    private String url;
    private String video;
    private String foto;
    private String justificativa;
    private Status status;
    private Categorias categorias;


    @ManyToMany(mappedBy = "libras")
    private List<InterpreteEntity> interprete = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="sugeriu_libras",
            joinColumns = @JoinColumn(name = "libras_id"),
            inverseJoinColumns = @JoinColumn(name ="usuario_id" ))
    private List<UsuarioEntity> sugeriu = new ArrayList<>();

}
