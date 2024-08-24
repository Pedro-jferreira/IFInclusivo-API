package com.example.IfGoiano.IfCoders.controller.DTO.output;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Objects;

public class LikeDTO extends RepresentationModel<LikeDTO> {
    private LocalDateTime localDateTime;

    public LikeDTO() {
    }

    public LikeDTO(LocalDateTime localDateTime) {

        this.localDateTime = localDateTime;
    }



    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LikeDTO likeDTO = (LikeDTO) o;
        return Objects.equals(localDateTime, likeDTO.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), localDateTime);
    }
}
