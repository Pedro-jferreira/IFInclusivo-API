package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;
import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;

import com.example.IfGoiano.IfCoders.controller.mapper.TopicoMapper;
import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import com.example.IfGoiano.IfCoders.repository.TopicoRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoServiceImpl {
    @Autowired
    private TopicoRepositoy topicoRepository;
    @Autowired
    private TopicoMapper mapper;

    public List<TopicoOutputDTO> findAll(){
        try {
            return topicoRepository.findAll().stream().map(mapper::topicoToTopicoDTO).collect(Collectors.toList());
        } catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while fetching all topics"+ e);
        }
    }

    public TopicoOutputDTO findById(Long id){
        try {
            Optional<TopicoEntity> topico = topicoRepository.findById(id);
            if (topico.isPresent()) return mapper.topicoToTopicoDTO(topico.get());
            else throw  new ResourceNotFoundException(id);
        } catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while fetching topic: "+ e);
        }
    }

    @Transactional
    public TopicoOutputDTO save(TopicoOutputDTO topico){
        try{
            return mapper.topicoToTopicoDTO(topicoRepository.save(mapper.topicoDTOToTopico(topico)));
        }catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while saving the topic"+ e);
        }
    }

    @Transactional
    public TopicoOutputDTO update(Long id, TopicoOutputDTO topicoDetails) {
        try {
            Optional<TopicoEntity> topicoOpt = topicoRepository.findById(id);
            if (topicoOpt.isPresent()) {
                TopicoEntity topicoEntity = topicoOpt.get();
                mapper.updateTopicoFromDTO(topicoDetails, topicoEntity);
                return mapper.topicoToTopicoDTO(topicoEntity);
            }else throw  new ResourceNotFoundException(id);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the topic"+ e);
        }
    }
    @Transactional
    public void delete(Long id) {
        try {
            TopicoOutputDTO topico = findById(id);
            topicoRepository.delete(mapper.topicoDTOToTopico(topico));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the topic"+ e);
        }
    }

}
