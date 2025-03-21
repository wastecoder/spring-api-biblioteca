package spring.api.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.api.biblioteca.entities.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
