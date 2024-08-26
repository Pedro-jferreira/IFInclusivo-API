package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoNapneInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoNapneOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.AlunoNapneMapper;
import com.example.IfGoiano.IfCoders.entity.AlunoNapneEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.AlunoNapneRepository;
import com.example.IfGoiano.IfCoders.service.AlunoNapneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoNapneServiceImpl implements AlunoNapneService {

    @Autowired
    AlunoNapneRepository alunoNapneRepository;

    @Autowired
    private AlunoNapneMapper alunoNapneMapper;

    public AlunoNapneOutputDTO save(AlunoNapneInputDTO alunoNapneInput) {
        var entity = alunoNapneMapper.toAlunoNapneEntity(alunoNapneInput);
        alunoNapneRepository.save(entity);

        return alunoNapneMapper.toAlunoNapneOutputDTO(entity);
    }

    @Override
    public AlunoNapneOutputDTO update(AlunoNapneInputDTO alunoNapne, Long id) {
        var entity = alunoNapneRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        alunoNapneMapper.updateAlunoNapneEntiryFromDTO(alunoNapne,entity);
        return alunoNapneMapper.toAlunoNapneOutputDTO(entity);

    }



    @Override
    public void delete(Long id) {
        var entity = alunoNapneRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        alunoNapneRepository.delete(entity);
    }

    @Override
    public AlunoNapneOutputDTO findById(Long id){
        var aluno  = alunoNapneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return alunoNapneMapper.toAlunoNapneOutputDTO(aluno);
    }

    @Override
    public List<AlunoNapneOutputDTO> findAll(){
        List<AlunoNapneOutputDTO> listAlunos = new ArrayList<>();
        alunoNapneRepository.findAll().stream().forEach(alunoNapneEntity -> listAlunos.add(alunoNapneMapper.toAlunoNapneOutputDTO(alunoNapneEntity)));

        return listAlunos;
    }


}
