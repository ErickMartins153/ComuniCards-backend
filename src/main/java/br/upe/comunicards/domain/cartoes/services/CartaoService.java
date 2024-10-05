package br.upe.comunicards.domain.cartoes.services;

import br.upe.comunicards.domain.cartoes.models.Cartao;
import br.upe.comunicards.domain.cartoes.models.DTOs.CartaoDTO;

import java.util.List;
import java.util.UUID;

public interface CartaoService {

    public List<Cartao> getAll();

    public Cartao getById(UUID id);

    public Cartao create(CartaoDTO cartao);

    public Cartao update(CartaoDTO cartao, UUID id);

    public void delete(UUID id);
}
