package org.serratec.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VendaItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne
	private Venda venda;

	@ManyToOne
	private Livro livro;
	private Double preco;
	private Integer quantidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorTotalSemDesconto() {

		Double total = (this.preco * this.quantidade);

		return total;
	}

	public Double getValorTotalComDesconto() {

		Double total = this.getValorTotalSemDesconto();

		if (venda.getCupom() != null) {
			List<Categoria> categorias = venda.getCupom().getCategoria();

			if (categorias.size()>0) {
				
				for (Categoria c : categorias) {
					if(c.equals(this.getLivro().getCategoria())) {
						total = total * (1 - venda.getCupom().getValor());
						break;
					}	
				}	
			} else {
				total = total * (1 - venda.getCupom().getValor());
			}	
		}

		return total;
	}
}
