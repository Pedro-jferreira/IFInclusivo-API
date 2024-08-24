package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.PublicacaoMapper;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.repository.PublicacaoRepositoy;
import com.example.IfGoiano.IfCoders.service.PublicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicacaoServiceImpl implements PublicacaoService {
    @Autowired
    private PublicacaoRepositoy repositoy;
    @Autowired
    private PublicacaoMapper mapper;


    public List<PublicacaoDTO> findAll(){
        try {
            return repositoy.findAll().stream().map(mapper::publicacaoToPublicacaoDTO).collect(Collectors.toList());
        } catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while fetching all publications"+ e);
        }
    }

    public PublicacaoDTO findById(Long id){
        try {
            Optional<PublicacaoEntity> publicacao = repositoy.findById(id);
            if (publicacao.isPresent()) return mapper.publicacaoToPublicacaoDTO(publicacao.get());
            else throw new ResourceNotFoundException("Publication not found");
        } catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while fetching publication with ID: "+ e);
        }
    }

    @Transactional
    public PublicacaoDTO save(PublicacaoDTO publicacao){
        try{
            return mapper.publicacaoToPublicacaoDTO(repositoy.save(mapper.publicacaoDTOToPublicacao(publicacao))) ;
        }catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while saving the publication"+ e);
        }
    }

    @Transactional
    public PublicacaoDTO update(Long id, PublicacaoDTO publicacaoDetails) {
        try {
            Optional<PublicacaoEntity> publicacaoOpt = repositoy.findById(id);
            if (publicacaoOpt.isPresent()) {
                PublicacaoEntity publicacaoEntity = publicacaoOpt.get();
                mapper.updatePublicacaoFromDTO(publicacaoDetails, publicacaoEntity);
                return mapper.publicacaoToPublicacaoDTO(repositoy.save(publicacaoEntity));
            }else throw new ResourceNotFoundException("Publication not found");
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the publication"+ e);
        }
    }
    @Transactional
    public void delete(Long id) {
        try {
            PublicacaoDTO publicacao = findById(id);
            repositoy.delete(mapper.publicacaoDTOToPublicacao(publicacao));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the publication"+ e);
        }
    }

}
