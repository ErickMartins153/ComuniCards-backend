package br.upe.comunicards.domain.usuarios.services;

import br.upe.comunicards.domain.usuarios.models.Usuario;
import br.upe.comunicards.domain.usuarios.models.DTOs.UsuarioDTO;
import br.upe.comunicards.domain.usuarios.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getById(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    public Usuario create(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioDTO.toUsuario();
        return usuarioRepository.save(usuario);
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
