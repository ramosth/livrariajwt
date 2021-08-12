package org.serratec.resources;

import java.time.LocalDateTime;
import java.util.Optional;

import org.serratec.dto.carrinho.CarrinhoAtualizarItemDTO;
import org.serratec.dto.carrinho.CarrinhoDetalhesDTO;
import org.serratec.dto.carrinho.CarrinhoFinalizarDTO;
import org.serratec.exceptions.VendaException;
import org.serratec.models.Carrinho;
import org.serratec.models.CarrinhoItem;
import org.serratec.models.CupomDesconto;
import org.serratec.models.Leitor;
import org.serratec.models.StatusPedido;
import org.serratec.models.Venda;
import org.serratec.models.VendaItem;
import org.serratec.models.VendaStatus;
import org.serratec.repository.CarrinhoRepository;
import org.serratec.repository.LeitorRepository;
import org.serratec.repository.LivroRepository;
import org.serratec.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarrinhoResource {

	@Autowired
	CarrinhoRepository carrinhoRepository;
	
	@Autowired
	LeitorRepository leitorRepository;
	
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	VendaRepository vendaRepository;
	
	@GetMapping("/carrinho/{email}")
	public ResponseEntity<?> getDetalhes (@PathVariable String email, @RequestParam (required = false) CupomDesconto cupom){
		
		Optional<Leitor> leitor = leitorRepository.findByEmail(email);
		
		if (leitor.isEmpty())
			return new ResponseEntity<>("E-mail não encontrado", HttpStatus.NOT_FOUND);
		
		Optional<Carrinho> carrinho = carrinhoRepository.findByLeitor(leitor.get());
		
		if (carrinho.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		return new ResponseEntity<>(new CarrinhoDetalhesDTO(carrinho.get(), cupom), HttpStatus.OK);	
	}
	
	@PutMapping("/carrinho")
	public ResponseEntity<?> atualizarCarrinho(@RequestBody CarrinhoAtualizarItemDTO dto){
		
		try {
			Carrinho carrinho = dto.toCarrinho(leitorRepository, carrinhoRepository);
			
			for (CarrinhoItem i : carrinho.getItens()) {
				if (i.getLivro().getCodigo().equals(dto.getCodigoLivro())) {
					if (dto.getQuantidade() == 0) {
						carrinho.getItens().remove(i);	
					}else {
						i.setQuantidade(dto.getQuantidade());
					}
					
					if(carrinho.getItens().isEmpty()) {
						carrinhoRepository.delete(carrinho);
						return new ResponseEntity<>("Carrinho esvaziado", HttpStatus.OK);
					} else {
						carrinhoRepository.save(carrinho);
						return new ResponseEntity<>("Item atualizado com sucesso!", HttpStatus.OK);
					}	
				}
			}
			
			CarrinhoItem carrinhoItem = dto.toItem(livroRepository);
			carrinhoItem.setCarrinho(carrinho);
			
			carrinho.getItens().add(carrinhoItem);
			carrinhoRepository.save(carrinho);
			
			return new ResponseEntity<>("Item adicionado com sucesso.", HttpStatus.OK);
			
		}catch (VendaException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/carrinho/finalizar")
	public ResponseEntity<?> finalizarCarrinho(@RequestBody CarrinhoFinalizarDTO dto) {
		
		try {
			Carrinho carrinho = dto.toCarrinho(leitorRepository, carrinhoRepository);
			Venda venda = new Venda();
			venda.setLeitor(carrinho.getLeitor());
			venda.gerarProtocolo();
			venda.setCupom(dto.getCupom());
			venda.setDataPedido(LocalDateTime.now());
			venda.setFormaDePagamento(dto.getPagamento());
			
			for (CarrinhoItem c : carrinho.getItens()) {
				VendaItem vendaItem = new VendaItem();
				vendaItem.setVenda(venda);
				vendaItem.setLivro(c.getLivro());
				vendaItem.setQuantidade(c.getQuantidade());
				vendaItem.setPreco(c.getPreco());
				venda.getItens().add(vendaItem);
			}
			
			VendaStatus nova = new VendaStatus();
			nova.setVenda(venda);
			nova.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
			nova.setDataStatus(LocalDateTime.now());
			
			venda.getVendaStatus().add(nova);
			
			vendaRepository.save(venda);
			return new ResponseEntity<>("Venda efetuada com sucesso. Protocolo: " + venda.getProtocolo() , HttpStatus.OK);
				
		} catch (VendaException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
}
