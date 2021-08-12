package org.serratec.dto.editora;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.serratec.models.Editora;

public class EditoraCadastroDTO {

	@NotNull
	@NotBlank
	private String codigo;
	
	@NotNull
	@NotBlank
	private String nome;	
	
	public Editora toEditora() {
		
		Editora editora = new Editora();
		editora.setCodigo(this.codigo);
		editora.setNome(this.nome);
				
		return editora;
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

}
