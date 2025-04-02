package br.com.biblioteca.dto;

import java.time.format.DateTimeFormatter;

import br.com.biblioteca.entities.EmprestimoEntity;

public record EmprestimoDTO(Long id, UsuarioDTO usuario, LivroDTO livro, String dataEmprestimo, String dataDevolucao,
        EmprestimoEntity.StatusEmprestimo status) {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public EmprestimoDTO(EmprestimoEntity emprestimoEntity) {
        this(
                emprestimoEntity.getId(),
                (emprestimoEntity.getUsuario() != null) ? new UsuarioDTO(emprestimoEntity.getUsuario()) : null,
                (emprestimoEntity.getLivro() != null) ? new LivroDTO(emprestimoEntity.getLivro()) : null,
                emprestimoEntity.getDataEmprestimo().format(formatter),
                emprestimoEntity.getDataDevolucao().format(formatter),
                emprestimoEntity.getStatus());
    }
}
