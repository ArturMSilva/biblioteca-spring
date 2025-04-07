package br.com.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.repositories.EmprestimoRepository;
import br.com.biblioteca.repositories.LivroRepository;

import java.util.List;
import br.com.biblioteca.dto.EmprestimoDTO;
import br.com.biblioteca.entities.EmprestimoEntity;
import br.com.biblioteca.entities.LivroEntity;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    public List<EmprestimoDTO> buscarTodosEmprestimos() {
        List<EmprestimoEntity> emprestimos = emprestimoRepository.findAll();
        return emprestimos.stream().map(EmprestimoDTO::new).toList();
    }

    public EmprestimoDTO buscarEmprestimoPorId(Long id) {
        return new EmprestimoDTO(emprestimoRepository.findById(id).get());
    }

    public EmprestimoDTO adicionarEmprestimo(EmprestimoDTO emprestimoDTO) {
        EmprestimoEntity emprestimo = new EmprestimoEntity(emprestimoDTO);
        LivroEntity livro = emprestimo.getLivro();
    
        if (livro.getQuantidadeDisponivel() > 0) {
            emprestimo.setStatus(EmprestimoEntity.StatusEmprestimo.EM_ANDAMENTO);
            livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1); // Diminui a quantidade disponível
        } else {
            emprestimo.setStatus(EmprestimoEntity.StatusEmprestimo.PENDENTE);
        }
    
        emprestimo = emprestimoRepository.save(emprestimo);
        livroRepository.save(livro); 
    
        return new EmprestimoDTO(emprestimo);
    }

    public EmprestimoDTO ativarEmprestimoPendente(Long id) {
        EmprestimoEntity emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo com ID " + id + " não encontrado"));

        if (emprestimo.getStatus() != EmprestimoEntity.StatusEmprestimo.PENDENTE) {
            throw new IllegalStateException("Somente empréstimos pendentes podem ser ativados.");
        }

        emprestimo.setStatus(EmprestimoEntity.StatusEmprestimo.EM_ANDAMENTO);
        return new EmprestimoDTO(emprestimoRepository.save(emprestimo));
    }

    public EmprestimoDTO renovarEmprestimo(Long id, int diasAdicionais) {
        EmprestimoEntity emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo com ID " + id + " não encontrado"));

        if (emprestimo.getStatus() == EmprestimoEntity.StatusEmprestimo.FINALIZADO
                || emprestimo.getStatus() == EmprestimoEntity.StatusEmprestimo.CANCELADO) {
            throw new IllegalStateException("Não é possível renovar um empréstimo finalizado ou cancelado.");
        }

        emprestimo.setDataDevolucao(emprestimo.getDataDevolucao().plusDays(diasAdicionais));

        if (emprestimo.getStatus() != EmprestimoEntity.StatusEmprestimo.EM_ANDAMENTO) {
            emprestimo.setStatus(EmprestimoEntity.StatusEmprestimo.EM_ANDAMENTO);
        }

        return new EmprestimoDTO(emprestimoRepository.save(emprestimo));
    }

    public EmprestimoDTO encerrarEmprestimo(Long id) {
        EmprestimoEntity emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo com ID " + id + " não encontrado"));

        if (emprestimo.getStatus() == EmprestimoEntity.StatusEmprestimo.FINALIZADO) {
            throw new IllegalStateException("O empréstimo já está finalizado.");
        }

        emprestimo.setStatus(EmprestimoEntity.StatusEmprestimo.FINALIZADO);
        return new EmprestimoDTO(emprestimoRepository.save(emprestimo));
    }

    public EmprestimoDTO cancelarEmprestimo(Long id) {
        EmprestimoEntity emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo com ID " + id + " não encontrado"));

        if (emprestimo.getStatus() == EmprestimoEntity.StatusEmprestimo.FINALIZADO) {
            throw new IllegalStateException("Não é possível cancelar um empréstimo já finalizado.");
        }

        emprestimo.setStatus(EmprestimoEntity.StatusEmprestimo.CANCELADO);
        return new EmprestimoDTO(emprestimoRepository.save(emprestimo));
    }

    public void deletarEmprestimo(Long id) {
        emprestimoRepository.deleteById(id);
    }
}
