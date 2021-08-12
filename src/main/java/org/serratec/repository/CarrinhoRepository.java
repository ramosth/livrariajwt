package org.serratec.repository;

import java.util.Optional;

import org.serratec.models.Carrinho;
import org.serratec.models.Leitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

	Optional<Carrinho> findByLeitor(Leitor leitor);

}
