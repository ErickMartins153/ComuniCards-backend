package br.upe.comunicards.domain.cartoes.models;

import br.upe.comunicards.domain.cartoes.models.enums.Categoria;
import br.upe.comunicards.domain.usuarios.models.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cartoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Column(name = "frase", nullable = false)
    private String frase;

    @Column(name = "url_imagem", nullable = false)
    private String urlImagem;

    @Column(name = "is_base", nullable = false)
    private boolean isBase;

    @Column(name = "is_favorito", nullable = false)
    private boolean isFavorito;

    @ManyToOne
    @JoinColumn(name = "criador_id", nullable = false)
    private Usuario criador;

    @ManyToMany(mappedBy = "favoritos")
    @JsonBackReference
    private Set<Usuario> favoritados;

    public boolean isFavorito() {
        return isFavorito;
    }

    public void setIsFavorito(boolean isFavorito) {
        this.isFavorito = isFavorito;
    }

}
