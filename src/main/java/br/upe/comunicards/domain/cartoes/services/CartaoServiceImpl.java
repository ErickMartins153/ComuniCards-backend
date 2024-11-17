package br.upe.comunicards.domain.cartoes.services;

import br.upe.comunicards.domain.cartoes.models.Cartao;
import br.upe.comunicards.domain.cartoes.models.DTOs.CartaoDTO;

import br.upe.comunicards.domain.cartoes.repositories.CartaoRepository;
import br.upe.comunicards.domain.usuarios.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Cartao> getAll() {
        return cartaoRepository.findAll();
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
}
