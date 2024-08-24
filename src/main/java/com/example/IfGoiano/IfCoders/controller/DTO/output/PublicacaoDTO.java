package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.input.LikeDTO;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PublicacaoDTO extends RepresentationModel<PublicacaoDTO> {
    private Long id;
    private String text;
    private String urlVideo;
    private String urlFoto;
    private LocalDateTime localDateTime;
    private TopicoDTO topicoDTO;
    List<LikeDTO> likeDTOS;

    public PublicacaoDTO() {
    }

    public PublicacaoDTO(Long id, String text, String urlVideo, String urlFoto, LocalDateTime localDateTime, TopicoDTO topicoDTO, List<LikeDTO> likeDTOS) {
        this.id = id;
        this.text = text;
        this.urlVideo = urlVideo;
        this.urlFoto = urlFoto;
        this.localDateTime = localDateTime;
        this.topicoDTO = topicoDTO;
        this.likeDTOS = likeDTOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public TopicoDTO getTopicoDTO() {
        return topicoDTO;
    }

    public void setTopicoDTO(TopicoDTO topicoDTO) {
        this.topicoDTO = topicoDTO;
    }

    public List<LikeDTO> getLikeDTOS() {
        return likeDTOS;
    }

    public void setLikeDTOS(List<LikeDTO> likeDTOS) {
        this.likeDTOS = likeDTOS;
    }
    public int getCountLikes(){
        return likeDTOS.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PublicacaoDTO that = (PublicacaoDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(text, that.text) && Objects.equals(topicoDTO, that.topicoDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, text, topicoDTO);
    }
}
