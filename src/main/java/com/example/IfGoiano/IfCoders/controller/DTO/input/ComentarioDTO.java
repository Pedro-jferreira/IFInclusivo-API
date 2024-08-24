package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ResolveuProblemaDTO;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ComentarioDTO   extends RepresentationModel<ComentarioDTO> {
    private Long id;
    private LocalDateTime localDateTime;
    private String content;
    private PublicacaoDTO publicacaoDTO;

    private ComentarioDTO comentarioPaiDTO;
    private List<ResolveuProblemaDTO> resolveuProblemaDTOS;

    public ComentarioDTO() {
    }

    public ComentarioDTO(Long id, LocalDateTime localDateTime, String content, PublicacaoDTO publicacaoDTO,  ComentarioDTO comentarioPaiDTO, List<ResolveuProblemaDTO> resolveuProblemaDTOS) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.content = content;
        this.publicacaoDTO = publicacaoDTO;

        this.comentarioPaiDTO = comentarioPaiDTO;
        this.resolveuProblemaDTOS = resolveuProblemaDTOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PublicacaoDTO getPublicacaoDTO() {
        return publicacaoDTO;
    }

    public void setPublicacaoDTO(PublicacaoDTO publicacaoDTO) {
        this.publicacaoDTO = publicacaoDTO;
    }


    public ComentarioDTO getComentarioPaiDTO() {
        return comentarioPaiDTO;
    }

    public void setComentarioPaiDTO(ComentarioDTO comentarioPaiDTO) {
        this.comentarioPaiDTO = comentarioPaiDTO;
    }

    public List<ResolveuProblemaDTO> getResolveuProblemaDTOS() {
        return resolveuProblemaDTOS;
    }

    public void setResolveuProblemaDTOS(List<ResolveuProblemaDTO> resolveuProblemaDTOS) {
        this.resolveuProblemaDTOS = resolveuProblemaDTOS;
    }
    public int getCountResolveuProblemaDTOS() {
        return resolveuProblemaDTOS.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ComentarioDTO that = (ComentarioDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
