package br.upe.comunicards.domain.usuarios.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.upe.comunicards.domain.usuarios.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Usuario findByEmail(String email);
    Optional<Usuario> findById(UUID id);
    boolean existsById(UUID id);
    
}
