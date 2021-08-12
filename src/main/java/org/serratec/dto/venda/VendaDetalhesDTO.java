package org.serratec.dto.venda;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.serratec.dto.EnderecoCompletoDTO;
import org.serratec.dto.leitor.LeitorSimplificadoDTO;
import org.serratec.models.CupomDesconto;
import org.serratec.models.Pagamento;
import org.serratec.models.StatusPedido;
import org.serratec.models.Venda;
import org.serratec.models.VendaItem;

public class VendaDetalhesDTO {

	private String codigoVenda;
	private Pagamento pagamento;
	private CupomDesconto cupom;
	private LeitorSimplificadoDTO leitor;
	private LocalDateTime dataPedido;
	private EnderecoCompletoDTO enderecoEntrega;
	private Double totalSemDesconto;
	private Double totalComDesconto;
	private Double totalFrete;
	private Double totalASerPago;

	private List<VendaItem> itens = new ArrayList<>();

	private static DecimalFormat DF = new DecimalFormat("#.##");

	public VendaDetalhesDTO(Venda venda) {
		this.codigoVenda = venda.getProtocolo();
		this.pagamento = venda.getFormaDePagamento();
		this.cupom = venda.getCupom();
		this.leitor = new LeitorSimplificadoDTO(venda.getLeitor());
		this.dataPedido = venda.getDataPedido();

		this.itens = venda.getItens();
		this.totalSemDesconto = venda.getTotalSemDesconto();
		this.totalComDesconto = venda.getTotalComDesconto();
		
	}

	public String getCodigoVenda() {
		return codigoVenda;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public LeitorSimplificadoDTO getLeitor() {
		return leitor;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public EnderecoCompletoDTO getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public List<VendaItem> getItens() {
		return itens;
	}

	public CupomDesconto getCupom() {
		return cupom;
	}

	public Double getTotalSemDesconto() {
		return Double.valueOf(DF.format(this.totalSemDesconto));
	}

	public Double getTotalComDesconto() {
		return Double.valueOf(DF.format(this.totalComDesconto));
	}

//	public Double getTotalASerPago() {
//		return Double.valueOf(df.format(this.totalASerPago));
//	}
//
//	public Double getTotalFrete() {
//		return totalFrete;
//	}

}
