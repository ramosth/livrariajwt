package org.serratec.resources;

import java.util.List;

import org.serratec.dto.editora.EditoraCadastroDTO;
import org.serratec.models.Editora;
import org.serratec.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditoraResource {

	@Autowired
	EditoraRepository repository;
	
	@GetMapping("/editora/todos")
	public ResponseEntity<?> getTodos() {
		
		List<Editora> todos = repository.findAll();
		return new ResponseEntity<>(todos, HttpStatus.OK);
	}
	
	@PostMapping("/editora/tradicional")
	public ResponseEntity<?> postEditora(@Validated @RequestBody Editora editora) {
		repository.save(editora);
		
		return new ResponseEntity<>("Cadastrado com sucesso!", HttpStatus.OK);
	}
	
	@PostMapping("/editora")
	public ResponseEntity<?> postEditoraDTO(@Validated @RequestBody EditoraCadastroDTO dto) {
		Editora editora = dto.toEditora();
		
		repository.save(editora);
		
		return new ResponseEntity<>("Cadastrado com sucesso", HttpStatus.OK);

	}
}
