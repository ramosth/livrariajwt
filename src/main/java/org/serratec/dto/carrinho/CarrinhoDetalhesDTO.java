package org.serratec.dto.carrinho;

import java.util.ArrayList;
import java.util.List;

import org.serratec.dto.EnderecoCompletoDTO;
import org.serratec.models.Carrinho;
import org.serratec.models.CarrinhoItem;
import org.serratec.models.CupomDesconto;
import org.serratec.models.Frete;

public class CarrinhoDetalhesDTO {

	private String nomeLeitor;
	private List<CarrinhoItemDetalhesDTO> itens;
	private EnderecoCompletoDTO enderecoEntrega;
	private Double totalSemDesconto;
	private Double totalComDesconto;
	private Double totalFrete;
	private Double totalASerPago;
	
	public CarrinhoDetalhesDTO(Carrinho carrinho, CupomDesconto cupom) {
		this.nomeLeitor = carrinho.getLeitor().getNome();
		this.itens = new ArrayList<>();
		
		for (CarrinhoItem c : carrinho.getItens()) {
			this.itens.add(new CarrinhoItemDetalhesDTO(c));
		}
		
		this.enderecoEntrega = new EnderecoCompletoDTO(carrinho.getLeitor().getEndereco());
		
		Frete frete = Frete.valueOf(this.enderecoEntrega.getUf());
		
		this.totalSemDesconto = carrinho.getTotalSemDesconto();
		this.totalComDesconto = carrinho.getTotalComDesconto(cupom);
		this.totalASerPago = carrinho.getTotalASerPago(frete,cupom);
		this.totalFrete = carrinho.getTotalFrete(frete);
		
	}
	
	public String getNomeLeitor() {
		return nomeLeitor;
	}

	public List<CarrinhoItemDetalhesDTO> getItens() {
		return itens;
	}

	public EnderecoCompletoDTO getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public Double getTotalSemDesconto() {
		return totalSemDesconto;
	}

	public Double getTotalComDesconto() {
		return totalComDesconto;
	}

	public Double getTotalFrete() {
		return totalFrete;
	}

	public Double getTotalASerPago() {
		return totalASerPago;
	}

	
	
}
