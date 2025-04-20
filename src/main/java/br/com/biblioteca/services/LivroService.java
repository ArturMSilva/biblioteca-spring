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
        LivroEntity livro = livroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livro com ID " + id + " não encontrado"));
        return new LivroDTO(livro);
    }

    public List<LivroDTO> buscarLivroPorTitulo(String titulo) {
        List<LivroEntity> livros = livroRepository.findByTitulo(titulo);
        return livros.stream().map(LivroDTO::new).toList();
    }

    public LivroDTO buscarLivroPorEmprestimo(Long idEmprestimo) {
        LivroEntity livro = livroRepository.findLivroByEmprestimoId(idEmprestimo);
        return new LivroDTO(livro);
    }

    public LivroDTO adicionarLivro(LivroDTO livroDTO) {
        LivroEntity livro = new LivroEntity(livroDTO);
        livro.setQuantidadeDisponivel(livro.getQuantidadeExemplares());
        return new LivroDTO(livroRepository.save(livro));
    }

    public LivroDTO atualizarLivro(Long id, LivroDTO livroAtualizado) {
        LivroEntity livro = livroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livro com ID " + id + " não encontrado"));

        livro.setTitulo(livroAtualizado.titulo());
        livro.setNomeAutor(livroAtualizado.nomeAutor());
        livro.setAnoPublicacao(livroAtualizado.anoPublicacao());
        livro.setGenero(livroAtualizado.genero());

        return new LivroDTO(livroRepository.save(livro));
    }

    public LivroDTO adicionarExemplar(Long id, int quantidade) {
        LivroEntity livro = livroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livro com ID " + id + " não encontrado"));

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de exemplares a ser adicionada deve ser maior que zero.");
        }

        livro.setQuantidadeExemplares(livro.getQuantidadeExemplares() + quantidade);
        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + quantidade);

        return new LivroDTO(livroRepository.save(livro));
    }

    public void deletarLivro(Long id) {
        LivroEntity livro = livroRepository.findById(id).get();
        livroRepository.delete(livro);
    }

}
