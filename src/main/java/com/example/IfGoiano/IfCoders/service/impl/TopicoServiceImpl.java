package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.TopicoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ProfessorMapper;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;

import com.example.IfGoiano.IfCoders.controller.mapper.TopicoMapper;
import com.example.IfGoiano.IfCoders.repository.TopicoRepositoy;
import com.example.IfGoiano.IfCoders.service.ProfessorService;
import com.example.IfGoiano.IfCoders.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Autowired
    ProfessorService professorService;
    @Autowired
    ProfessorMapper professorMapper;

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
    public TopicoOutputDTO save(TopicoInputDTO topico, Long idProfessor){
        var professor = professorService.findById(idProfessor);

        TopicoEntity topicoEntity = mapper.toTopicoEntity(topico);
        topicoEntity.setProfessor(professorMapper.toProfessorEntity(professor));
            return findById(topicoRepository.save(topicoEntity).getId());
    }

    @Override
    @Transactional
    public TopicoOutputDTO update(Long id, TopicoInputDTO topicoDetails) {
            var topico = topicoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
                mapper.updateTopicoEntityFromDTO(topicoDetails,topico );
                return mapper.toTopicoOutputDTO(topico);

    }

    @Override
    public Page<TopicoOutputDTO> findByCategoria(Categorias categoria, int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("dataCriacao").descending());
        return topicoRepository.findByCategoria(categoria, pageable).map(mapper::toTopicoOutputDTO);
    }

    @Override
    public Page<TopicoOutputDTO> searchTopicByTermQuickly(String termo, int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return topicoRepository.findByTituloStartingWithIgnoreCase(termo, pageable)
                .map(mapper::toTopicoOutputDTO);
    }

    @Override
    public Page<TopicoOutputDTO> searchTopicByTermDeeply(String termo, int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return topicoRepository.searchTopicByTermDeeply(termo, pageable).map(mapper::toTopicoOutputDTO);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        topicoRepository.delete(mapper.toTopicoEntity(findById(id)));
    }
}