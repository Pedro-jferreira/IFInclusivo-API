package com.example.IfGoiano.IfCoders.service;


import com.example.IfGoiano.IfCoders.model.Curso;
import com.example.IfGoiano.IfCoders.repository.CursoRepository;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CursoService {
    private final CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository usuarioRepository) {
        this.cursoRepository = usuarioRepository;
    }

    public List<Curso> findAllCursos() {
        return cursoRepository.findAll();
    }

    public Curso findCursoById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Curso saveCurso(Curso curso) {
        // Implementar lógica de validação, se necessário
        return cursoRepository.save(curso);
    }

    public Curso updateCurso(Long id, Curso cursoAtualizado) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        curso.setNome(cursoAtualizado.getNome());

        return cursoRepository.save(curso);
    }

    public void deleteUsuario(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        cursoRepository.deleteById(id);
    }
}
