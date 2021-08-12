package org.serratec.dto.livro;

import java.util.List;

import org.serratec.models.Livro;

public class LivroClassificacaoDTO {

	private String codigoLivro;
	private String nomeLivro;
	private List<String> classificacao;
	
	public LivroClassificacaoDTO(Livro livro) {
		this.codigoLivro = livro.getCodigo();
		this.nomeLivro = livro.getNome();
//		this.classificacao = 
		
//		for (String classific: classificacao) {
//			if(classific.g .equals(livro.getCodigo()))
//		}
	}

	public String getCodigoLivro() {
		return codigoLivro;
	}

	public void setCodigoLivro(String codigoLivro) {
		this.codigoLivro = codigoLivro;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public List<String> getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(List<String> classificacao) {
		this.classificacao = classificacao;
	}
}
