package com.example.IfGoiano.IfCoders.controller.DTO.output;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Objects;

public class ResolveuProblemaDTO extends RepresentationModel<ResolveuProblemaDTO> {


    private LocalDateTime localDateTime;

    public ResolveuProblemaDTO() {
    }

    public ResolveuProblemaDTO( LocalDateTime localDateTime) {

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
        ResolveuProblemaDTO that = (ResolveuProblemaDTO) o;
        return Objects.equals(localDateTime, that.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), localDateTime);
    }
}
