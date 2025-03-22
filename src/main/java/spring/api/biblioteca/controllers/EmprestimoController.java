package spring.api.biblioteca.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.api.biblioteca.dtos.EmprestimoDto;
import spring.api.biblioteca.entities.Emprestimo;
import spring.api.biblioteca.services.EmprestimoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> todosEmprestimos() {
        return ResponseEntity.ok().body(emprestimoService.todosEmprestimos());
    }

    @PostMapping
    public ResponseEntity<?> criarEmprestimo(@RequestBody @Valid EmprestimoDto emprestimoDto) {
        Emprestimo emprestimo = emprestimoDto.dtoParaEmprestimo();
        Emprestimo salvo = emprestimoService.salvarEmprestimo(emprestimo);

        if (salvo == null) {
            return ResponseEntity.badRequest().body("Erro: Cliente ou Livro inválido, ou livro indisponível.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEmprestimo(@PathVariable Long id) {
        Emprestimo emprestimoRetornado = emprestimoService.buscarEmprestimoId(id);
        if (emprestimoRetornado == null) return retornarEntidadeNaoEncontrada();

        return ResponseEntity.ok().body(emprestimoRetornado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEmprestimo(@PathVariable Long id, @RequestBody @Valid EmprestimoDto emprestimoAtualizado) {
        Emprestimo emprestimoBuscado = emprestimoService.atualizarEmprestimo(id, emprestimoAtualizado.dtoParaEmprestimo());

        if (emprestimoBuscado == null) retornarEntidadeNaoEncontrada();

        return ResponseEntity.ok(emprestimoBuscado);
    }


    private ResponseEntity<?> retornarEntidadeNaoEncontrada() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", "ID do empréstimo não encontrado."));
    }
}
