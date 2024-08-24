package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import com.example.IfGoiano.IfCoders.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioEntity> findAll() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity findById(Long id) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public UsuarioEntity save(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public UsuarioEntity update(Long id, UsuarioEntity usuarioDetails) {
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        updateUsuarioDetails(usuario, usuarioDetails);
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void delete(Long id) {
        UsuarioEntity usuario = findById(id);
        usuarioRepository.delete(usuario);
    }

    private void updateUsuarioDetails (UsuarioEntity usuario, UsuarioEntity usuarioDetails){
        usuario.setNome(usuarioDetails.getNome());
        usuario.setLogin(usuarioDetails.getLogin());
        usuario.setSenha(usuarioDetails.getSenha());
        usuario.setMatricula(usuarioDetails.getMatricula());
        usuario.setBiografia(usuarioDetails.getBiografia());
        usuario.setConfigAcessibilidade(usuarioDetails.getConfigAcessibilidade());
    }
}
