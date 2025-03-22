package spring.api.biblioteca.services;

import org.springframework.stereotype.Service;
import spring.api.biblioteca.entities.Autor;
import spring.api.biblioteca.repositories.AutorRepository;

import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }


    public Autor salvarAutor(Autor autorNovo) {
        return autorRepository.save(autorNovo);
    }

    public List<Autor> todosAutores() {
        return autorRepository.findAll();
    }

    public Autor buscarAutorId(Long id) {
        return autorRepository.findById(id).orElse(null);
    }

    public boolean deletarAutorId(Long id) {
        if (buscarAutorId(id) == null) return false;

        autorRepository.deleteById(id);
        return true;
    }

    public Autor atualizarAutor(Long id, Autor novo) {
        Autor antigo = this.buscarAutorId(id);
        if (antigo == null) return null;

        antigo.setNome(novo.getNome());

        return autorRepository.save(antigo);
    }
}
