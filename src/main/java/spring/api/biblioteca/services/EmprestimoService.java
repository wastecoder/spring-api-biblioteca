package spring.api.biblioteca.services;

import org.springframework.stereotype.Service;
import spring.api.biblioteca.entities.Cliente;
import spring.api.biblioteca.entities.Emprestimo;
import spring.api.biblioteca.entities.Livro;
import spring.api.biblioteca.repositories.EmprestimoRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final LivroService livroService;
    private final ClienteService clienteService;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroService livroService, ClienteService clienteService) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroService = livroService;
        this.clienteService = clienteService;
    }

    public Emprestimo salvarEmprestimo(Emprestimo emprestimoNovo) {
        Livro livroBuscado = livroService.buscarLivroId(emprestimoNovo.getLivro().getId());
        Cliente clienteBuscado = clienteService.buscarClienteId(emprestimoNovo.getCliente().getId());

        if (livroBuscado == null || clienteBuscado == null || !livroBuscado.getDisponivel()) {
            return null;
        }

        livroBuscado.setDisponivel(false);
        livroService.salvarLivro(livroBuscado);

        return emprestimoRepository.save(emprestimoNovo);
    }

    public List<Emprestimo> todosEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo buscarEmprestimoId(Long id) {
        return emprestimoRepository.findById(id).orElse(null);
    }

    public boolean deletarEmprestimoId(Long id) {
        if (buscarEmprestimoId(id) == null) return false;

        emprestimoRepository.deleteById(id);
        return true;
    }

    public Emprestimo atualizarEmprestimo(Long id, Emprestimo novo) {
        Emprestimo antigo = this.buscarEmprestimoId(id);
        if (antigo == null) return null;

        // Dá para lançar exceção aqui ou apenas não atualizar no setLivro()
        Livro livroNovo = livroService.buscarLivroId(novo.getLivro().getId());
        if (livroNovo == null)
            throw new NoSuchElementException("ID do livro informado não encontrado.");

        Cliente clienteNovo = clienteService.buscarClienteId(novo.getCliente().getId());
        if (clienteNovo == null)
            throw new NoSuchElementException("ID do cliente não encontrado.");

        antigo.setDataEmprestimo(novo.getDataEmprestimo());
        antigo.setDataDevolucao(novo.getDataDevolucao());
        antigo.setDevolvido(novo.getDevolvido());
        antigo.setLivro(livroNovo);
        antigo.setCliente(clienteNovo);

        // Se o empréstimo foi atualizado para devolvido, marca o livro como disponível - considera como apenas um livro na biblioteca
        if (novo.getDevolvido() && !antigo.getDevolvido()) {
            livroNovo.setDisponivel(true);
            livroService.salvarLivro(livroNovo);
        }

        return emprestimoRepository.save(antigo);
    }
}
