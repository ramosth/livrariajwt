package org.serratec.dto.editora;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.serratec.models.Editora;

public class EditoraSimplesDTO {
	
	@NotNull
	@NotBlank
	private String nome;	
	
	public EditoraSimplesDTO(Editora editora) {
		super();
		this.nome = editora.getNome();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
