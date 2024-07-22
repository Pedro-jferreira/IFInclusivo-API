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
    private LikeService likeService = new LikeService();

    private ComentarioService comentarioService = new ComentarioService();

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

    @Transactional
    public  Like receberLike(Long publicacaoId, Usuario usuario){
        try{
            Optional<Publicacao> publicacaoOpt = publicacaoRepositoy.findById(publicacaoId);
            if (publicacaoOpt.isPresent()){
                Publicacao publicacao = publicacaoOpt.get();
                Like like = new Like(usuario,publicacao);
                publicacao.addLikeToPublicacao(like);
                likeService.save(like);
                publicacaoRepositoy.save(publicacao);
                return like;
            } else throw new ResourceNotFoundException(publicacaoId);
        } catch (DataBaseException e){
            throw  new DataBaseException("Database error occurred when adding a like to the post"+ e);
        }

    }
    public void removerLike(Long publicacaoId, Usuario usuario) {
        try {
            Optional<Publicacao> publicacaoOpt = publicacaoRepositoy.findById(publicacaoId);
            if (publicacaoOpt.isPresent()){
                Publicacao publicacao = publicacaoOpt.get();
                Optional<Like> like = likeService.findByUsuarioAndPublicacao(usuario,publicacao);
                if (like.isPresent()){
                    Like like1 = like.get();
                    likeService.delete(like1.getId());
                    publicacao.removeLikeFromPublicacao(like1);
                    usuario.removeLikeFromUser(like1);
                    publicacaoRepositoy.save(publicacao);
                }else throw new ResourceNotFoundException(like.get().getId());
            }else throw new ResourceNotFoundException(publicacaoId);
        }catch (DataBaseException e){
            throw new DataBaseException("Database error occurred when removing a like to the post"+ e);
        }
    }


    public Comentario receberComentario(Long publicacaoId, Comentario comentario) {
        try{
            Optional<Publicacao> publicacaoOpt = publicacaoRepositoy.findById(publicacaoId);
            if (publicacaoOpt.isPresent()){
                Publicacao publicacao = publicacaoOpt.get();
                publicacao.addCommentToPublicacao(comentario);
                comentarioService.save(comentario);
                publicacaoRepositoy.save(publicacao);
                return comentario;
            } else throw  new ResourceNotFoundException(publicacaoId);
        } catch (DataBaseException e){
            throw new DataBaseException("Database error occurred when adding a Comment to the post"+ e);
        }

    }
    @Transactional
    public void removerComentario(Long publicacaoId, Usuario  usuario) {
        try {
            Optional<Publicacao> publicacaoOpt = publicacaoRepositoy.findById(publicacaoId);
            if (publicacaoOpt.isPresent()) {
                Publicacao publicacao = publicacaoOpt.get();
                Optional<Comentario> comentarioOpt =comentarioService.findByUsuarioAndPublicacao(usuario, publicacao);
                if (comentarioOpt.isPresent()){
                    Comentario comentario = comentarioOpt.get();
                    comentarioService.delete(comentario.getId());
                    publicacao.removeCommentFromPublicacao(comentario);
                    publicacaoRepositoy.save(publicacao);
                    usuario.removeCommentFromUser(comentario);
                } else  throw new ResourceNotFoundException(comentarioOpt.get().getId());
            }else throw new ResourceNotFoundException(publicacaoId);
        } catch (Exception e) {
            throw new DataBaseException("Database error occurred when removing a Comment to the post"+ e);
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
