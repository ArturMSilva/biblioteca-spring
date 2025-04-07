package br.com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.dto.EnderecoDTO;
import br.com.biblioteca.services.EnderecoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<EnderecoDTO> listarTodosEnderecos() {
        return enderecoService.bucarTodosEnderecos();
    }

    @GetMapping("/{id}")
    public EnderecoDTO buscarEnderecoPorId(@PathVariable Long id) {
        return enderecoService.buscarEnderecoPorId(id);
    }

    @GetMapping("/buscar")
    public List<EnderecoDTO> buscarEnderecoPorCEP(@RequestParam String cep) {
        return enderecoService.buscarEnderecoPorCEP(cep);
    }

    @PostMapping
    public void adicionarEndereco(@Validated @RequestBody EnderecoDTO endereco) {
        enderecoService.adicionarEndereco(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEndereco(@PathVariable Long id, @Validated @RequestBody EnderecoDTO endereco) {
        enderecoService.atualizarEndereco(id, endereco);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.ok().build();
    }
}
