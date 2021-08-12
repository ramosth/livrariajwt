package org.serratec.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.dto.leitor.LeitorCadastroDTO;
import org.serratec.dto.leitor.LeitorCompletoDTO;
import org.serratec.dto.leitor.LeitorSimplificadoDTO;
import org.serratec.exceptions.LivroException;
import org.serratec.models.Leitor;
import org.serratec.repository.EnderecoRepository;
import org.serratec.repository.LeitorRepository;
import org.serratec.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeitorResource {

	@Autowired
	LeitorRepository repository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/leitor/todos/tradicional")
	public ResponseEntity<?> getTodosTradicional() {
		
		List<Leitor> todos = repository.findAll();
		return new ResponseEntity<>(todos, HttpStatus.OK);
	}
	
	@GetMapping("/leitor/todos")
	public ResponseEntity<?> getTodos() {
		List<Leitor> todos = repository.findAll();
		List<LeitorSimplificadoDTO> dtos = new ArrayList<>();
		
		for (Leitor leitor : todos) 
			dtos.add(new LeitorSimplificadoDTO(leitor));
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@GetMapping("/leitor/detalhe/{id}")
	public ResponseEntity<?> getDetalhe(@PathVariable Long id) {
		Optional<Leitor> optional = repository.findById(id);
		
		if (optional.isEmpty())
			return new ResponseEntity<>("Não encontrado", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(new LeitorCompletoDTO(optional.get()), HttpStatus.OK);
	}	
	
	@PostMapping("/leitor")
	public ResponseEntity<?> postLeitor(@Validated @RequestBody LeitorCadastroDTO dto) {
		
		Leitor leitor = dto.toLeitor();
		
		try {					
			repository.save(leitor);
			
			emailService.enviar("Bem vindo", "Seu e-mail foi cadastrado com sucesso", leitor.getEmail());
			
			return new ResponseEntity<>("Cadastrado com sucesso!", HttpStatus.OK);
		} catch (LivroException e) {
			if(repository.existsByEmail(leitor.getEmail()))
				return new ResponseEntity<>("já existe um usuario com este email", HttpStatus.BAD_REQUEST);
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}		
	}	
	
}
