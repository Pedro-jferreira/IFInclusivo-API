package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
import com.example.IfGoiano.IfCoders.repository.AlunoRepository;
import com.example.IfGoiano.IfCoders.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl  implements AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<AlunoEntity> findAll() {
        return alunoRepository.findAll();
    }

    public AlunoEntity findById(Long id) {
        Optional<AlunoEntity> aluno = alunoRepository.findById(id);
        return aluno.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public AlunoEntity save(AlunoEntity aluno) {
        return alunoRepository.save(aluno);
    }

    @Transactional
    public AlunoEntity update(Long id, AlunoEntity alunoDetails) {
        AlunoEntity aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        updateAlunoDetails(aluno, alunoDetails);
        return alunoRepository.save(aluno);
    }

    @Transactional
    public void delete(Long id) {
        AlunoEntity aluno = findById(id);
        alunoRepository.delete(aluno);
    }

    private void updateAlunoDetails (AlunoEntity aluno, AlunoEntity alunoDetails){
        aluno.setNome(alunoDetails.getNome());
        aluno.setLogin(alunoDetails.getLogin());
        aluno.setSenha(alunoDetails.getSenha());
        aluno.setMatricula(alunoDetails.getMatricula());
        aluno.setBiografia(alunoDetails.getBiografia());
        aluno.setConfigAcessibilidade(alunoDetails.getConfigAcessibilidade());
    }
}
