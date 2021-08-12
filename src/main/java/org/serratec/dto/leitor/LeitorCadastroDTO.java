package org.serratec.dto.leitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.serratec.models.Categoria;
import org.serratec.models.Endereco;
import org.serratec.models.Genero;
import org.serratec.models.Leitor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LeitorCadastroDTO {
	
	@NotNull
	@NotBlank
	private String nome;	
	
	@NotNull
	private LocalDate dataNascimento;
	
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	private String senha;
	
	@NotNull
	private Genero genero;
	
	private List<Categoria> categoriaFavorita = new ArrayList<>();
	
	private Optional<Endereco> endereco;	
	
	public Leitor toLeitor() {
		
		Leitor leitor = new Leitor();
		leitor.setNome(this.nome);
		leitor.setDataNascimento(this.dataNascimento);
		leitor.setGenero(this.genero);
		leitor.setEmail(this.email);
		leitor.setCategoriaFavorita(this.categoriaFavorita);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCodificada = encoder.encode(this.senha);
		
		leitor.setSenha(senhaCodificada);

		if (!this.endereco.isEmpty())
			leitor.setEndereco(this.endereco.get());
		
		return leitor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<Categoria> getCategoriaFavorita() {
		return categoriaFavorita;
	}

	public void setCategoriaFavorita(List<Categoria> categoriaFavorita) {
		this.categoriaFavorita = categoriaFavorita;
	}

	public Optional<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(Optional<Endereco> endereco) {
		this.endereco = endereco;
	}

}

