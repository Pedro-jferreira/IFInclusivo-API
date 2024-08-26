package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.TopicoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;

import com.example.IfGoiano.IfCoders.controller.mapper.TopicoMapper;
import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import com.example.IfGoiano.IfCoders.repository.TopicoRepositoy;
import com.example.IfGoiano.IfCoders.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoServiceImpl  implements TopicoService {
    @Autowired
    private TopicoRepositoy topicoRepository;
    @Autowired
    private TopicoMapper mapper;


    @Transactional
    public TopicoOutputDTO save(TopicoInputDTO topico){
            return mapper.toTopicoOutputDTO(topicoRepository.save(mapper.toTopicoEntity(topico)));
    }

    @Transactional
    public TopicoOutputDTO update(Long id, TopicoInputDTO topicoDetails) {
            Optional<TopicoEntity> topicoOpt = topicoRepository.findById(id);
            if (topicoOpt.isPresent()) {
                TopicoEntity topicoEntity = topicoOpt.get();
                mapper.updateTopicoEntityFromDTO(topicoDetails, topicoEntity);
                return mapper.toTopicoOutputDTO(topicoEntity);
            }else throw  new ResourceNotFoundException(id);
    }

    @Transactional
    public void delete(Long id) {
            topicoRepository.delete(mapper.toTopicoEntity(findById(id)));
    }

    @Transactional
    public TopicoOutputDTO findById(Long id) {
        Optional<TopicoEntity> topico = topicoRepository.findById(id);
        if (topico.isPresent()) return mapper.toTopicoOutputDTO(topico.get());
        else throw new ResourceNotFoundException(id);
    }

    @Transactional
    public List<TopicoOutputDTO> findAll(){
        return topicoRepository.findAll().stream().map(mapper::toTopicoOutputDTO).collect(Collectors.toList());
    }
}