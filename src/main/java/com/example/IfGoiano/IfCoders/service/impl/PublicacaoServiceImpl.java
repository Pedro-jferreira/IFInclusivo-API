package com.example.IfGoiano.IfCoders.service.impl;


import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.PublicacaoMapper;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.PublicacaoRepositoy;
import com.example.IfGoiano.IfCoders.service.PublicacaoService;
import com.example.IfGoiano.IfCoders.utils.UsuarioFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicacaoServiceImpl implements PublicacaoService {
    @Autowired
    private PublicacaoRepositoy repositoy;

    @Autowired
    private PublicacaoMapper mapper;
    @Autowired
    private UsuarioFinder usuarioFinder;

    @Override
    @Transactional
    public List<PublicacaoOutputDTO> findAll(){
        return repositoy.findAll().stream().map(mapper::toPublicacaoOutputDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PublicacaoOutputDTO findById(Long id){
        Optional<PublicacaoEntity> publicacao = repositoy.findById(id);
        if (publicacao.isPresent()) return mapper.toPublicacaoOutputDTO(publicacao.get());
        else throw new ResourceNotFoundException("Publication not found");
    }

    @Override
    @Transactional
    public PublicacaoOutputDTO save(Long idUser,PublicacaoInputDTO publicacao){
        var user = usuarioFinder.findUsuarioById(idUser);
        PublicacaoEntity publicacaoEntity = mapper.toPublicacaoEntity(publicacao);
        publicacaoEntity.setUsuario(user);

        return findById(repositoy.save(publicacaoEntity).getId()) ;
    }

    @Override
    @Transactional
    public PublicacaoOutputDTO update(Long id, PublicacaoInputDTO publicacaoDetails) {
        Optional<PublicacaoEntity> publicacaoOpt = repositoy.findById(id);
        if (publicacaoOpt.isPresent()) {
            PublicacaoEntity publicacaoEntity = publicacaoOpt.get();
            mapper.updatePublicacaoEntityFromDTO(publicacaoDetails, publicacaoEntity);
            return mapper.toPublicacaoOutputDTO(repositoy.save(publicacaoEntity));
        }else throw new ResourceNotFoundException("Publication not found");
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repositoy.delete(mapper.toPublicacaoEntity(findById(id)));
    }
}