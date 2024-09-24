package br.upe.comunicards.domain.cartoes.models.DTOs;

import br.upe.comunicards.domain.cartoes.models.Cartao;
import br.upe.comunicards.domain.cartoes.models.enums.Categoria;
import lombok.Builder;


@Builder
public record CartaoDTO(String titulo, String categoria, String descricao, String urlAudio) {

    public static CartaoDTO from(Cartao cartao) {
        return new CartaoDTO(cartao.getTitulo(),
                cartao.getCategoria().name(), cartao.getDescricao(), cartao.getUrlAudio());
    }

    public Cartao toCartao() {
        Cartao cartao = new Cartao();
        cartao.setTitulo(titulo);

        Categoria categoriaEnum;
        try {
            categoriaEnum = Categoria.valueOf(categoria);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Categoria inválida: " + categoria, e);
        }

        cartao.setCategoria(categoriaEnum);
        cartao.setDescricao(descricao);
        cartao.setUrlAudio(urlAudio);
        return cartao;
    }
}