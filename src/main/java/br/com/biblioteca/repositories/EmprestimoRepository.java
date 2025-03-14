package br.com.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.biblioteca.entities.EmprestimoEntity;
import br.com.biblioteca.entities.LivroEntity;
import br.com.biblioteca.entities.UsuarioEntity;
import java.util.List;

public interface EmprestimoRepository extends JpaRepository<EmprestimoEntity, Long> {
    List<EmprestimoEntity> findByUsuario(UsuarioEntity usuario);

    List<EmprestimoEntity> findByLivro(LivroEntity livro);
}
