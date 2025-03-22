package spring.api.biblioteca.services;

import org.springframework.stereotype.Service;
import spring.api.biblioteca.entities.Cliente;
import spring.api.biblioteca.repositories.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> todosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClienteId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public boolean deletarClienteId(Long id) {
        if (buscarClienteId(id) == null) return false;

        clienteRepository.deleteById(id);
        return true;
    }

    public Cliente atualizarCliente(Long id, Cliente novo) {
        Cliente antigo = this.buscarClienteId(id);
        if (antigo == null) return null;

        antigo.setNome(novo.getNome());
        antigo.setEmail(novo.getEmail());
        antigo.setTelefone(novo.getTelefone());

        return clienteRepository.save(antigo);
    }
}
