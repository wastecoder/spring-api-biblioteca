package spring.api.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 11)
    private String telefone;
}
