package spring.api.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String titulo;

    private Integer anoPublicacao;

    private Boolean disponivel;

    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;
}
