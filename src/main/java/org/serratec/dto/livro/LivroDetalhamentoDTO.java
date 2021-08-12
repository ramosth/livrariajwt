package org.serratec.dto.livro;

import java.util.ArrayList;
import java.util.List;

import org.serratec.dto.autor.AutorSimplesDTO;
import org.serratec.dto.classificacao.ClassificacaoSimplificadaDTO;
import org.serratec.models.Autor;
import org.serratec.models.Classificacao;
import org.serratec.models.Livro;

public class LivroDetalhamentoDTO {

	private String nome;
	private List<ClassificacaoSimplificadaDTO> classificacoes;
	private List<AutorSimplesDTO> autores;
	
	public LivroDetalhamentoDTO(Livro livro) {
		this.nome = livro.getNome();
		this.classificacoes = new ArrayList<>();
		this.autores = new ArrayList<>();
		
		for (Classificacao c : livro.getClassificacoes()) {
			this.classificacoes.add(new ClassificacaoSimplificadaDTO(c));
		}
		for (Autor a : livro.getAutores()) {
			this.autores.add(new AutorSimplesDTO(a));
		}
	}
	public String getNome() {
		return nome;
	}
	public List<ClassificacaoSimplificadaDTO> getClassificacoes() {
		return classificacoes;
	}
	public List<AutorSimplesDTO> getAutores() {
		return autores;
	}
	
	
}
