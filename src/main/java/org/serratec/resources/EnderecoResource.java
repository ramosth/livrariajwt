package org.serratec.resources;

import java.util.List;

import org.serratec.models.Endereco;
import org.serratec.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnderecoResource {

	@Autowired
	EnderecoRepository repository;
	
	@GetMapping("/endereco/todos/tradicional")
	public ResponseEntity<?> getTodosTradicional() {
		
		List<Endereco> todos = repository.findAll();
		return new ResponseEntity<>(todos, HttpStatus.OK);
	}
	
	@PostMapping("/endereco/tradicional")
	public ResponseEntity<?> postEndereco(@Validated @RequestBody Endereco endereco) {
		repository.save(endereco);
		
		return new ResponseEntity<>("Cadastrado com sucesso!", HttpStatus.OK);
	}
}
