package br.upe.comunicards.domain.usuarios.controllers;

import br.upe.comunicards.domain.cartoes.models.Cartao;
import br.upe.comunicards.domain.cartoes.models.DTOs.CartaoDTO;
import br.upe.comunicards.domain.usuarios.models.DTOs.Credentials;
import br.upe.comunicards.domain.usuarios.models.Usuario;
import br.upe.comunicards.domain.usuarios.models.DTOs.UsuarioDTO;
import br.upe.comunicards.domain.usuarios.services.UsuarioService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/usuarios")
@AllArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    // Listar todos os usuários
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok().body(usuarioService.getAll());
    }

    // Buscar usuário por ID
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Usuario usuario = usuarioService.getById(uuid);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
            }
            return ResponseEntity.ok(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID inválido. O ID deve ser um UUID válido.");
        }
    }

    // Cadastro de usuário
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuarioExistente = usuarioService.buscarPorEmail(usuarioDTO.email());
            if (usuarioExistente != null) {
                return ResponseEntity.status(409).body(null);
            }

            Usuario novoUsuario = usuarioService.create(usuarioDTO);
            return ResponseEntity.ok().body(novoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Login de usuário
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials usuario) {
        Usuario usuarioEncontrado = usuarioService.buscarPorEmail(usuario.email());
        if (usuarioEncontrado != null && usuarioEncontrado.getSenha().equals(usuario.senha())) {
            return ResponseEntity.ok().body(UsuarioDTO.from(usuarioEncontrado));
        } else {
            return ResponseEntity.status(401).body("Email ou senha inválidos.");
        }
    }

    // Atualizar usuário
    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable UUID id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            return ResponseEntity.ok(usuarioService.update(usuarioDTO, id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar usuário
    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable UUID id) {
        try {
            usuarioService.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{usuarioId}/favoritos")
    public ResponseEntity<?> getFavoritos(
            @PathVariable UUID usuarioId) {
        try {
            Set<CartaoDTO> favoritos = usuarioService.getFavoritos(usuarioId);
            return ResponseEntity.ok().body(favoritos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}
