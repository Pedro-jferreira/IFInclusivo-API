package com.example.IfGoiano.IfCoders.apresentacaoEnsinoMedio.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull()

    private String name;

    private String imgUrl;

    private Timestamp vistoPorUltimo;

    public UserEntity() {

    }

    public UserEntity(Long id,String name, String imgUrl) {

        this.id= id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.vistoPorUltimo =  new Timestamp(System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getVistoPorUltimo() {
        return vistoPorUltimo;
    }

}
