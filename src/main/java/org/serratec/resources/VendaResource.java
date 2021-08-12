package org.serratec.resources;

import java.time.LocalDateTime;

import org.serratec.dto.venda.VendaAtualizacaoStatusDTO;
import org.serratec.dto.venda.VendaDetalhesDTO;
import org.serratec.exceptions.VendaException;
import org.serratec.models.Venda;
import org.serratec.models.VendaStatus;
import org.serratec.repository.VendaRepository;
import org.serratec.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendaResource {

	@Autowired
	VendaRepository vendaRepository;
	
	@Autowired
	EmailService emailService;

	@GetMapping("/venda/detalhada/{protocolo}")
	public ResponseEntity<?> getDetalhada(@PathVariable String protocolo) {

		try {
			Venda venda = vendaRepository.findByProtocolo(protocolo).orElseThrow(() -> new VendaException("Venda não encontrada!"));

			return new ResponseEntity<>(new VendaDetalhesDTO(venda), HttpStatus.OK);

		} catch (VendaException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/venda/status")
	public ResponseEntity<?> putStatus(@RequestBody VendaAtualizacaoStatusDTO dto) {

		try {
			Venda venda = vendaRepository.findByProtocolo(dto.getProtocolo()).orElseThrow(() -> new VendaException("Venda não encontrada!"));

			VendaStatus corrente = venda.getVendaStatus().get(venda.getVendaStatus().size() - 1);

			VendaStatus nova = new VendaStatus();
			nova.setVenda(venda);
			nova.setStatus(dto.getStatus());
			nova.setDataStatus(LocalDateTime.now());

			vendaRepository.save(venda);
			emailService.enviar("Atualização do status do pedido","Status da venda alterado de " + corrente.getStatus() + " para " + nova.getStatus(), nova.getVenda().getLeitor().getEmail());

			return new ResponseEntity<>(
					"Status da venda alterado de " + corrente.getStatus() + " para " + nova.getStatus(), HttpStatus.OK);

		} catch (VendaException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
}
