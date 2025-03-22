package spring.api.biblioteca.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.api.biblioteca.dtos.AutorDto;
import spring.api.biblioteca.entities.Autor;
import spring.api.biblioteca.services.AutorService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/autores")
public class AutorController {
    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }


    @GetMapping
    public ResponseEntity<List<Autor>> todosAutores() {
        return ResponseEntity.ok().body(autorService.todosAutores());
    }

    @PostMapping
    public ResponseEntity<Autor> criarAutor(@RequestBody @Valid AutorDto novoAutor) {
        Autor autorSalvo = autorService.salvarAutor(novoAutor.dtoParaAutor());
        return ResponseEntity.status(HttpStatus.CREATED).body(autorSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAutor(@PathVariable Long id) {
        Autor autorRetornado = autorService.buscarAutorId(id);

        if (autorRetornado == null)
            return retornarAutorNaoEncontrado();

        return ResponseEntity.ok().body(autorRetornado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody @Valid AutorDto autorAtualizado) {
        Autor retorno = autorService.atualizarAutor(id, autorAtualizado.dtoParaAutor());

        if (retorno == null) return retornarAutorNaoEncontrado();

        return ResponseEntity.ok(retorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(Long id) {
        if (autorService.deletarAutorId(id)) {
            return ResponseEntity.noContent().build();
        }

        return retornarAutorNaoEncontrado();
    }


    private ResponseEntity<?> retornarAutorNaoEncontrado() {
        Map<String, String> erro = Map.of("erro", "Autor não encontrado.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
