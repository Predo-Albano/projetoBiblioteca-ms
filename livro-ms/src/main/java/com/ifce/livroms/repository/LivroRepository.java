package com.ifce.livroms.repository;

import com.ifce.livroms.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByDisponivel(Boolean disponivel);
}