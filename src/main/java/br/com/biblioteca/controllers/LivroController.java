package br.com.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.services.LivroService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<LivroDTO> listarTodosLivros() {
        return livroService.listarTodosLivros();
    }
    
    @GetMapping("/{id}")
    public LivroDTO buscarLivroPorId(@PathVariable Long id) {
        return livroService.buscarLivroPorId(id);
    }
    
    @GetMapping("/buscar-livro-titulo")
    public List<LivroDTO> buscarLivroPorTitulo(@RequestParam String titulo) {
        return livroService.buscarLivroPorTitulo(titulo);
    }

    @PostMapping
    public void adicionarLivro(@Validated @RequestBody LivroDTO livro) {
        livroService.adicionarLivro(livro);
    }

    @PutMapping
    public LivroDTO atualizarLivro(@Validated @RequestBody LivroDTO livro) {
        return livroService.atualizarLivro(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        livroService.deletarLivro(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/emprestar/{id}")
    public void realizarEmprestimoLivro(@PathVariable Long id) {
        livroService.realizarEmprestimoLivro(id);
    }

    @PutMapping("/devolver/{id}")
    public void devolverLivro(@PathVariable Long id) {
        livroService.devolverLivro(id);
    }
}
