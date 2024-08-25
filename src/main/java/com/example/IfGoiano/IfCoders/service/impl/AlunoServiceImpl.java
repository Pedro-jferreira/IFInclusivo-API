package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
import com.example.IfGoiano.IfCoders.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<AlunoEntity> findAll() {
        try {
            return alunoRepository.findAll();
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching all alunos: " + e);
        }
    }

    public AlunoEntity findById(Long id) {
        try {
            Optional<AlunoEntity> aluno = alunoRepository.findById(id);
            return aluno.orElseThrow(() -> new ResourceNotFoundException(id));
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching user: " + e);
        }
    }

    @Transactional
    public AlunoEntity save(AlunoEntity aluno) {
        try {
            return alunoRepository.save(aluno);
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while saving new aluno: " + e);
        }
    }

    @Transactional
    public AlunoEntity update(Long id, AlunoEntity alunoDetails) {
        try {
            AlunoEntity aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            updateAlunoDetails(aluno, alunoDetails);
            return alunoRepository.save(aluno);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the aluno: " + e);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            AlunoEntity aluno = findById(id);
            alunoRepository.delete(aluno);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the aluno: " + e);
        }
    }

    public void updateAlunoDetails (AlunoEntity aluno, AlunoEntity alunoDetails){
        aluno.setNome(alunoDetails.getNome());
        aluno.setLogin(alunoDetails.getLogin());
        aluno.setSenha(alunoDetails.getSenha());
        aluno.setMatricula(alunoDetails.getMatricula());
        aluno.setBiografia(alunoDetails.getBiografia());
      
    }
}
