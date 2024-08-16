package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.model.Usuario;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Usuario saveUsuario(Usuario usuario) {
        // Implementar lógica de validação, se necessário
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setLogin(usuarioAtualizado.getLogin());
        usuario.setSenha(usuarioAtualizado.getSenha());
        usuario.setMatricula(usuarioAtualizado.getMatricula());
        usuario.setBiografia(usuarioAtualizado.getBiografia());
        usuario.setConfigAcessibilidade(usuarioAtualizado.getConfigAcessibilidade());

        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        usuarioRepository.deleteById(id);
    }
}
