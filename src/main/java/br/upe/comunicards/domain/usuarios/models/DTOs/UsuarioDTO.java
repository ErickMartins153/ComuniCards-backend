package br.upe.comunicards.domain.usuarios.models.DTOs;

import br.upe.comunicards.domain.usuarios.models.Usuario;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UsuarioDTO(
        UUID id,
        String nome,
        String email,
        String senha
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
        return new UsuarioDTO(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getNome());
    }
}
