package br.com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.dto.UsuarioDTO;
import br.com.biblioteca.services.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> listarTodosUsuarios() {
        return usuarioService.listarTodosUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioDTO buscarUsuarioPorID(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorID(id);
    }

    @GetMapping("/buscar")
    public List<UsuarioDTO> buscarUsuario(@RequestParam(required = false) String nome,
            @RequestParam(required = false) Long idEmprestimo) {
        if (nome != null) {
            return usuarioService.buscarUsuarioPorNome(nome);
        } else if (idEmprestimo != null) {
            return List.of(usuarioService.buscarUsuarioPorEmprestimo(idEmprestimo));
        }
        return List.of();
    }

    @PostMapping
    public void adicionarUsuario(@Validated @RequestBody UsuarioDTO usuario) {
        usuarioService.adicionarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public UsuarioDTO atualizarUsuario(@PathVariable Long id, @Validated @RequestBody UsuarioDTO usuarioAtualizado) {
        return usuarioService.atualizarUsuario(id, usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.ok().build();
    }
}
