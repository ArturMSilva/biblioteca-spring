package br.com.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.repositories.EmprestimoRepository;
import br.com.biblioteca.repositories.LivroRepository;
import br.com.biblioteca.repositories.UsuarioRepository;

import java.util.List;
import br.com.biblioteca.dto.EmprestimoDTO;
import br.com.biblioteca.entities.EmprestimoEntity;
import br.com.biblioteca.entities.LivroEntity;
import br.com.biblioteca.entities.UsuarioEntity;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroRepository livroRepository;

    public List<EmprestimoDTO> buscarTodosEmprestimos() {
        List<EmprestimoEntity> emprestimos = emprestimoRepository.findAll();
        return emprestimos.stream().map(EmprestimoDTO::new).toList();
    }

    public EmprestimoDTO buscarEmprestimoPorId(Long id) {
        return new EmprestimoDTO(emprestimoRepository.findById(id).get());
    }

    public List<EmprestimoDTO> buscarEmprestimoPorUsuarioId(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).get();
        List<EmprestimoEntity> emprestimos = emprestimoRepository.findByUsuario(usuario);
        return emprestimos.stream().map(EmprestimoDTO::new).toList();
    }

    public List<EmprestimoDTO> buscarEmprestimoPorLivroId(Long id) {
        LivroEntity livro = livroRepository.findById(id).get();
        List<EmprestimoEntity> emprestimos = emprestimoRepository.findByLivro(livro);
        return emprestimos.stream().map(EmprestimoDTO::new).toList();
    }

    public EmprestimoDTO adicionarEmprestimo(EmprestimoDTO emprestimoDTO) {
        EmprestimoEntity emprestimo = new EmprestimoEntity(emprestimoDTO);
        emprestimo = emprestimoRepository.save(emprestimo);

        return new EmprestimoDTO(emprestimo);
    }

    public EmprestimoDTO atualizarEmprestimo(EmprestimoDTO emprestimoDTO) {
        EmprestimoEntity emprestimo = new EmprestimoEntity(emprestimoDTO);
        emprestimo = emprestimoRepository.save(emprestimo);

        return new EmprestimoDTO(emprestimo);
    }

    public void deletarEmprestimo(Long id) {
        emprestimoRepository.deleteById(id);
    }
}
