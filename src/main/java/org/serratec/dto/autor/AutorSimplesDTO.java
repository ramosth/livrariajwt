package org.serratec.dto.autor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.serratec.models.Autor;

public class AutorSimplesDTO {
	
	@NotNull
	@NotBlank
	private String nome;

	public AutorSimplesDTO(Autor obj) {
		super();
		this.nome = obj.getNome();
	}

	public String getNome() {
		return nome;
	}
}

