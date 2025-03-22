package spring.api.biblioteca.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import spring.api.biblioteca.entities.Autor;

@Data
public class AutorDto {
    @NotEmpty(message = "Nome não pode ser vazio")
    @NotBlank(message = "Nome não pode conter apenas espaço")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 a 100 caracteres")
    private String nome;

    public Autor dtoParaAutor() {
        Autor autor = new Autor();
        autor.setNome(this.getNome());
        return autor;
    }
}
