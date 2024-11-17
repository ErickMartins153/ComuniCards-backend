package br.upe.comunicards.domain.usuarios.services;

import br.upe.comunicards.domain.cartoes.models.Cartao;
import br.upe.comunicards.domain.usuarios.models.Usuario;
import br.upe.comunicards.domain.usuarios.models.DTOs.UsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public interface UsuarioService {
    Usuario create(UsuarioDTO usuarioDTO);

    Usuario buscarPorEmail(String email);

    List<Usuario> getAll();

    Usuario getById(UUID id);

    Usuario update(UsuarioDTO usuarioDTO, UUID id);

    void delete(UUID id);

    Set<Cartao> getFavoritos(UUID usuarioId);


}
