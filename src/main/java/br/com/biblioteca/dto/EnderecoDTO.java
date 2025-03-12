package br.com.biblioteca.dto;

import br.com.biblioteca.entities.EnderecoEntity;

public record EnderecoDTO(Long id, String rua, String bairro, int numero, String cidade, String estado, String cep) {
    public EnderecoDTO(EnderecoEntity enderecoEntity) {
        this(
                enderecoEntity.getId(), 
                enderecoEntity.getRua(), 
                enderecoEntity.getBairro(), 
                enderecoEntity.getNumero(),
                enderecoEntity.getCidade(), 
                enderecoEntity.getEstado(), 
                enderecoEntity.getCep());
    }
}