package br.upe.comunicards.domain.usuarios.models.DTOs;

import br.upe.comunicards.domain.cartoes.models.Cartao;
import br.upe.comunicards.domain.usuarios.models.Usuario;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public record UsuarioDTO(
        UUID id,
        String nome,
        String email,
        String senha,
        Set<UUID> favoritosIds
) {
    public Usuario toUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNome(nome);
        return usuario;
    }

    public static UsuarioDTO from(Usuario usuario) {
        Set<UUID> favoritosIds = usuario.getFavoritos().stream()
                .map(Cartao::getId)
                .collect(Collectors.toSet());
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), favoritosIds);
    }
}

