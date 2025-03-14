package br.com.biblioteca.dto;

import br.com.biblioteca.entities.LivroEntity;

public record LivroDTO(Long id, String titulo, String nomeAutor, Integer anoPublicacao, Integer quantidadeExemplares,
        String genero, Boolean emprestado) {
    public LivroDTO(LivroEntity livroEntity) {
        this(
                livroEntity.getId(),
                livroEntity.getTitulo(),
                livroEntity.getNomeAutor(),
                livroEntity.getAnoPublicacao(),
                livroEntity.getQuantidadeExemplares(),
                livroEntity.getGenero(),
                livroEntity.getEmprestado());
    }
}
