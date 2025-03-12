package br.com.biblioteca.entities;

import br.com.biblioteca.dto.EnderecoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
public class EnderecoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false, unique = true)
    private String cep;
    
    public EnderecoEntity(EnderecoDTO enderecoDTO) {
        this.id = enderecoDTO.id();
        this.rua = enderecoDTO.rua();
        this.bairro = enderecoDTO.bairro();
        this.numero = enderecoDTO.numero();
        this.cidade = enderecoDTO.cidade();
        this.estado = enderecoDTO.estado();
        this.cep = enderecoDTO.cep();
    }

    public EnderecoEntity() {
    }

    public EnderecoEntity(Long id, String rua, String bairro, int numero, String cidade, String estado, String cep) {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }  
}
