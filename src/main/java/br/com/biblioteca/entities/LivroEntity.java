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
    private Integer anoPublicacao;

    @Column(nullable = false)
    private Integer quantidadeExemplares;

    @Column(nullable = false)
    private Integer quantidadeDisponivel;

    @Column(nullable = false)
    private String genero;

    @Column
    private Boolean emprestado;

    public LivroEntity() {
    }

    public LivroEntity(LivroDTO livroDTO) {
        this.id = livroDTO.id();
        this.titulo = livroDTO.titulo();
        this.nomeAutor = livroDTO.nomeAutor();
        this.anoPublicacao = livroDTO.anoPublicacao();
        this.quantidadeExemplares = livroDTO.quantidadeExemplares();
        this.quantidadeDisponivel = livroDTO.quantidadeDisponivel() != null ? livroDTO.quantidadeDisponivel() : 0;
        this.genero = livroDTO.genero();
        this.emprestado = livroDTO.emprestado() != null ? livroDTO.emprestado() : false; 
    }
    
    public LivroEntity(Long id, String titulo, String nomeAutor, Integer anoPublicacao, Integer quantidadeExemplares,
            String genero, Boolean emprestado) {
        this.id = id;
        this.titulo = titulo;
        this.nomeAutor = nomeAutor;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeExemplares = quantidadeExemplares;
        this.quantidadeDisponivel = quantidadeExemplares != null ? quantidadeExemplares : 0;
        this.genero = genero;
        this.emprestado = emprestado != null ? emprestado : false; 
    }
}
