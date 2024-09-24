package br.upe.comunicards.domain.cartoes.models;

import br.upe.comunicards.domain.cartoes.models.enums.Categoria;
import br.upe.comunicards.domain.usuarios.models.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cartoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter @ToString @EqualsAndHashCode
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "url_audio", nullable = false)
    private String urlAudio;

    @ManyToMany(mappedBy = "favoritos")
    Set<Usuario> favoritados;
}