package br.com.biblioteca.entities;

import jakarta.persistence.*;
import br.com.biblioteca.dto.LivroDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "livros")
@Getter
@Setter
public class LivroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String nomeAutor;

    @Column(nullable = false)
    private int anoPublicacao;

    @Column(nullable = false)
    private int quantidadeExemplares;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private boolean emprestado;

    public LivroEntity(LivroDTO livroDTO) {
        this.id = livroDTO.id();
        this.titulo = livroDTO.titulo();
        this.nomeAutor = livroDTO.nomeAutor();
        this.anoPublicacao = livroDTO.anoPublicacao();
        this.quantidadeExemplares = livroDTO.quantidadeExemplares();
        this.genero = livroDTO.genero();
        this.emprestado = false;
    }

    public LivroEntity() {
        this.emprestado = false;
    }

    public LivroEntity(Long id, String titulo, String nomeAutor, int anoPublicacao, int quantidadeExemplares,
            String genero, boolean emprestado) {
        this.id = id;
        this.titulo = titulo;
        this.nomeAutor = nomeAutor;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeExemplares = quantidadeExemplares;
        this.genero = genero;
        this.emprestado = emprestado;
    }
}
