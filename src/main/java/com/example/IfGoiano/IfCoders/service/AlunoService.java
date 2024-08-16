package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.Aluno;
import com.example.IfGoiano.IfCoders.repository.AlunoRepository;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    // Método para buscar todos os alunos
    public List<Aluno> findAllAlunos() {
        return alunoRepository.findAll();
    }

    // Método para buscar um aluno por ID
    public Aluno findAlunoById(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // Método para salvar um aluno
    public Aluno saveAluno(Aluno aluno) {
        // Implementar lógica de validação, se necessário
        return alunoRepository.save(aluno);
    }

    // Método para atualizar um aluno
    public Aluno updateAluno(Long id, Aluno alunoAtualizado) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        // Atualizar os campos do aluno com os valores do alunoAtualizado
        aluno.setNome(alunoAtualizado.getNome());
        aluno.setLogin(alunoAtualizado.getLogin());
        aluno.setSenha(alunoAtualizado.getSenha());
        aluno.setMatricula(alunoAtualizado.getMatricula());
        aluno.setBiografia(alunoAtualizado.getBiografia());
        aluno.setConfigAcessibilidade(alunoAtualizado.getConfigAcessibilidade());
        aluno.setCurso(alunoAtualizado.getCurso());

        // Salvar e retornar o aluno atualizado
        return alunoRepository.save(aluno);
    }

    // Método para deletar um aluno por ID
    public void deleteAluno(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        alunoRepository.deleteById(id);
    }
}
