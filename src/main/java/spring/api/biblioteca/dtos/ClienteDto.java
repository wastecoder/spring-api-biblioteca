package spring.api.biblioteca.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import spring.api.biblioteca.entities.Cliente;

@Data
public class ClienteDto {
    @NotEmpty(message = "Nome não pode ser vazio")
    @NotBlank(message = "Nome não pode conter apenas espaço")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 a 100 caracteres")
    private String nome;

    @NotEmpty(message = "E-mail não pode ser vazio")
    @NotBlank(message = "E-mail não pode conter apenas espaço")
    @Size(min = 3, max = 100, message = "E-mail deve ter entre 3 a 100 caracteres")
    private String email;

    @NotEmpty(message = "Telefone não pode ser vazio")
    @NotBlank(message = "Telefone não pode conter apenas espaço")
    @Size(min = 8, max = 11, message = "Telefone deve ter entre 3 a 100 caracteres")
    private String telefone;

    public Cliente dtoParaCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(this.getNome());
        cliente.setEmail(this.getEmail());
        cliente.setTelefone(this.getTelefone());
        return cliente;
    }
}
