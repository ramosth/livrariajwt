package org.serratec.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull //precisa ter conteudo
	@NotBlank //a String não pode ser vazia
	private String nome;
	@NotNull
	@NotBlank
	private String codigo;
	private LocalDate dataLancamento;
	
	@ManyToMany //muitos livros, tem muitos autores
	private List<Autor> autores = new ArrayList<>();
	
	@Enumerated(EnumType.ORDINAL) //no caso do String, pode ser que precise trocar de nome, e bagunce os antigos já cadastrados.
	private Categoria categoria;
	
	@ManyToOne
	private Editora editora;
	
	private Integer numeroPaginas;
	private Double valor;
	
	@JsonIgnore
	@OneToMany(mappedBy = "livro")
	private List<Classificacao> classificacoes = new ArrayList<>();
	
	private byte[] capa;
	
	private byte[] pdf; 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<Classificacao> getClassificacoes() {
		return classificacoes;
	}
	public void setClassificacoes(List<Classificacao> classificacoes) {
		this.classificacoes = classificacoes;
	}
	public byte[] getCapa() {
		return capa;
	}
	public void setCapa(byte[] capa) {
		this.capa = capa;
	}
	public byte[] getPdf() {
		return pdf;
	}
	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}
	
}
