package br.upe.comunicards.domain.cartoes.models.DTOs;

import br.upe.comunicards.domain.cartoes.models.Cartao;
import br.upe.comunicards.domain.cartoes.models.enums.Categoria;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;


@Builder
public record CartaoDTO(
        @NotNull
        String titulo,
        @NotNull
        String categoria,
        @NotNull
        String frase,
        @NotNull
        String urlImagem,
        @NotNull
        boolean isBase,
        @NotNull
        UUID criadorId,
        boolean isFavorito
) {

    public static CartaoDTO from(Cartao cartao) {
        return new CartaoDTO(cartao.getTitulo(),
                cartao.getCategoria().name(), cartao.getFrase(), cartao.getUrlImagem(), cartao.isBase(), cartao.getCriador().getId(), cartao.isFavorito());
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
        cartao.setFrase(frase);
        cartao.setUrlImagem(urlImagem());
        cartao.setBase(isBase);
        return cartao;
    }
}