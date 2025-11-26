package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoNapneInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.update.AlunoNapneUpdateDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoNapneOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleAlunoDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.AlunoMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.AlunoNapneMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
import com.example.IfGoiano.IfCoders.entity.AlunoNapneEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.AlunoNapneRepository;
import com.example.IfGoiano.IfCoders.repository.AlunoRepository;
import com.example.IfGoiano.IfCoders.service.AlunoNapneService;
import com.example.IfGoiano.IfCoders.service.ConfigAcessibilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoNapneServiceImpl implements AlunoNapneService {

    @Autowired
    AlunoNapneRepository alunoNapneRepository;

    @Autowired
    private AlunoNapneMapper alunoNapneMapper;

    @Autowired
    ConfigAcessibilidadeService configAcessibilidadeService;
    @Autowired
    ConfigAcblMapper configAcblMapper;
    
    @Autowired
    AlunoRepository alunoRepository;
    
    @Autowired
    AlunoMapper alunoMapper;


    @Override
    public List<AlunoNapneOutputDTO> findAll(){
        List<AlunoNapneOutputDTO> listAlunos = new ArrayList<>();
        alunoNapneRepository.findAll().stream().forEach(alunoNapneEntity -> listAlunos.add(alunoNapneMapper.toAlunoNapneOutputDTO(alunoNapneEntity)));

        return listAlunos;
    }

    @Override
    public AlunoNapneOutputDTO findById(Long id){
        var aluno  = alunoNapneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return alunoNapneMapper.toAlunoNapneOutputDTO(aluno);
    }

    @Override
    @Transactional
    public AlunoNapneOutputDTO save(AlunoNapneInputDTO alunoNapneInput, Long idConfigAc) {
        var acessibilidade = configAcessibilidadeService.findById(idConfigAc);
        AlunoNapneEntity a = alunoNapneMapper.toAlunoNapneEntity(alunoNapneInput);
        a.setConfigAcessibilidadeEntity(configAcblMapper.toConfigAcblEntity(acessibilidade));
        return findById(alunoNapneRepository.save(a).getId());
    }

    @Override
    @Transactional
    public AlunoNapneOutputDTO update(AlunoNapneUpdateDTO alunoNapne, String username) {
        var entity = alunoNapneRepository.findByLogin(username).orElseThrow(()-> new ResourceNotFoundException(username));
        alunoNapneMapper.updateAlunoNapneEntiryFromDTO(alunoNapne,entity);
        return alunoNapneMapper.toAlunoNapneOutputDTO(entity);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        var entity = alunoNapneRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        alunoNapneRepository.delete(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return alunoNapneRepository.existsById(id);
    }
    
    @Override
    public List<SimpleAlunoDTO> buscarAlunosPorTermo(String termo) {
        return alunoRepository.buscarAlunosPorTermo(termo)
                .stream()
                .map(alunoMapper::toSimpleAlunoDTO)
                .toList();
    }
    
    @Override
    @Transactional
    public AlunoNapneOutputDTO converterAlunoParaNapne(Long alunoId) {
        var aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno not found"));
        
        AlunoNapneEntity alunoNapne = new AlunoNapneEntity();
        alunoNapne.setNome(aluno.getNome());
        alunoNapne.setLogin(aluno.getLogin());
        alunoNapne.setSenha(aluno.getSenha());
        alunoNapne.setMatricula(aluno.getMatricula());
        alunoNapne.setCurso(aluno.getCurso());
        alunoNapne.setConfigAcessibilidadeEntity(aluno.getConfigAcessibilidadeEntity());
        
        // Campos específicos do AlunoNapne precisam ser definidos
        alunoNapne.setCondicao("");
        alunoNapne.setNecessidadeEspecial("");
        alunoNapne.setNecessidadeEscolar("");
        alunoNapne.setAcompanhamento("");
        alunoNapne.setSituacao("");
        
        alunoRepository.deleteById(alunoId);
        return alunoNapneMapper.toAlunoNapneOutputDTO(alunoNapneRepository.save(alunoNapne));
    }
    
    @Override
    @Transactional
    public AlunoOutputDTO converterNapneParaAluno(Long alunoNapneId) {
        var alunoNapne = alunoNapneRepository.findById(alunoNapneId)
                .orElseThrow(() -> new ResourceNotFoundException("AlunoNapne not found"));
        
        AlunoEntity aluno = new AlunoEntity();
        aluno.setNome(alunoNapne.getNome());
        aluno.setLogin(alunoNapne.getLogin());
        aluno.setSenha(alunoNapne.getSenha());
        aluno.setMatricula(alunoNapne.getMatricula());
        aluno.setCurso(alunoNapne.getCurso());
        aluno.setConfigAcessibilidadeEntity(alunoNapne.getConfigAcessibilidadeEntity());
        
        alunoNapneRepository.deleteById(alunoNapneId);
        return alunoMapper.toAlunoOutputDTO(alunoRepository.save(aluno));
    }
    
    @Override
    @Transactional
    public AlunoNapneOutputDTO editarAlunoNapne(Long id, AlunoNapneUpdateDTO dto) {
        var entity = alunoNapneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AlunoNapne not found"));
        alunoNapneMapper.updateAlunoNapneEntiryFromDTO(dto, entity);
        return alunoNapneMapper.toAlunoNapneOutputDTO(alunoNapneRepository.save(entity));
    }
}
