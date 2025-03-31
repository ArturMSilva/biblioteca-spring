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

    @Autowired
    private EmailService emailService;

    public List<UsuarioDTO> listarTodosUsuarios() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).toList();
    }

    public UsuarioDTO buscarUsuarioPorID(Long id) {
        return new UsuarioDTO(usuarioRepository.findById(id).get());
    }

    public List<UsuarioDTO> buscarUsuarioPorNome(String nome) {
        List<UsuarioEntity> usuarios = usuarioRepository.findByNome(nome);
        return usuarios.stream().map(UsuarioDTO::new).toList();
    }

    public UsuarioDTO buscarUsuarioPorEmprestimo(Long idEmprestimo) {
        UsuarioEntity usuario = usuarioRepository.findUsuarioByEmprestimoId(idEmprestimo);
        return new UsuarioDTO(usuario);
    }

    public void adicionarUsuario(UsuarioDTO usuario) {
        if (usuario.cpf() == null || usuario.cpf().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        usuarioRepository.save(usuarioEntity);

        emailService.enviarEmail(usuario.email(), "Bem-vindo",
                "Olá, " + usuario.nome() + "! Seja bem-vindo à nossa biblioteca!");
    }

    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioAtualizado) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + id + " não encontrado"));
    
        usuario.setNome(usuarioAtualizado.nome());
        usuario.setEmail(usuarioAtualizado.email());
    
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    public void deletarUsuario(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).get();
        usuarioRepository.delete(usuario);
    }
}