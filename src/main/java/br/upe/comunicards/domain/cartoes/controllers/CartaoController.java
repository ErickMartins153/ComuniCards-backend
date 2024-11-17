package br.upe.comunicards.domain.cartoes.controllers;

import br.upe.comunicards.domain.cartoes.models.Cartao;
import br.upe.comunicards.domain.cartoes.models.DTOs.CartaoDTO;
import br.upe.comunicards.domain.cartoes.services.CartaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("api/cartoes")
@AllArgsConstructor
public class CartaoController {
    CartaoService cartaoService;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping()
    public ResponseEntity<List<CartaoDTO>> getCartoes( @RequestHeader("Usuario-Id") UUID usuarioId) {
        return ResponseEntity.ok().body(cartaoService.getAll(usuarioId));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{id}")
    public ResponseEntity<CartaoDTO> getCartaoById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok().body(cartaoService.getById(id));

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping()
    public ResponseEntity<CartaoDTO> createCartao(@RequestBody CartaoDTO cartaoDTO) {

        return ResponseEntity.ok().body(cartaoService.create(cartaoDTO));
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/{id}")
    public ResponseEntity<CartaoDTO> updateCartao(@PathVariable UUID id, @RequestBody CartaoDTO cartaoDTO) {
        try {

            cartaoService.update(cartaoDTO, id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCartao(@PathVariable UUID id, @RequestHeader("Usuario-Id") UUID usuarioId) {
        try {
            cartaoService.delete(id, usuarioId);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/{id}/favoritar")
    public ResponseEntity<?> favoritarCartao(
            @PathVariable UUID id,
            @RequestHeader("Usuario-Id") UUID usuarioId) {
        try {
            System.out.println(id);
            System.out.println(usuarioId);
            cartaoService.favoritarCartao(usuarioId, id);
            return ResponseEntity.ok().body("Cartão favoritado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
