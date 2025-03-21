package spring.api.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;
}
