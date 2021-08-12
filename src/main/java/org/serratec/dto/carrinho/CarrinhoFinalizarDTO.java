package org.serratec.dto.carrinho;

import org.serratec.exceptions.VendaException;
import org.serratec.models.Carrinho;
import org.serratec.models.CupomDesconto;
import org.serratec.models.Leitor;
import org.serratec.models.Pagamento;
import org.serratec.repository.CarrinhoRepository;
import org.serratec.repository.LeitorRepository;

public class CarrinhoFinalizarDTO {

	private String emailLeitor;
	private CupomDesconto cupom;
	private Pagamento pagamento;

	// TODO ALterar exceptions
	public Carrinho toCarrinho(LeitorRepository leitorRepository, CarrinhoRepository carrinhoRepository)
			throws VendaException {

		Leitor leitor = leitorRepository.findByEmail(this.emailLeitor)
				.orElseThrow(() -> new VendaException("E-mail não encontrado!"));

		Carrinho carrinho = carrinhoRepository.findByLeitor(leitor).orElseThrow(() -> new VendaException ("Carrinho não encontado"));
		
		return carrinho;
	}

	public String getEmailLeitor() {
		return emailLeitor;
	}


	public CupomDesconto getCupom() {
		return this.cupom;
	}

	public Pagamento getPagamento() {
		return this.pagamento;
	}

}
