package spring.api.biblioteca.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.api.biblioteca.dtos.LivroDto;
import spring.api.biblioteca.entities.Autor;
import spring.api.biblioteca.entities.Livro;
import spring.api.biblioteca.services.AutorService;
import spring.api.biblioteca.services.LivroService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroService livroService;
    private final AutorService autorService;

    public LivroController(LivroService livroService, AutorService autorService) {
        this.livroService = livroService;
        this.autorService = autorService;
    }


    @GetMapping
    public ResponseEntity<List<Livro>> todosLivros() {
        return ResponseEntity.ok().body(livroService.todosLivros());
    }

    @PostMapping
    public ResponseEntity<?> criarLivro(@RequestBody @Valid LivroDto novoLivro) {
        Autor autorRetornado = autorService.buscarAutorId(novoLivro.getAutorId());
        if (autorRetornado == null) return retornarEntidadeNaoEncontrada("autor");

        Livro livroSalvo = livroService.salvarLivro(novoLivro.dtoParaLivro(autorRetornado));
        return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarLivro(@PathVariable Long id) {
        Livro livroRetornado = livroService.buscarLivroId(id);

        if (livroRetornado == null) return retornarEntidadeNaoEncontrada("livro");

        return ResponseEntity.ok().body(livroRetornado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarLivro(@PathVariable Long id, @RequestBody @Valid LivroDto livroAtualizado) {
        Autor autorRetornado = autorService.buscarAutorId(livroAtualizado.getAutorId());
        if (autorRetornado == null) return retornarEntidadeNaoEncontrada("autor");

        Livro livroBuscado = livroService.atualizarLivro(id, livroAtualizado.dtoParaLivro(autorRetornado));

        if (livroBuscado == null) return retornarEntidadeNaoEncontrada("livro");

        return ResponseEntity.ok(livroBuscado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLivro(Long id) {
        if (livroService.deletarLivroId(id)) {
            return ResponseEntity.noContent().build();
        }

        return retornarEntidadeNaoEncontrada("livro");
    }


    private ResponseEntity<?> retornarEntidadeNaoEncontrada(String entidade) {
        Map<String, String> erro = Map.of("erro", "ID do " + entidade + " não encontrado.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
