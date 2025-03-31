package br.com.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.entities.LivroEntity;
import br.com.biblioteca.repositories.LivroRepository;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroDTO> listarTodosLivros() {
        List<LivroEntity> livros = livroRepository.findAll();
        return livros.stream().map(livro -> new LivroDTO(livro)).toList();
    }

    public LivroDTO buscarLivroPorId(Long id) {
        return new LivroDTO(livroRepository.findById(id).get());
    }

    public List<LivroDTO> buscarLivroPorTitulo(String titulo) {
        List<LivroEntity> livros = livroRepository.findByTitulo(titulo);
        return livros.stream().map(LivroDTO::new).toList();
    }

    public LivroDTO buscarLivroPorEmprestimo(Long idEmprestimo) {
        LivroEntity livro = livroRepository.findLivroByEmprestimoId(idEmprestimo);
        return new LivroDTO(livro);
    }

    public void adicionarLivro(LivroDTO livro) {
        LivroEntity livroEntity = new LivroEntity(livro);
        livroRepository.save(livroEntity);
    }

    public LivroDTO atualizarLivro(Long id, LivroDTO livroAtualizado) {
        LivroEntity livro = livroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livro com ID " + id + " não encontrado"));
    
        livro.setTitulo(livroAtualizado.titulo());
        livro.setNomeAutor(livroAtualizado.nomeAutor());
        livro.setAnoPublicacao(livroAtualizado.anoPublicacao());
        livro.setQuantidadeExemplares(livroAtualizado.quantidadeExemplares());
        livro.setGenero(livroAtualizado.genero());
    
        return new LivroDTO(livroRepository.save(livro));
    }

    public void deletarLivro(Long id) {
        LivroEntity livro = livroRepository.findById(id).get();
        livroRepository.delete(livro);
    }

    public void realizarEmprestimoLivro(Long id) {
        LivroEntity livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livro.setEmprestado(true);
        livroRepository.save(livro);
    }

    public void devolverLivro(Long id) {
        LivroEntity livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livro.setEmprestado(false);
        livroRepository.save(livro);
    }
}
