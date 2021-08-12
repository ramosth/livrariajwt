package org.serratec.dto;

import org.serratec.models.Endereco;
import org.springframework.web.client.RestTemplate;

public class EnderecoCompletoDTO {
	
	private String completo;
	private String uf;

	public EnderecoCompletoDTO(Endereco endereco) {
		
		String uri = "https://viacep.com.br/ws/" + endereco.getCep() + "/json/";

	    RestTemplate rest = new RestTemplate();    
	    EnderecoViaCepDTO viaCep = rest.getForObject(uri, EnderecoViaCepDTO.class);
	    
	    this.completo = viaCep.getLogradouro() + ", " 
	    		+ endereco.getNumeroResidencia() + ", " + viaCep.getBairro();
	    this.uf = viaCep.getUf();
	}

	public String getCompleto() {
		return completo;
	}

	public String getUf() {
		return uf;
	}
	
}
