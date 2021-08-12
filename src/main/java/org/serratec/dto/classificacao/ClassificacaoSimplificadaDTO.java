package org.serratec.dto.classificacao;

import java.time.LocalDateTime;

import org.serratec.dto.leitor.LeitorSimplificadoDTO;
import org.serratec.models.Classificacao;

public class ClassificacaoSimplificadaDTO {
	
	private String comentario;
	private LocalDateTime dataClassificacao;
	private LeitorSimplificadoDTO leitor;
	
	public ClassificacaoSimplificadaDTO(Classificacao classificacao) {
		this.comentario = classificacao.getComentario();
		this.dataClassificacao = classificacao.getDataClassificacao();
		this.leitor = new LeitorSimplificadoDTO(classificacao.getLeitor());
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDateTime getDataClassificacao() {
		return dataClassificacao;
	}

	public void setDataClassificacao(LocalDateTime dataClassificacao) {
		this.dataClassificacao = dataClassificacao;
	}

	public LeitorSimplificadoDTO getLeitor() {
		return leitor;
	}

	public void setLeitor(LeitorSimplificadoDTO leitor) {
		this.leitor = leitor;
	}
	
}
