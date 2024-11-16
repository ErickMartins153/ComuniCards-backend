package br.upe.comunicards.domain.usuarios.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.upe.comunicards.domain.usuarios.models.Usuario;

@Service
public interface UsuarioService {
    public Usuario salvar(Usuario usuario);
    public Usuario buscarPorEmail(String email);
    public List<Usuario> getAll();
    public Usuario getById(UUID id);
}
