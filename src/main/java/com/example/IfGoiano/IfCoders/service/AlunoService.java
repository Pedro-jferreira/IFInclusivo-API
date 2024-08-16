package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.Aluno;
import com.example.IfGoiano.IfCoders.model.Usuario;
import com.example.IfGoiano.IfCoders.repository.AlunoRepository;
import com.example.IfGoiano.IfCoders.service.Exception.DataBaseException;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> findAll() {
        try {
            return alunoRepository.findAll();
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching all alunos: " + e);
        }
    }

    public Aluno findById(Long id) {
        try {
            Optional<Aluno> aluno = alunoRepository.findById(id);
            return aluno.orElseThrow(() -> new ResourceNotFoundException(id));
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching user: " + e);
        }
    }

    @Transactional
    public Aluno save(Aluno aluno) {
        try {
            return alunoRepository.save(aluno);
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while saving new aluno: " + e);
        }
    }

    @Transactional
    public Aluno update(Long id, Aluno alunoDetails) {
        try {
            Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            updateAlunoDetails(aluno, alunoDetails);
            return alunoRepository.save(aluno);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the aluno: " + e);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            Aluno aluno = findById(id);
            alunoRepository.delete(aluno);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the aluno: " + e);
        }
    }

    public void updateAlunoDetails (Aluno aluno, Aluno alunoDetails){
        aluno.setNome(alunoDetails.getNome());
        aluno.setLogin(alunoDetails.getLogin());
        aluno.setSenha(alunoDetails.getSenha());
        aluno.setMatricula(alunoDetails.getMatricula());
        aluno.setBiografia(alunoDetails.getBiografia());
        aluno.setConfigAcessibilidade(alunoDetails.getConfigAcessibilidade());
    }
}
