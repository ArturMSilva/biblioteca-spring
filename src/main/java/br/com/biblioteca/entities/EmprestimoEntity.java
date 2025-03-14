package br.com.biblioteca.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.biblioteca.dto.EmprestimoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "emprestimos")
@Getter
@Setter
public class EmprestimoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "livro_id", referencedColumnName = "id", nullable = false)
    private LivroEntity livro;

    @Column(nullable = false)
    private LocalDate dataEmprestimo;

    @Column(nullable = false)
    private LocalDate dataDevolucao;

    @Column(nullable = false)
    private Boolean devolvido;

    public EmprestimoEntity(EmprestimoDTO emprestimoDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.id = emprestimoDTO.id();
        this.usuario = (emprestimoDTO.usuario() != null) ? new UsuarioEntity(emprestimoDTO.usuario()) : null;
        this.livro = (emprestimoDTO.livro() != null) ? new LivroEntity(emprestimoDTO.livro()) : null;
        this.dataEmprestimo = LocalDate.parse(emprestimoDTO.dataEmprestimo(), formatter);
        this.dataDevolucao = LocalDate.parse(emprestimoDTO.dataDevolucao(), formatter);
        this.devolvido = emprestimoDTO.devolvido();
    }

    public EmprestimoEntity() {
    }

    public EmprestimoEntity(Long id, UsuarioEntity usuario, LivroEntity livro, LocalDate dataEmprestimo,
            LocalDate dataDevolucao, Boolean devolvido) {
        this.id = id;
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
    }
}
