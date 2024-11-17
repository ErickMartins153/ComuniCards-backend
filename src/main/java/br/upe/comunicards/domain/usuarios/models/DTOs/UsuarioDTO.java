package br.upe.comunicards.domain.usuarios.models.DTOs;

import br.upe.comunicards.domain.usuarios.models.Usuario;
import lombok.Builder;

@Builder
public record UsuarioDTO(
        String nome,
        String email, 
        String senha
) {

    public Usuario toUsuario(String senha) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuario;
    }

    public static UsuarioDTO from(Usuario usuario) {
        return new UsuarioDTO(usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }
}
