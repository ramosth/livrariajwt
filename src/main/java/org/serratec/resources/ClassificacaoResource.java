package org.serratec.resources;

import java.time.LocalDateTime;
import java.util.List;

import org.serratec.models.Classificacao;
import org.serratec.repository.ClassificacaoRepository;
import org.serratec.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassificacaoResource {

	@Autowired
	ClassificacaoRepository repository;
	
	@Autowired
	LivroRepository livroRepository;

	@PostMapping("/classificacao")
	public ResponseEntity<?> postClassificacao(@Validated @RequestBody Classificacao classificacao, @RequestHeader("segredo") String segredo) {

		if(!"123456".equals(segredo))
			return new ResponseEntity<>("o segredo não foi informado ou está incorreto!", HttpStatus.UNAUTHORIZED);
		
		LocalDateTime data = LocalDateTime.now();
		classificacao.setDataClassificacao(data);

		repository.save(classificacao);

		return new ResponseEntity<>("Cadastrado com sucesso!", HttpStatus.OK);
	}

	@GetMapping("/classificacao/todos")
	public ResponseEntity<?> getTodos() {

		List<Classificacao> todos = repository.findAll();
		return new ResponseEntity<>(todos, HttpStatus.OK);
	}
	
//	@GetMapping("/classificacao/livro")
//	public ResponseEntity<List<ClassificacaoSimplificadaDTO>> getClassificacaoPorLivro(@RequestParam(required = false, value = "livro", defaultValue = "0") String codigo) {
//		Optional<Livro> optional = livroRepository.findByCodigo(codigo);
//		List<Classificacao> classificacoes = repository.findByLivro(optional);
//		List<ClassificacaoSimplificadaDTO> todosDTO = classificacoes.stream().map(obj -> new ClassificacaoSimplificadaDTO(obj)).collect(Collectors.toList());
//
//		return new ResponseEntity<>(todosDTO, HttpStatus.OK);
//	}
}
