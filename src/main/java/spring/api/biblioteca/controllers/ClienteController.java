package spring.api.biblioteca.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.api.biblioteca.dtos.ClienteDto;
import spring.api.biblioteca.entities.Cliente;
import spring.api.biblioteca.services.ClienteService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @GetMapping
    public ResponseEntity<List<Cliente>> todosClientes() {
        List<Cliente> produtos = clienteService.todosClientes();
        return ResponseEntity.ok().body(produtos);
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid ClienteDto novoCliente) {
        Cliente clienteSalvo = clienteService.salvarCliente(novoCliente.dtoParaCliente());
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
        Cliente clienteRetornado = clienteService.buscarClienteId(id);

        if (clienteRetornado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(clienteRetornado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteDto clienteAtualizado) {
        Cliente retorno = clienteService.atualizarCliente(id, clienteAtualizado.dtoParaCliente());

        if (retorno == null) return retornarClienteNaoEncontrado();

        return ResponseEntity.ok(retorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(Long id) {
        if (clienteService.deletarClienteId(id)) {
            return ResponseEntity.noContent().build();
        }

        return retornarClienteNaoEncontrado();
    }


    private ResponseEntity<?> retornarClienteNaoEncontrado() {
        Map<String, String> erro = Map.of("erro", "Cliente não encontrado.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
