package org.serratec.dto.carrinho;

import org.serratec.exceptions.VendaException;
import org.serratec.models.Carrinho;
import org.serratec.models.CarrinhoItem;
import org.serratec.models.Leitor;
import org.serratec.models.Livro;
import org.serratec.repository.CarrinhoRepository;
import org.serratec.repository.LeitorRepository;
import org.serratec.repository.LivroRepository;

public class CarrinhoAtualizarItemDTO {

	private String emailLeitor;
	private String codigoLivro;
	private Integer quantidade;

	// TODO ALterar exceptions
	public Carrinho toCarrinho(LeitorRepository leitorRepository, CarrinhoRepository carrinhoRepository)
			throws VendaException {

		Leitor leitor = leitorRepository.findByEmail(this.emailLeitor)
				.orElseThrow(() -> new VendaException("E-mail não encontrado!"));

		Carrinho carrinho = carrinhoRepository.findByLeitor(leitor)
				.orElse( new Carrinho());

		carrinho.setLeitor(leitor);
		
		return carrinho;
	}

	// FIXME Corrigir as exceções para suas respectivas classes ou utilizar um
	// CarrinhoException para ambos.
	public CarrinhoItem toItem(LivroRepository livroRepository) throws VendaException {

		CarrinhoItem carrinhoItem = new CarrinhoItem();

		carrinhoItem.setQuantidade(this.quantidade);

		Livro livro = livroRepository.findByCodigo(this.codigoLivro)
				.orElseThrow(() -> new VendaException("Código do livro inexistente."));

		carrinhoItem.setLivro(livro);

		return carrinhoItem;
	}

	public String getEmailLeitor() {
		return emailLeitor;
	}

	public String getCodigoLivro() {
		return codigoLivro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

}
