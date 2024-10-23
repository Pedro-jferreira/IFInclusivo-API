package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.TopicoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;

import com.example.IfGoiano.IfCoders.controller.mapper.TopicoMapper;
import com.example.IfGoiano.IfCoders.repository.TopicoRepositoy;
import com.example.IfGoiano.IfCoders.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoServiceImpl  implements TopicoService {
    @Autowired
    private TopicoRepositoy topicoRepository;

    @Autowired
    private TopicoMapper mapper;

    @Override
    @Transactional
    public List<TopicoOutputDTO> findAll(){
        return topicoRepository.findAll().stream().map(mapper::toTopicoOutputDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TopicoOutputDTO findById(Long id) {
        var topico = topicoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        return mapper.toTopicoOutputDTO(topico);
    }

    @Override
    @Transactional
    public TopicoOutputDTO save(TopicoInputDTO topico){
            return findById(topicoRepository.save(mapper.toTopicoEntity(topico)).getId());
    }

    @Override
    @Transactional
    public TopicoOutputDTO update(Long id, TopicoInputDTO topicoDetails) {
            var topico = topicoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
                mapper.updateTopicoEntityFromDTO(topicoDetails,topico );
                return mapper.toTopicoOutputDTO(topico);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        topicoRepository.delete(mapper.toTopicoEntity(findById(id)));
    }
}