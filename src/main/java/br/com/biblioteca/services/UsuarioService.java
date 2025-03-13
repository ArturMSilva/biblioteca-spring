package br.com.biblioteca.services;

import br.com.biblioteca.dto.UsuarioDTO;
import br.com.biblioteca.entities.UsuarioEntity;
import br.com.biblioteca.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> listarTodosUsuarios(){
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).toList(); 
        //stream() é um método que transforma a lista em um fluxo de dados
        //map() é um método que transforma cada elemento do fluxo de dados
        //toList() é um método que transforma o fluxo de dados em uma lista
    }

    public List<UsuarioDTO> buscarUsuarioPorNome(String nome){
        List<UsuarioEntity> usuarios = usuarioRepository.findByNome(nome);
        return usuarios.stream().map(UsuarioDTO::new).toList();
    }

    public void adicionarUsuario(UsuarioDTO usuario){
        if (usuario.cpf() == null || usuario.cpf().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        usuarioRepository.save(usuarioEntity);
    }

    public UsuarioDTO atualizarUsuario(UsuarioDTO usuario){
        if (usuario.cpf() == null || usuario.cpf().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        return new UsuarioDTO(usuarioRepository.save(usuarioEntity));
    }

    public void deletarUsuario(Long id){
        UsuarioEntity usuario = usuarioRepository.findById(id).get();
        usuarioRepository.delete(usuario);
    }

    public UsuarioDTO buscarUsuarioPorID(Long id){
        return new UsuarioDTO(usuarioRepository.findById(id).get());
    }
}