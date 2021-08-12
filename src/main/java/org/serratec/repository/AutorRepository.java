package org.serratec.repository;

import java.util.Optional;

import org.serratec.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long>{

	 Optional<Autor> findByCodigo(String codigo);

}
