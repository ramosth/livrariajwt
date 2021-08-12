package org.serratec.models;
import java.util.ArrayList;
import java.util.List;

public enum CupomDesconto {
	
	CUPOM10(0.1, List.of(Categoria.SUSPENSE, Categoria.AUTOAJUDA)),
	CUPOM20(0.2, List.of()),
	CUPOM50(0.5, List.of(Categoria.ROMANCE, Categoria.ACAO, Categoria.INVESTIGACAO, Categoria.CIENTIFICO));
	
	private Double valor;
	private List<Categoria> categorias = new ArrayList<>();
	
	private CupomDesconto( Double valor, List<Categoria> categorias) {
		this.valor = valor;
		this.categorias = categorias;
	}

	public Double getValor() {
		return valor;
	}

	public List<Categoria> getCategoria() {
		return categorias;
	}

	
}

	
