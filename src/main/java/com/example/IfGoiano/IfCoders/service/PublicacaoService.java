package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.DTO.PublicacaoDTO;
import com.example.IfGoiano.IfCoders.mapper.LikeMapper;
import com.example.IfGoiano.IfCoders.mapper.PublicacaoMapper;
import com.example.IfGoiano.IfCoders.model.Publicacao;
import com.example.IfGoiano.IfCoders.repository.LikeRepository;
import com.example.IfGoiano.IfCoders.repository.PublicacaoRepositoy;
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
public class PublicacaoService {
    @Autowired
    private PublicacaoRepositoy repositoy;
    @Autowired
    private PublicacaoMapper mapper;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private LikeMapper likeMapper;


    public List<PublicacaoDTO> findAll(){
        try {
            return repositoy.findAll().stream().map(mapper::publicacaoToPublicacaoDTO).collect(Collectors.toList());
        } catch (DataBaseException e){
            throw new DataBaseException("Database error occurred while fetching all publications"+ e);
        }
    }

    public PublicacaoDTO findById(Long id){
        try {
            Optional<Publicacao> publicacao = repositoy.findById(id);
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
            Optional<Publicacao> publicacaoOpt = repositoy.findById(id);
            if (publicacaoOpt.isPresent()) {
                Publicacao publicacao = publicacaoOpt.get();
                mapper.updatePublicacaoFromDTO(publicacaoDetails,publicacao);
                return mapper.publicacaoToPublicacaoDTO(repositoy.save(publicacao));
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
