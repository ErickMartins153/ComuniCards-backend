package br.upe.comunicards.domain.usuarios.services;

import br.upe.comunicards.domain.usuarios.models.Usuario;
import br.upe.comunicards.domain.usuarios.models.DTOs.UsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UsuarioService {
    public Usuario create(UsuarioDTO usuarioDTO);
    public Usuario buscarPorEmail(String email);
    public List<Usuario> getAll();
    public Usuario getById(UUID id);
    public Usuario update(UsuarioDTO usuarioDTO, UUID id);
    public void delete(UUID id);


}
