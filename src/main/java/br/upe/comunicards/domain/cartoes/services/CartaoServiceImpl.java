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
    public List<CartaoDTO> getAll(UUID usuarioId) {
        Usuario usuario = usuarioService.getById(usuarioId);
        List<Cartao> cartoes = cartaoRepository.findAll().stream()
                .filter(cartao -> cartao.isBase() || cartao.getCriador().getId().equals(usuarioId))
                .toList();

        cartoes.forEach(cartao -> {
            cartao.setIsFavorito(usuario.getFavoritos().contains(cartao));
        });

        return cartoes.stream().map(CartaoDTO::from).toList();
    }

    @Override
    public CartaoDTO getById(UUID id) {
        Cartao cartao = cartaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Cartão não encontrado"));


        return CartaoDTO.from(cartao);
    }

    @Override
    public CartaoDTO create(CartaoDTO cartaoDTO) {
        Cartao cartao = cartaoDTO.toCartao();
        Usuario usuario = usuarioService.getById(cartaoDTO.criadorId());
        cartao.setCriador(usuario);
        cartao.addFavoritado(usuario);
        Cartao savedCartao = cartaoRepository.save(cartao);
        usuario.addFavorito(savedCartao);
        usuarioService.update(UsuarioDTO.from(usuario), usuario.getId());

        return CartaoDTO.from(savedCartao);
    }

    @Override
    public CartaoDTO update(CartaoDTO cartaoDTO, UUID id) {
        Optional<Cartao> cartaoOptional = cartaoRepository.findById(id);
        if (cartaoOptional.isEmpty()) return null;

        Cartao cartao = cartaoOptional.get();

        BeanUtils.copyProperties(cartaoDTO, cartao, "id");

        return CartaoDTO.from(cartaoRepository.save(cartao));
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
