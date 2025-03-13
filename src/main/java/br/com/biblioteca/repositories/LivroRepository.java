package br.com.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.entities.LivroEntity;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
    List<LivroEntity> findByTitulo(String titulo);
}