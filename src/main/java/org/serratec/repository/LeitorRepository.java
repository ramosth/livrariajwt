package org.serratec.repository;

import java.util.Optional;

import org.serratec.models.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeitorRepository extends JpaRepository<Leitor, Long>{

	boolean existsByEmail(String email);

	Optional<Leitor> findByEmail(String codigoLeitor);

}
