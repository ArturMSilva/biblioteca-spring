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

    @GetMapping("/buscar")
    public Object buscarLivro(@RequestParam(required = false) String titulo,
            @RequestParam(required = false) Long idEmprestimo) {
        if (titulo != null) {
            return livroService.buscarLivroPorTitulo(titulo);
        } else if (idEmprestimo != null) {
            return livroService.buscarLivroPorEmprestimo(idEmprestimo);
        }
        return ResponseEntity.badRequest().body("Parâmetro inválido. Informe 'titulo' ou 'id'.");
    }

    @PostMapping
    public void adicionarLivro(@Validated @RequestBody LivroDTO livro) {
        livroService.adicionarLivro(livro);
    }

    @PutMapping("/{id}")
    public LivroDTO atualizarLivro(@PathVariable Long id, @Validated @RequestBody LivroDTO livroAtualizado) {
        return livroService.atualizarLivro(id, livroAtualizado);
    }

    @PostMapping("/{id}/adicionar-exemplar")
    public ResponseEntity<LivroDTO> adicionarExemplar(@PathVariable Long id, @RequestParam int quantidade) {
        LivroDTO livro = livroService.adicionarExemplar(id, quantidade);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        livroService.deletarLivro(id);
        return ResponseEntity.ok().build();
    }

}
