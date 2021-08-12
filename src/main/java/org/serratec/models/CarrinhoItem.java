package org.serratec.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CarrinhoItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne
	private Carrinho carrinho;

	@ManyToOne
	private Livro livro;
	private Integer quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorTotalSemDesconto() {

		Double total = (this.getPreco() * this.quantidade);

		return total;
	}

	public Double getValorTotalComDesconto(CupomDesconto cupom) {

		Double total = this.getValorTotalSemDesconto();

		if (cupom != null) {
			List<Categoria> categorias = cupom.getCategoria();

			if (categorias.size()>0) {
				
				for (Categoria c : categorias) {
					if(c.equals(this.getLivro().getCategoria())) {
						total = total * (1 - cupom.getValor());
						break;
					}	
				}	
			} else {
				total = total * (1 - cupom.getValor());
			}	
		}

		return total;
	}

	public Double getPreco() {
		return getLivro().getValor();
	}
}
