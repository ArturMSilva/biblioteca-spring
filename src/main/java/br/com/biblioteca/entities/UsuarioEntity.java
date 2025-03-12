package br.com.biblioteca.entities;

import br.com.biblioteca.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private EnderecoEntity endereco;

    public UsuarioEntity(UsuarioDTO usuarioDTO) {
        this.id = usuarioDTO.id();
        this.nome = usuarioDTO.nome();
        this.cpf = usuarioDTO.cpf();
        this.email = usuarioDTO.email();
        this.endereco = (usuarioDTO.endereco() != null) ? new EnderecoEntity(usuarioDTO.endereco()) : null;
        //verificar se o endereco é nulo e se não for, criar um novo endereco
    }

    public UsuarioEntity() {
    }

    public UsuarioEntity(Long id, String nome, String cpf, String email, EnderecoEntity endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
    }
}
