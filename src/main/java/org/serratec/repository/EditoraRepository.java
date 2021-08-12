package org.serratec.repository;

import java.util.Optional;

import org.serratec.models.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditoraRepository extends JpaRepository<Editora, Long>{

	Optional<Editora> findByCodigo(String editora);
}
