package spring.api.biblioteca.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import spring.api.biblioteca.entities.Autor;
import spring.api.biblioteca.entities.Livro;

@Data
public class LivroDto {
    @NotEmpty(message = "Título não pode ser vazio")
    @NotBlank(message = "Título não pode conter apenas espaço")
    @Size(min = 3, max = 100, message = "Título deve ter entre 3 a 100 caracteres")
    private String titulo;

    @Min(value = 1800, message = "Ano de publicação deve ser de 1800 ou mais velho")
    @Max(value = 2030, message = "Ano de publicação deve ser de 2030 ou mais novo") //Daria para validar melhor com @PrePersist/Update, pegando ano atual
    private Integer anoPublicacao;

    @NotNull(message = "Disponível não pode ser nulo")
    private Boolean disponivel;

    @NotNull(message = "Autor selecionado inválido")
    @Positive(message = "ID do autor deve ser um número positivo")
    private Long autorId;

    public Livro dtoParaLivro(Autor autor) {
        Livro livro = new Livro();
        livro.setTitulo(this.getTitulo());
        livro.setAnoPublicacao(this.getAnoPublicacao());
        livro.setDisponivel(this.getDisponivel());
        livro.setAutor(autor);
        return livro;
    }
}
