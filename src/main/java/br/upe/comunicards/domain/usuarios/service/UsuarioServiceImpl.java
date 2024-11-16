package br.upe.comunicards.domain.usuarios.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upe.comunicards.domain.usuarios.models.Usuario;
import br.upe.comunicards.domain.usuarios.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario salvar(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new RuntimeException("Email já cadastrado.");
        }
        if (usuarioRepository.save(usuario) == null) {
            throw new RuntimeException("Erro ao salvar usuário.");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        if (usuarioRepository.findByEmail(email) == null) {
            return null;
        }
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> getAll() {
    List<Usuario> usuarios = usuarioRepository.findAll();
    if (usuarios.isEmpty()) {
        return new ArrayList<>();
    }
    return usuarios;
}

    @Override
    public Usuario getById(UUID id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado.");
        }
        return usuarioRepository.findById(id).get();
    }
    
    
}
