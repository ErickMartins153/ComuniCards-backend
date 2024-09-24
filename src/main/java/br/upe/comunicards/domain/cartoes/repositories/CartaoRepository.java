package br.upe.comunicards.domain.cartoes.repositories;

import br.upe.comunicards.domain.cartoes.models.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartaoRepository extends JpaRepository<Cartao, UUID> {
}
