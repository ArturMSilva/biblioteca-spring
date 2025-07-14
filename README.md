# MantasLibrary

Este projeto é uma aplicação desenvolvida em **Spring Framework** para gerenciar uma biblioteca. Ele permite o cadastro, consulta, atualização e exclusão de livros, autores e usuários, além de funcionalidades para empréstimos e devoluções.

## Funcionalidades

- **Gerenciamento de Livros**: Cadastro, edição, listagem e exclusão de livros.
- **Gerenciamento de Autores**: Cadastro e listagem de autores.
- **Gerenciamento de Usuários**: Registro e consulta de usuários da biblioteca.
- **Empréstimos e Devoluções**: Controle de empréstimos e devoluções de livros.
- **Relatórios**: Geração de relatórios sobre o acervo e atividades da biblioteca.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA** para persistência de dados.
- **Thymeleaf** para renderização de páginas HTML.
- **Spring Security** para autenticação e autorização.
- **Maven** para gerenciamento de dependências.

## Pré-requisitos

- **Java 17** ou superior.
- **Maven** instalado.
- IDE de sua preferência (IntelliJ, Eclipse, VS Code, etc.).

## Como Executar o Projeto

1. Clone o repositório:
    ```bash
    git clone https://github.com/ArturMSilva/biblioteca-spring.git
    ```
2. Navegue até o diretório do projeto:
    ```bash
    cd biblioteca-spring
    ```
3. Compile e execute o projeto:
    ```bash
    mvn spring-boot:run
    ```
4. Acesse a aplicação no navegador:
    ```
    http://localhost:8080
    ```

## Estrutura do Projeto

- **src/main/java**: Contém o código-fonte da aplicação.
  - **controller**: Controladores responsáveis pelas requisições HTTP.
  - **service**: Regras de negócio.
  - **repository**: Interfaces para acesso ao banco de dados.
  - **model**: Classes de modelo (entidades).
- **src/main/resources**: Recursos estáticos e arquivos de configuração.
  - **templates**: Páginas HTML renderizadas pelo Thymeleaf.
  - **application.properties**: Configurações da aplicação.

## Contribuição

Contribuições são bem-vindas! Siga os passos abaixo:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature:
    ```bash
    git checkout -b minha-feature
    ```
3. Commit suas alterações:
    ```bash
    git commit -m "Minha nova feature"
    ```
4. Envie para o repositório remoto:
    ```bash
    git push origin minha-feature
    ```
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).