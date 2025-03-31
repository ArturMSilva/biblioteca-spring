package br.com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.biblioteca.entities.LivroEntity;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
    List<LivroEntity> findByTitulo(String titulo);

    @Query("SELECT l FROM LivroEntity l WHERE l.id = (SELECT e.livro.id FROM EmprestimoEntity e WHERE e.id = :idEmprestimo)")
    LivroEntity findLivroByEmprestimoId(@Param("idEmprestimo") Long idEmprestimo);
}