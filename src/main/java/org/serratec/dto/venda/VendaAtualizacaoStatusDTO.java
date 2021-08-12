package org.serratec.dto.venda;

import org.serratec.models.StatusPedido;

public class VendaAtualizacaoStatusDTO {

	private String protocolo;
	private StatusPedido status;

	
	
	public String getProtocolo() {
		return protocolo;
	}
	public StatusPedido getStatus() {
		return status;
	}
	
	
}
