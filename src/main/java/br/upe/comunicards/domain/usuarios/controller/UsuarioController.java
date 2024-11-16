package br.upe.comunicards.domain.usuarios.controller;

import br.upe.comunicards.domain.usuarios.models.Usuario;
import br.upe.comunicards.domain.usuarios.service.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Listar todos os usuários
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok().body(usuarioService.getAll());
    }

    // Buscar usuário por ID (Agora usando UUID)
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);  // Conversão de String para UUID
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
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioExistente = usuarioService.buscarPorEmail(usuario.getEmail());
            if (usuarioExistente != null) {
                return ResponseEntity.status(409).body(null);
            }

            Usuario novoUsuario = usuarioService.salvar(usuario);
            return ResponseEntity.ok().body(novoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); 
        }
    }

    // Login de usuário
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        Usuario usuarioEncontrado = usuarioService.buscarPorEmail(usuario.getEmail());
        if (usuarioEncontrado != null && usuarioEncontrado.getSenha().equals(usuario.getSenha())) {
            return ResponseEntity.ok().body("Login efetuado com sucesso.");
        } else {
            return ResponseEntity.status(401).body("Email ou senha inválidos.");
        }
    }
}
