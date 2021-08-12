package org.serratec.dto.leitor;

import org.serratec.models.Leitor;

public class LeitorSimplificadoDTO {
	
	private String nome;
//	private LocalDate dataNascimento;
	private String email;
	
	public LeitorSimplificadoDTO(Leitor leitor) {
		this.nome = leitor.getNome();
//		this.dataNascimento = leitor.getDataNascimento();
		this.email = leitor.getEmail();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
//	public LocalDate getDataNascimento() {
//		return dataNascimento;
//	}
//	public void setDataNascimento(LocalDate dataNascimento) {
//		this.dataNascimento = dataNascimento;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
