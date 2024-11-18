package br.upe.comunicards.domain.usuarios.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import br.upe.comunicards.domain.cartoes.models.DTOs.CartaoDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.upe.comunicards.domain.usuarios.models.Usuario;
import br.upe.comunicards.domain.usuarios.models.DTOs.UsuarioDTO;
import br.upe.comunicards.domain.usuarios.repositories.UsuarioRepository;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario create(UsuarioDTO usuario) {
        Usuario novoUsuario = usuario.toUsuario(usuario.senha());
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


    public UsuarioDTO update(UsuarioDTO usuarioDTO, UUID id) {
        Usuario usuario = getById(id);
        usuario.setEmail(usuarioDTO.email());
        usuario.setFoto(usuarioDTO.foto());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setNome(usuarioDTO.nome());
        return UsuarioDTO.from(usuarioRepository.save(usuario));
    }

    public void delete(UUID id) {
        Usuario usuario = getById(id);
        usuarioRepository.delete(usuario);
    }

    public Set<CartaoDTO> getFavoritos(UUID usuarioId) {
        Usuario usuario = usuarioRepository.getById(usuarioId);

        return usuario.getFavoritos().stream()
                .map(favorito -> {
                    favorito.setIsFavorito(true);
                    return favorito;
                }).map(CartaoDTO::from)
                .collect(Collectors.toSet());
    }



}
