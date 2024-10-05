package br.upe.comunicards.domain.cartoes.services;

import br.upe.comunicards.domain.cartoes.models.Cartao;
import br.upe.comunicards.domain.cartoes.models.DTOs.CartaoDTO;

import br.upe.comunicards.domain.cartoes.repositories.CartaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartaoServiceImpl implements CartaoService {
    CartaoRepository cartaoRepository;

    @Autowired
    public CartaoServiceImpl(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

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
    public void delete(UUID id) {
        if (!cartaoRepository.existsById(id)) {
            throw new RuntimeException("Não existe cartão cadastrado com o id " + id);
        }
        cartaoRepository.deleteById(id);
    }
}
