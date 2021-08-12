package org.serratec.dto.autor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.serratec.models.Autor;

public class AutorCadastroDTO {

	@NotNull
	@NotBlank
	private String codigo;
	
	@NotNull
	@NotBlank
	private String nome;	
	
	private String bio;
	
	public Autor toAutor() {
		
		Autor autor = new Autor();
		autor.setCodigo(this.codigo);
		autor.setNome(this.nome);
		autor.setBio(this.bio);
				
		return autor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

}

