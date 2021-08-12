package org.serratec.resources;

import java.util.List;

import org.serratec.dto.autor.AutorCadastroDTO;
import org.serratec.models.Autor;
import org.serratec.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutorResource {

	@Autowired
	AutorRepository repository;
	
	@GetMapping("/autor/todos")
	public ResponseEntity<?> getTodos() {
		
		List<Autor> todos = repository.findAll();
		
		return new ResponseEntity<>(todos, HttpStatus.OK);
	}
	
	@PostMapping("/autor/tradicional")
	public ResponseEntity<?> postAutor(@Validated @RequestBody Autor autor) {
		repository.save(autor);
		
		return new ResponseEntity<>("Cadastrado com sucesso!", HttpStatus.OK);
	}
	
	@PostMapping("/autor")
	public ResponseEntity<?> postAutorDTO(@Validated @RequestBody AutorCadastroDTO dto) {
		Autor autor = dto.toAutor();
		
		repository.save(autor);
		
		return new ResponseEntity<>("Cadastrado com sucesso", HttpStatus.OK);
	}
}
