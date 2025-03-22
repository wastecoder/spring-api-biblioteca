package spring.api.biblioteca.services;

import org.springframework.stereotype.Service;
import spring.api.biblioteca.entities.Livro;
import spring.api.biblioteca.repositories.LivroRepository;

import java.util.List;

@Service
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }


    public Livro salvarLivro(Livro livroNovo) {
        return livroRepository.save(livroNovo);
    }

    public List<Livro> todosLivros() {
        return livroRepository.findAll();
    }

    public Livro buscarLivroId(Long id) {
        return livroRepository.findById(id).orElse(null);
    }

    public boolean deletarLivroId(Long id) {
        if (buscarLivroId(id) == null) return false;

        livroRepository.deleteById(id);
        return true;
    }

    public Livro atualizarLivro(Long id, Livro novo) {
        Livro antigo = this.buscarLivroId(id);
        if (antigo == null) return null;

        antigo.setTitulo(novo.getTitulo());
        antigo.setAnoPublicacao(novo.getAnoPublicacao());
        antigo.setDisponivel(novo.getDisponivel());
        antigo.setAutor(novo.getAutor());

        return livroRepository.save(antigo);
    }
}
