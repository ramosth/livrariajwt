package org.serratec.repository;

import java.util.List;
import java.util.Optional;

import org.serratec.models.Categoria;
import org.serratec.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long>{

	Optional<Livro> findByCodigo(String codigo);

	List<Livro> findAllByCategoria(Categoria categoria);

}
