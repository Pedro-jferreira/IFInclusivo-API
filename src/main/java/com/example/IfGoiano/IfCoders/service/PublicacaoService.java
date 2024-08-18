package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.Comentario;
import com.example.IfGoiano.IfCoders.model.Like;
import com.example.IfGoiano.IfCoders.model.Publicacao;
import com.example.IfGoiano.IfCoders.model.Usuario;
import com.example.IfGoiano.IfCoders.repository.PublicacaoRepositoy;
import com.example.IfGoiano.IfCoders.service.Exception.DataBaseException;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacaoService {
    @Autowired
    private PublicacaoRepositoy publicacaoRepositoy;


    public List<Publicacao> findAll(){
        try {
            return publicacaoRepositoy.findAll();
        } catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while fetching all publications"+ e);
        }
    }

    public Publicacao findById(Long id){
        try {
            Optional<Publicacao> publicacao = publicacaoRepositoy.findById(id);
            return publicacao.orElseThrow(() -> new ResourceNotFoundException(id));
        } catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while fetching publication with ID: "+ e);
        }
    }

    @Transactional
    public Publicacao save(Publicacao publicacao){
        try{
            return publicacaoRepositoy.save(publicacao);
        }catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while saving the publication"+ e);
        }
    }

    @Transactional
    public Publicacao update(Long id, Publicacao publicacaoDetails) {
        try {
            Publicacao publicacao = findById(id);
            updatePublicacaoDetails(publicacao, publicacaoDetails);
            return publicacaoRepositoy.save(publicacao);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the publication"+ e);
        }
    }
    @Transactional
    public void delete(Long id) {
        try {
            Publicacao publicacao = findById(id);
            publicacaoRepositoy.delete(publicacao);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the publication"+ e);
        }
    }


    private void updatePublicacaoDetails(Publicacao publicacao, Publicacao publicacaoDetails) {
        publicacao.setText(publicacaoDetails.getText());
        publicacao.setUrlVideo(publicacaoDetails.getUrlVideo());
        publicacao.setUrlFoto(publicacaoDetails.getUrlFoto());
        publicacao.setLocalDateTime(publicacaoDetails.getLocalDateTime());
        publicacao.setUsuario(publicacaoDetails.getUsuario());
        publicacao.setTopico(publicacaoDetails.getTopico());
        publicacao.setLikes(publicacaoDetails.getLikes());
        publicacao.setComentarios(publicacaoDetails.getComentarios());

    }
}
