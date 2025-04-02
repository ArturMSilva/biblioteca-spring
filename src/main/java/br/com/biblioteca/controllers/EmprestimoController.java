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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping
    public ResponseEntity<Void> adicionarEmprestimo(@Validated @RequestBody EmprestimoDTO emprestimo) {
        emprestimoService.adicionarEmprestimo(emprestimo);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<EmprestimoDTO> ativarEmprestimoPendente(@PathVariable Long id) {
        EmprestimoDTO emprestimoAtualizado = emprestimoService.ativarEmprestimoPendente(id);
        return ResponseEntity.ok(emprestimoAtualizado);
    }

    @PatchMapping("/{id}/renovar")
    public ResponseEntity<EmprestimoDTO> renovarEmprestimo(@PathVariable Long id, @RequestBody int diasAdicionais) {
        EmprestimoDTO emprestimoAtualizado = emprestimoService.renovarEmprestimo(id, diasAdicionais);
        return ResponseEntity.ok(emprestimoAtualizado);
    }

    @PatchMapping("/{id}/encerrar")
    public ResponseEntity<EmprestimoDTO> encerrarEmprestimo(@PathVariable Long id) {
        EmprestimoDTO emprestimoAtualizado = emprestimoService.encerrarEmprestimo(id);
        return ResponseEntity.ok(emprestimoAtualizado);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<EmprestimoDTO> cancelarEmprestimo(@PathVariable Long id) {
        EmprestimoDTO emprestimoAtualizado = emprestimoService.cancelarEmprestimo(id);
        return ResponseEntity.ok(emprestimoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletarEmprestimo(@PathVariable Long id) {
        emprestimoService.deletarEmprestimo(id);
    }

}
