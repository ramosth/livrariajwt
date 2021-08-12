package org.serratec.dto.leitor;

import java.time.LocalDate;

import org.serratec.dto.EnderecoCompletoDTO;
import org.serratec.models.Leitor;

public class LeitorCompletoDTO {

	private String nome;
	private LocalDate dataNascimento;
	private EnderecoCompletoDTO endereco;
	
	public LeitorCompletoDTO(Leitor leitor) {
		this.nome = leitor.getNome();
		this.dataNascimento = leitor.getDataNascimento();
				
	    this.endereco = new EnderecoCompletoDTO(leitor.getEndereco());
	}

	public EnderecoCompletoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoCompletoDTO endereco) {
		this.endereco = endereco;
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

}
