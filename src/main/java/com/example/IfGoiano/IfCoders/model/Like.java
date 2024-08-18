package com.example.IfGoiano.IfCoders.model;

import com.example.IfGoiano.IfCoders.model.PK.LikeId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Likes")
public class Like implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private LikeId id = new LikeId();

    public LikeId getId() {
        return id;
    }

    public void setId(LikeId id) {
        this.id = id;
    }
}
