package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.Comentario;
import com.example.IfGoiano.IfCoders.model.PK.ComentarioId;
import com.example.IfGoiano.IfCoders.model.Publicacao;
import com.example.IfGoiano.IfCoders.model.Usuario;
import com.example.IfGoiano.IfCoders.repository.ComentarioRepository;
import com.example.IfGoiano.IfCoders.service.Exception.DataBaseException;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> findAll() {
        try {
            return comentarioRepository.findAll();
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while fetching all comments"+ e);
        }
    }

    public Comentario findById(ComentarioId id) {
        try {
            Optional<Comentario> comentario = comentarioRepository.findById(id);
            return comentario.orElseThrow(() -> new ResourceNotFoundException(id));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while fetching comment : " + e);
        }
    }

    @Transactional
    public Comentario save(Comentario comentario) {
        try {
            if (comentario.getComentarioPai() == null) {
                comentario.setComentarioPai(comentario);
            }
            return comentarioRepository.save(comentario);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while saving the comment"+ e);
        }
    }

    @Transactional
    public Comentario update(ComentarioId id, Comentario comentarioDetails) {
        try {
            Comentario comentario = findById(id);
            updateComentarioDetails(comentario, comentarioDetails);
            return comentarioRepository.save(comentario);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the comment"+ e);
        }
    }

    @Transactional
    public void delete(ComentarioId id) {
        try {
            Comentario comentario = findById(id);
            comentarioRepository.delete(comentario);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the comment"+ e);
        }
    }
    public Optional<Comentario> findByUsuarioAndPublicacao(Usuario usuario, Publicacao publicacao){
        ComentarioId comentarioId = new ComentarioId();
        comentarioId.setUsuario(usuario);
        comentarioId.setPublicacao(publicacao);
        return comentarioRepository.findById(comentarioId);
    }
    @Transactional
    public Comentario receberSubComentario(ComentarioId comentarioPaiId, Usuario usuario, Comentario comentario){

      try {
          Optional<Comentario> comentarioOptional = comentarioRepository.findById(comentarioPaiId);
          if (comentarioOptional.isPresent()){
              Comentario comentarioPai = comentarioOptional.get();
              comentario.setPublicacao(comentarioPai.getPublicacao());
              comentarioPai.addChildCommentToComment(comentario);
              usuario.addCommentToUser(comentario);
              comentarioRepository.save(comentario);
              comentarioRepository.save(comentarioPai);
              return comentario;
          }else throw  new ResourceNotFoundException(comentarioPaiId);
      } catch (DataBaseException e){
          throw new DataBaseException("Database error occurred when adding a Comment to the comment" + e);
      }

    }
    @Transactional
    public void removerSubComentario(ComentarioId comentarioPaiId, ComentarioId subComentarioId) {
        try {
            Optional<Comentario> comentarioPaiOptional = comentarioRepository.findById(comentarioPaiId);
            if (comentarioPaiOptional.isPresent()) {
                Comentario comentarioPai = comentarioPaiOptional.get();
                Optional<Comentario> subComentarioOptional = comentarioRepository.findById(subComentarioId);
                if (subComentarioOptional.isPresent()) {
                    Comentario subComentario = subComentarioOptional.get();
                    comentarioPai.removeChildCommentFromComment(subComentario);
                    comentarioRepository.save(comentarioPai);
                    comentarioRepository.delete(subComentario);
                } else {
                    throw new ResourceNotFoundException(subComentarioId);
                }
            } else {
                throw new ResourceNotFoundException(comentarioPaiId);
            }
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred when removing a sub-comment" + e);
        }
    }
    private void updateComentarioDetails(Comentario comentario, Comentario comentarioDetails) {
        comentario.setContent(comentarioDetails.getContent());
        comentario.setLocalDateTime(comentarioDetails.getLocalDateTime());
        comentario.setComentarioPai(comentarioDetails.getComentarioPai());
        comentario.setResolveuProblemas(comentarioDetails.getResolveuProblemas());
        comentario.setComentariosFilhos(comentarioDetails.getComentariosFilhos());
    }
}
