package br.com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.dto.EnderecoDTO;
import br.com.biblioteca.entities.EnderecoEntity;
import br.com.biblioteca.entities.UsuarioEntity;
import br.com.biblioteca.repositories.EnderecoRepository;
import br.com.biblioteca.repositories.UsuarioRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<EnderecoDTO> bucarTodosEnderecos() {
        List<EnderecoEntity> enderecos = enderecoRepository.findAll();
        return enderecos.stream().map(EnderecoDTO::new).toList();
    }

    public EnderecoDTO buscarEnderecoPorId(Long id) {
        return new EnderecoDTO(enderecoRepository.findById(id).get());
    }

    public List<EnderecoDTO> buscarEnderecoPorCEP(String cep) {
        List<EnderecoEntity> enderecos = enderecoRepository.findByCep(cep);
        return enderecos.stream().map(EnderecoDTO::new).toList();
    }

    public void adicionarEndereco(EnderecoDTO endereco) {
        EnderecoEntity enderecoEntity = new EnderecoEntity(endereco);
        enderecoRepository.save(enderecoEntity);
    }

    public EnderecoDTO atualizarEndereco(Long id, EnderecoDTO enderecoAtualizado) {
        EnderecoEntity endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereco com ID " + id + " não encontrado"));

        endereco.setCep(enderecoAtualizado.cep());
        endereco.setNumero(enderecoAtualizado.numero());
        endereco.setBairro(enderecoAtualizado.bairro());
        endereco.setCidade(enderecoAtualizado.cidade());
        endereco.setEstado(enderecoAtualizado.estado());
        endereco.setRua(enderecoAtualizado.rua());

        return new EnderecoDTO(enderecoRepository.save(endereco));
    }

    public void deletarEndereco(Long id) {
        EnderecoEntity endereco = enderecoRepository.findById(id).get();
        List<UsuarioEntity> usuarios = usuarioRepository.findByEndereco(endereco);
        for (UsuarioEntity usuario : usuarios) {
            usuario.setEndereco(null);
            usuarioRepository.save(usuario);
        }
        enderecoRepository.delete(endereco);
    }
}
