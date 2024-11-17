package br.upe.comunicards.domain.usuarios.models;

import br.upe.comunicards.domain.cartoes.models.Cartao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "foto")
    private String foto;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable (
            name = "usuarios_cartoes",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "cartao_id")
    )
    @JsonIgnore
    private Set<Cartao> favoritos = new HashSet<>();

    public void setFavoritos(Set<Cartao> novosFavoritos) {
        this.favoritos.addAll(novosFavoritos);
    }

    public void addFavorito(Cartao novoFavorito) {
        this.favoritos.add(novoFavorito);
    }
}
