package br.upe.comunicards.domain.usuarios.models.DTOs;

import br.upe.comunicards.domain.usuarios.models.Usuario;
import lombok.Builder;

@Builder
public record UsuarioDTO(
        String nome,    
        String email,
        String senha
) {
    public Usuario toUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setNome(nome);
        return usuario;
    }

    public static UsuarioDTO from(Usuario usuario) {
        return new UsuarioDTO(usuario.getEmail(), usuario.getSenha(), usuario.getNome());
    }
}
