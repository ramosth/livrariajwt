
package org.serratec.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Carrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Leitor leitor;

	@OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CarrinhoItem> itens = new ArrayList<>(); 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Leitor getLeitor() {
		return leitor;
	}

	public void setLeitor(Leitor leitor) {
		this.leitor = leitor;
	}

	public List<CarrinhoItem> getItens() {
		return itens;
	}

	public void setItens(List<CarrinhoItem> itens) {
		this.itens = itens;
	}

	public Double getTotalSemDesconto() {
		Double total = 0.00;

		for (CarrinhoItem v : itens) {
			total += v.getValorTotalSemDesconto();
		}

		return total;
	}
	
	public Double getTotalComDesconto(CupomDesconto cupom) {
		Double total = 0.00;

		for (CarrinhoItem v : itens) {
			total += v.getValorTotalComDesconto(cupom);
		}

		return total;
	}
	
	public Double getTotalFrete(Frete frete) {
		
		Double valorFreteMinimo = frete.getValorMinimo();
		Double valorFretePorLivro = 0.00;
		
		for (CarrinhoItem v : itens) {
			valorFretePorLivro += v.getQuantidade() * frete.getValorPorLivro();
		}
		
		return Math.max(valorFreteMinimo, valorFretePorLivro);
	}
	
	public Double getTotalASerPago(Frete frete, CupomDesconto cupom) {
		Double valorPago = getTotalComDesconto(cupom);
		Double valorFrete = getTotalFrete(frete);

		return valorPago + valorFrete;
	}

}
