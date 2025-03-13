package br.com.biblioteca.dto;

import br.com.biblioteca.entities.LivroEntity;

public record LivroDTO(Long id, String titulo, String nomeAutor, int anoPublicacao, int quantidadeExemplares,
        String genero, boolean emprestado) {
    public LivroDTO(LivroEntity livroEntity) {
        this(
                livroEntity.getId(),
                livroEntity.getTitulo(),
                livroEntity.getNomeAutor(),
                livroEntity.getAnoPublicacao(),
                livroEntity.getQuantidadeExemplares(),
                livroEntity.getGenero(),
                livroEntity.isEmprestado());
    }
}
