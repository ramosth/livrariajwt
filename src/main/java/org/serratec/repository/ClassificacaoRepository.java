package org.serratec.repository;

import java.util.List;
import java.util.Optional;

import org.serratec.models.Classificacao;
import org.serratec.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassificacaoRepository extends JpaRepository<Classificacao, Long>{

	@Query("SELECT classif FROM Classificacao classif WHERE classif.livro.id = :optional ORDER BY data_classificacao")
	List<Classificacao> findAllByLivroId(Optional<Livro> optional);

	List<Classificacao> findByLivro(Optional<Livro> optional);

}
