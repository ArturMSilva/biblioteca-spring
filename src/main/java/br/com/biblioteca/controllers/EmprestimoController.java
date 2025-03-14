package br.com.biblioteca.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.dto.EmprestimoDTO;
import br.com.biblioteca.services.EmprestimoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public List<EmprestimoDTO> listarTodosEmprestimos() {
        return emprestimoService.buscarTodosEmprestimos();
    }

    @GetMapping("/{id}")
    public EmprestimoDTO buscarEmprestimoPorId(@PathVariable Long id) {
        return emprestimoService.buscarEmprestimoPorId(id);
    }

    @GetMapping("/buscar-emprestimo-por-usuario/{id}")
    public List<EmprestimoDTO> buscarEmprestimoPorUsuario(@PathVariable Long id) {
        return emprestimoService.buscarEmprestimoPorUsuarioId(id);
    }

    @GetMapping("/buscar-emprestimo-por-livro/{id}")
    public List<EmprestimoDTO> buscarEmprestimoPorLivro(@PathVariable Long id) {
        return emprestimoService.buscarEmprestimoPorLivroId(id);
    }

    @PostMapping
    public ResponseEntity<Void> adicionarEmprestimo(@Validated @RequestBody EmprestimoDTO emprestimo) {
        emprestimoService.adicionarEmprestimo(emprestimo);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public EmprestimoDTO atualizarEmprestimo(@Validated @RequestBody EmprestimoDTO emprestimo) {
        return emprestimoService.atualizarEmprestimo(emprestimo);
    }

    @DeleteMapping("/{id}")
    public void deletarEmprestimo(@PathVariable Long id) {
        emprestimoService.deletarEmprestimo(id);
    }

}
