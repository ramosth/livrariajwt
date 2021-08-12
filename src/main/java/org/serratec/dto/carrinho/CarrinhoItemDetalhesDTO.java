package org.serratec.dto.carrinho;

import org.serratec.models.CarrinhoItem;

public class CarrinhoItemDetalhesDTO {

	private String livro;
	private Integer quantidade;
	private Double preco;

	public CarrinhoItemDetalhesDTO(CarrinhoItem carrinhoItem) {
		this.livro = carrinhoItem.getLivro().getCodigo();
		this.quantidade = carrinhoItem.getQuantidade();
		this.preco = carrinhoItem.getLivro().getValor();
	}
	
	public String getLivro() {
		return livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getPreco() {
		return preco;
	}
	
	
	
}
