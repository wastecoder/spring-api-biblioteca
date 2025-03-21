package spring.api.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.api.biblioteca.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
