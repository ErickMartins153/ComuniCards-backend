package br.upe.comunicards.domain.usuarios.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upe.comunicards.domain.usuarios.models.Usuario;
import br.upe.comunicards.domain.usuarios.models.DTOs.UsuarioDTO;
import br.upe.comunicards.domain.usuarios.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario create(UsuarioDTO usuario) {
        Usuario novoUsuario = usuario.toUsuario();
        return usuarioRepository.save(novoUsuario);
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




    public Usuario update(UsuarioDTO usuarioDTO, UUID id) {
        Usuario usuario = getById(id);
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setNome(usuarioDTO.nome());
        return usuarioRepository.save(usuario);
    }

    public void delete(UUID id) {
        Usuario usuario = getById(id);
        usuarioRepository.delete(usuario);
    }

    
    
}
