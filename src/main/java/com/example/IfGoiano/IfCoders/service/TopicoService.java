package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.DTO.TopicoDTO;
import com.example.IfGoiano.IfCoders.mapper.TopicoMapper;
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
import java.util.stream.Collectors;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepositoy topicoRepository;
    @Autowired
    private TopicoMapper mapper;

    public List<TopicoDTO> findAll(){
        try {
            return topicoRepository.findAll().stream().map(mapper::topicoToTopicoDTO).collect(Collectors.toList());
        } catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while fetching all topics"+ e);
        }
    }

    public TopicoDTO findById(Long id){
        try {
            Optional<Topico> topico = topicoRepository.findById(id);
            if (topico.isPresent()) return mapper.topicoToTopicoDTO(topico.get());
            else throw  new ResourceNotFoundException(id);
        } catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while fetching topic: "+ e);
        }
    }

    @Transactional
    public TopicoDTO save(TopicoDTO topico){
        try{
            return mapper.topicoToTopicoDTO(topicoRepository.save(mapper.topicoDTOToTopico(topico)));
        }catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while saving the topic"+ e);
        }
    }

    @Transactional
    public TopicoDTO update(Long id, TopicoDTO topicoDetails) {
        try {
            Optional<Topico> topicoOpt = topicoRepository.findById(id);
            if (topicoOpt.isPresent()) {
                Topico topico = topicoOpt.get();
                mapper.updateTopicoFromDTO(topicoDetails,topico);
                return mapper.topicoToTopicoDTO(topico);
            }else throw  new ResourceNotFoundException(id);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the topic"+ e);
        }
    }
    @Transactional
    public void delete(Long id) {
        try {
            TopicoDTO topico = findById(id);
            topicoRepository.delete(mapper.topicoDTOToTopico(topico));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the topic"+ e);
        }
    }

}
