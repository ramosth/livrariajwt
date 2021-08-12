package org.serratec.dto.livro;

import org.serratec.dto.editora.EditoraSimplesDTO;
import org.serratec.models.Categoria;
import org.serratec.models.Livro;

public class LivroDetalheDTO {
	
	private Long id;
	private String nome;
	private String codigo;
//	private List<Autor> autores;
	private Categoria categoria;
	private EditoraSimplesDTO editora;
	private Integer numeroPaginas;
	private Double valor;
	
	public LivroDetalheDTO(Livro livro) {
		super();
		this.setId(livro.getId());
		this.nome = livro.getNome();
		this.codigo = livro.getCodigo();
//		this.autores = livro.getAutores();
		this.categoria = livro.getCategoria();
		this.editora = new EditoraSimplesDTO(livro.getEditora());
		this.numeroPaginas = livro.getNumeroPaginas();
		this.valor = livro.getValor();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
	
	public EditoraSimplesDTO getEditora() {
		return editora;
	}

	public void setEditora(EditoraSimplesDTO editora) {
		this.editora = editora;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public List<Autor> getAutores() {
//		return autores;
//	}
//
//	public void setAutores(List<Autor> autores) {
//		this.autores = autores;
//	}
}
