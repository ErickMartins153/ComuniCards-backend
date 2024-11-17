package br.upe.comunicards.domain.cartoes.services;

import br.upe.comunicards.domain.cartoes.models.Cartao;
import br.upe.comunicards.domain.cartoes.models.DTOs.CartaoDTO;

import br.upe.comunicards.domain.cartoes.repositories.CartaoRepository;
import br.upe.comunicards.domain.usuarios.models.DTOs.UsuarioDTO;
import br.upe.comunicards.domain.usuarios.models.Usuario;
import br.upe.comunicards.domain.usuarios.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartaoServiceImpl implements CartaoService {
    CartaoRepository cartaoRepository;

    UsuarioService usuarioService;

    @Override
    public List<Cartao> getAll(UUID usuarioId) {
        List<Cartao> cartoes = cartaoRepository.findAll();

        Usuario usuario = usuarioService.getById(usuarioId);

        cartoes.forEach(cartao -> {
            cartao.setIsFavorito(usuario.getFavoritos().contains(cartao));
        });

        return cartoes;
    }

    @Override
    public Cartao getById(UUID id) {
        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if (cartao.isEmpty()) return null;

        return cartao.get();
    }

    @Override
    public Cartao create(CartaoDTO cartaoDTO) {
        Cartao cartao = cartaoDTO.toCartao();
        cartao.setCriador(usuarioService.getById(cartaoDTO.criadorId()));
        Cartao savedCartao = cartaoRepository.save(cartao);
        return savedCartao;
    }

    @Override
    public Cartao update(CartaoDTO cartaoDTO, UUID id) {
        Optional<Cartao> cartaoOptional = cartaoRepository.findById(id);
        if (cartaoOptional.isEmpty()) return null;

        Cartao cartao = cartaoOptional.get();

        BeanUtils.copyProperties(cartaoDTO, cartao, "id");

        return cartaoRepository.save(cartao);
    }

    @Override
    public void delete(UUID cartaoId, UUID usuarioId) {
        Cartao cartao = cartaoRepository.findById(cartaoId)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

        if (cartao.isBase()) {
            throw new RuntimeException("Cartão base não pode ser deletado");
        }

        if (!cartao.getCriador().getId().equals(usuarioId)) {
            throw new RuntimeException("Você não tem permissão para deletar este cartão");
        }

        cartaoRepository.delete(cartao);
    }

    public void favoritarCartao(UUID usuarioId, UUID cartaoId) {

        Usuario usuario = usuarioService.getById(usuarioId);

        Cartao cartao = cartaoRepository.findById(cartaoId)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

        if (usuario.getFavoritos().contains(cartao)) {
            usuario.getFavoritos().remove(cartao);
        } else {
            usuario.getFavoritos().add(cartao);
        }

        usuarioService.update(UsuarioDTO.from(usuario), usuarioId);
    }


}
