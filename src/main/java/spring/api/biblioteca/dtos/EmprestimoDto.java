package spring.api.biblioteca.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import spring.api.biblioteca.entities.Cliente;
import spring.api.biblioteca.entities.Emprestimo;
import spring.api.biblioteca.entities.Livro;

import java.time.LocalDate;

@Data
public class EmprestimoDto {
    @NotNull(message = "Data de empréstimo não pode ser nula")
    @PastOrPresent(message = "Data de empréstimo deve ser hoje ou uma data passada")
    private LocalDate dataEmprestimo;

    @NotNull(message = "Data de devolução não pode ser nula")
    @FutureOrPresent(message = "Data de devolução deve ser hoje ou uma data futura")
    private LocalDate dataDevolucao;

    @NotNull(message = "O status de devolvido não pode ser nulo")
    private Boolean devolvido;

    @NotNull(message = "Livro selecionado inválido")
    @Positive(message = "ID do livro deve ser um número positivo")
    private Long livroId;

    @NotNull(message = "Cliente selecionado inválido")
    @Positive(message = "ID do cliente deve ser um número positivo")
    private Long clienteId;

    public Emprestimo dtoParaEmprestimo() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setDataEmprestimo(this.getDataEmprestimo());
        emprestimo.setDataDevolucao(this.getDataDevolucao());
        emprestimo.setDevolvido(this.getDevolvido());

        Livro livro = new Livro();
        livro.setId(this.getLivroId());
        emprestimo.setLivro(livro);

        Cliente cliente = new Cliente();
        cliente.setId(this.getClienteId());
        emprestimo.setCliente(cliente);

        return emprestimo;
    }
}
