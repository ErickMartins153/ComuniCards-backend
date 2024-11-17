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
        String fotoUrl,
        String nome,
        String email,
        String senha,
        Set<UUID> favoritosIds
) {

    public Usuario toUsuario(String senha) {
        Usuario usuario = new Usuario();
        usuario.setFotoUrl(fotoUrl);
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuario;
    }

    public static UsuarioDTO from(Usuario usuario) {
        Set<UUID> favoritosIds = usuario.getFavoritos().stream()
                .map(Cartao::getId)
                .collect(Collectors.toSet());
        return new UsuarioDTO(usuario.getId(), usuario.getFotoUrl(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), favoritosIds);
    }
}

