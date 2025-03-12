package br.com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.dto.UsuarioDTO;
import br.com.biblioteca.services.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> listarTodosUsuarios() {
        return usuarioService.listarTodosUsuarios();
    }

    @PostMapping
    public void adicionarUsuario(@RequestBody UsuarioDTO usuario) {
        usuarioService.adicionarUsuario(usuario);
    }
    
    
}
