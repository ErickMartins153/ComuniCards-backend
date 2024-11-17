package br.upe.comunicards.domain.cartoes.services;

import br.upe.comunicards.domain.cartoes.models.Cartao;
import br.upe.comunicards.domain.cartoes.models.DTOs.CartaoDTO;

import java.util.List;
import java.util.UUID;

public interface CartaoService {

     List<Cartao> getAll(UUID usuarioId);

     Cartao getById(UUID id);

     Cartao create(CartaoDTO cartao);

     Cartao update(CartaoDTO cartao, UUID id);

     void delete(UUID idCartao,  UUID usuarioId);

     void favoritarCartao(UUID usuarioId, UUID cartaoId);


}
