package org.serratec.repository;

import java.util.Optional;

import org.serratec.models.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {

	Optional<Venda> findByProtocolo(String protocolo);

}
