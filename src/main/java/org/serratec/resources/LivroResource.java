package org.serratec.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.serratec.dto.classificacao.ClassificacaoSimplificadaDTO;
import org.serratec.dto.livro.LivroCadastroDTO;
import org.serratec.dto.livro.LivroDetalhamentoDTO;
import org.serratec.dto.livro.LivroDetalheDTO;
import org.serratec.exceptions.LivroException;
import org.serratec.models.Categoria;
import org.serratec.models.Classificacao;
import org.serratec.models.Livro;
import org.serratec.repository.AutorRepository;
import org.serratec.repository.ClassificacaoRepository;
import org.serratec.repository.EditoraRepository;
import org.serratec.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivroResource {
	
	@Autowired
	LivroRepository repository;
	
	@Autowired
	AutorRepository autorRepository;
	
	@Autowired
	EditoraRepository editoraRepository;
	
	@Autowired
	ClassificacaoRepository classificacaoRepository;

	/*
	 * @GetMapping("/testar") public void testar() { Livro livro = new Livro();
	 * livro.setNome("testando..."); livro.setCategoria(Categoria.CIENTIFICO);
	 * 
	 * repository.save(livro); }
	 * 
	 * @GetMapping("/testar2") public ResponseEntity<?> testar2() { return new
	 * ResponseEntity<>(repository.findAll(), HttpStatus.OK); }
	 */
	
	@GetMapping("/livro/todos")
	public ResponseEntity<?> getTodos() {
		
		List<Livro> todos = repository.findAll();
		List<LivroDetalheDTO> todosDTO = todos.stream().map(obj -> new LivroDetalheDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<>(todosDTO, HttpStatus.OK);
	}
	
	@GetMapping("/livro/categoria/{categoria}")
	public ResponseEntity<?> getCategoria(@PathVariable Categoria categoria) {
		
		List<Livro> todos = repository.findAllByCategoria(categoria);
		List<LivroDetalheDTO> todosDTO = todos.stream().map(obj -> new LivroDetalheDTO(obj)).collect(Collectors.toList());
		return new ResponseEntity<>(todosDTO, HttpStatus.OK);
	}
	
	//ResponseEntity - é um padrão que a resposta pode ser qualquer coisa, com a ? o retorno pode ser String, Optional, ou em caso de não ter nada, será "null"
	@GetMapping("/livro/detalhe/{codigo}")
	public ResponseEntity<?> getDetalhe(@PathVariable String codigo, Classificacao classificacao) {
		
		Optional<Livro> optional = repository.findByCodigo(codigo);
		
		if(optional.isEmpty())
			return new ResponseEntity<>("Livro inexistente", HttpStatus.NOT_FOUND);
	
		return new ResponseEntity<>(new LivroDetalhamentoDTO(optional.get()), HttpStatus.OK);
	}
	
	@GetMapping("/livro/classificacao/{codigo}")
	public ResponseEntity<?> getDetalheClassificacao(@PathVariable String codigo) {
		
		Optional<Livro> optional = repository.findByCodigo(codigo);
		List<Classificacao> classificacoes = classificacaoRepository.findByLivro(optional);
		List<ClassificacaoSimplificadaDTO> todosDTO = classificacoes.stream().map(obj -> new ClassificacaoSimplificadaDTO(obj)).collect(Collectors.toList());
		
		if(optional.isEmpty())
			return new ResponseEntity<>("Livro inexistente", HttpStatus.NOT_FOUND);
	
		return new ResponseEntity<>(todosDTO, HttpStatus.OK);
	}
	
	@PostMapping("/livro/tradicional")
	public ResponseEntity<?> postLivro(@Validated @RequestBody Livro livro) {
		
		try {
			repository.save(livro);
			return new ResponseEntity<>("Cadastrado com sucesso!", HttpStatus.OK);
		} catch (LivroException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/livro")
	public ResponseEntity<?> postLivroDTO(@Validated @RequestBody LivroCadastroDTO dto) {
		
		try {
			Livro livro = dto.toLivro(autorRepository, editoraRepository);
			repository.save(livro);
			return new ResponseEntity<>("Cadastrado com sucesso!", HttpStatus.OK);
		} catch (LivroException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(value = "/livro/capa/{codigo}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<?> getCapa (@PathVariable String codigo){
		Livro livro = repository.findByCodigo(codigo).get();
		
		return new ResponseEntity<>(livro.getCapa() , HttpStatus.OK); 
	}
	
	@GetMapping(value = "/livro/pdf/{codigo}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<?> getPdf (@PathVariable String codigo){
		Livro livro = repository.findByCodigo(codigo).get();
		
		return new ResponseEntity<>(livro.getPdf() , HttpStatus.OK); 
	}
}
