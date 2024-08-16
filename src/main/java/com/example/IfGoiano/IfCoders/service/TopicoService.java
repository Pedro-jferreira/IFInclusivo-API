package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.Publicacao;
import com.example.IfGoiano.IfCoders.model.Topico;
import com.example.IfGoiano.IfCoders.repository.TopicoRepositoy;
import com.example.IfGoiano.IfCoders.service.Exception.DataBaseException;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class TopicoService {
    @Autowired
    private TopicoRepositoy topicoRepository;

    public List<Topico> findAll(){
        try {
            return topicoRepository.findAll();
        } catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while fetching all topics"+ e);
        }
    }

    public Topico findById(Long id){
        try {
            Optional<Topico> topico = topicoRepository.findById(id);
            return topico.orElseThrow(() -> new ResourceNotFoundException(id));
        } catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while fetching topic: "+ e);
        }
    }

    @Transactional
    public Topico save(Topico topico){
        try{
            return topicoRepository.save(topico);
        }catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while saving the topic"+ e);
        }
    }

    @Transactional
    public Topico update(Long id, Topico topicoDetails) {
        try {
            Topico topico = findById(id);
            updateTopicoDetails(topico, topicoDetails);
            return topicoRepository.save(topico);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the topic"+ e);
        }
    }
    @Transactional
    public void delete(Long id) {
        try {
            Topico topico = findById(id);
            topicoRepository.delete(topico);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the topic"+ e);
        }
    }

    @Transactional
    public void addPublicacaoToTopico(Long topicoId, Publicacao publicacao) {
        PublicacaoService publicacaoService = new PublicacaoService();
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new ResourceNotFoundException(topicoId));
        topico.addPublicacao(publicacao);
        publicacaoService.save(publicacao);
        topicoRepository.save(topico);
    }

    @Transactional
    public void removePublicacaoFromTopico(Long topicoId, Publicacao publicacao) {
        PublicacaoService publicacaoService = new PublicacaoService();
        Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new ResourceNotFoundException(topicoId));
        topico.removePublicacao(publicacao);
        publicacaoService.save(publicacao);
        topicoRepository.save(topico);
    }

    private void updateTopicoDetails(Topico topico, Topico topicoDetails) {
        topico.setTema(topicoDetails.getTema());
        topico.setDescripcion(topicoDetails.getDescripcion());
        topico.setCategoria(topicoDetails.getCategoria());
        topico.setPublicacoes(topicoDetails.getPublicacoes());


    }
}
