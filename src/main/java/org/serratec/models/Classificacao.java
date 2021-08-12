package org.serratec.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Classificacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Estrela estrela; // de 1 a 5 estrelas
	private String comentario;
	private Recomendacao recomenda;
	private LocalDateTime dataClassificacao;
	
	@ManyToOne
	private Leitor leitor;
	
	@ManyToOne
	private Livro livro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Estrela getEstrela() {
		return estrela;
	}

	public void setEstrela(Estrela estrela) {
		this.estrela = estrela;
	}

	public Recomendacao getRecomenda() {
		return recomenda;
	}

	public void setRecomenda(Recomendacao recomenda) {
		this.recomenda = recomenda;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDateTime getDataClassificacao() {
		return dataClassificacao;
	}

	public void setDataClassificacao(LocalDateTime dataClassificacao) {
		this.dataClassificacao = dataClassificacao;
	}

	public Leitor getLeitor() {
		return leitor;
	}

	public void setLeitor(Leitor leitor) {
		this.leitor = leitor;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivros(Livro livro) {
		this.livro = livro;
	}
	
	
}
