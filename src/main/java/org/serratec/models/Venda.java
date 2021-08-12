
package org.serratec.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String protocolo;

	@ManyToOne
	private Leitor leitor;

	private LocalDateTime dataPedido;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
	private List<VendaItem> itens = new ArrayList<>();

	@Enumerated(EnumType.ORDINAL)
	private Pagamento formaDePagamento;

	@Enumerated(EnumType.ORDINAL)
	private CupomDesconto cupom;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
	private List<VendaStatus> status = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public Leitor getLeitor() {
		return leitor;
	}

	public void setLeitor(Leitor leitor) {
		this.leitor = leitor;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime localDateTime) {
		this.dataPedido = localDateTime;
	}

	public Pagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(Pagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public List<VendaItem> getItens() {
		return itens;
	}

	public void setItens(List<VendaItem> itens) {
		this.itens = itens;
	}

	public CupomDesconto getCupom() {
		return cupom;
	}

	public void setCupom(CupomDesconto cupom) {
		this.cupom = cupom;
	}

	
	
	public List<VendaStatus> getVendaStatus() {
		return status;
	}

	public void setVendaStatus(List<VendaStatus> status) {
		this.status = status;
	}

	public Double getTotalSemDesconto() {
		Double total = 0.00;

		for (VendaItem v : itens) {
			total += v.getValorTotalSemDesconto();
		}

		return total;
	}

	public Double getTotalComDesconto() {
		Double total = 0.00;

		for (VendaItem v : itens) {
			total += v.getValorTotalComDesconto();
		}

		return total;
	}

	/**
	 * Função que retorna o valor total do frete com base no..
	 * 
	 * @return Valor total do Frete
	 */
	public Double getTotalFrete(Frete frete) {
		Double valorFreteMinimo = frete.getValorMinimo();
		Double valorFretePorLivro = 0.00;

		for (VendaItem v : itens) {
			valorFretePorLivro += v.getQuantidade() * frete.getValorPorLivro();
		}

		return Math.max(valorFreteMinimo, valorFretePorLivro);
	}

	public Double getTotalASerPago(Frete frete) {
		Double valorPago = getTotalComDesconto();
		Double valorFrete = getTotalFrete(frete);

		return valorPago + valorFrete;
	}

	public String gerarProtocolo() {
		if (this.protocolo == null || this.protocolo.isBlank()) {

			LocalDateTime agora = LocalDateTime.now();
			Random randomico = new Random();
			String codigo = "v";
			codigo += agora.getYear();
			codigo += agora.getMonth();
			codigo += agora.getDayOfMonth();
			codigo += agora.getHour();
			codigo += agora.getMinute();
			codigo += agora.getSecond();

			for (int i = 0; i < 10; i++) {
				codigo += randomico.nextInt(10);
			}
			this.protocolo = codigo;
		}

		return this.protocolo;
	}
}
