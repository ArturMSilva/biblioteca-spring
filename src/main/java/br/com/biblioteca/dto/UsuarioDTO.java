package br.com.biblioteca.dto;

import br.com.biblioteca.entities.UsuarioEntity;

public record UsuarioDTO(Long id, String nome, String email, String cpf, EnderecoDTO endereco) {
    public UsuarioDTO(UsuarioEntity usuarioEntity) {
        this(
                usuarioEntity.getId(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getCpf(),
                (usuarioEntity.getEndereco() != null) ? new EnderecoDTO(usuarioEntity.getEndereco()) : null);
    }
}