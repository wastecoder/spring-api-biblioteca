# API para Gerenciar uma Biblioteca
API para gerenciamento de livros, autores, clientes e empréstimos.
A API realiza operações de CRUD (Create, Read, Update, Delete) para as entidades com validação das requisições utilizando a anotação @Valid e tratamento adequado de exceções.
Cada livro é tratado como um único exemplar, ou seja, a API não contempla múltiplos exemplares de um mesmo título.
O sistema oferece uma maneira simples de gerenciar os empréstimos de livros, com validações para garantir a consistência dos dados e prevenir ações inválidas, 
como emprestar um livro indisponível ou cadastrar um cliente ou livro inexistente.

## Diagrama de classes
```mermaid
classDiagram
    class Autor {
        +String nome
    }

    class Cliente {
        +String nome
        +String email
        +String telefone
    }

    class Livro {
        +String titulo
        +Integer anoPublicacao
        +Boolean disponivel
        +Autor autor
    }

    class Emprestimo {
        +LocalDate dataEmprestimo
        +LocalDate dataDevolucao
        +Boolean devolvido
        +Livro livro
        +Cliente cliente
    }

    Livro "N" --> "1" Autor : pertence a
    Emprestimo "N" --> "1" Livro : empresta
    Emprestimo "N" --> "1" Cliente : é feito por
```

## Funcionalidades
- [x] URLs amigáveis no padrão REST
- [x] Validação de dados com @Valid nos DTOs
- [x] Erros de validação são retornados na resposta
- [x] Uso de DTO para prevenir manipulação de parâmetros
- [ ] Deploy no Railway
