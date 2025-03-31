package br.com.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.biblioteca.entities.EnderecoEntity;
import br.com.biblioteca.entities.UsuarioEntity;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    List<UsuarioEntity> findByNome(String nome);

    List<UsuarioEntity> findByEndereco(EnderecoEntity endereco);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.id = (SELECT e.usuario.id FROM EmprestimoEntity e WHERE e.id = :idEmprestimo)")
    UsuarioEntity findUsuarioByEmprestimoId(@Param("idEmprestimo") Long idEmprestimo);
}