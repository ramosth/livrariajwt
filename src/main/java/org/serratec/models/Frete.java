package org.serratec.models;

/* frete POR ESTADO: 
 * 	valor minimo
 *  cobrado por livro
 * 
 * Exemplo: 
 * Rio de Janeiro = $2.00/livro ou $25.00 de entrega minima
 * 
 * Caso 01:
 * Cliente comprou 3 livros
 * Valor de frete: $25.00
 * 
 * Caso 02:
 * Cliente comprou 15 livros
 * Valor do frete: $30.00
 * 
 * 
 * */

public enum Frete {
	
	AC(20.00, 3.50),
	AL(21.00, 3.50),
	AP(11.00, 3.50),
	AM(18.00, 3.50),
	BA(15.00, 3.50),
	CE(10.00, 3.50),
	DF(10.00, 3.50),
	ES(12.00, 3.50),
	GO(15.00, 3.50),
	MA(15.00, 3.50),
	MT(17.00, 3.50),
	MS(14.00, 3.50),
	MG(18.00, 3.50),
	PA(13.00, 3.50),
	PB(13.00, 3.50),
	PR(10.00, 3.50),
	PE(12.00, 3.50),
	PI(15.00, 3.50),
	RJ(20.00, 3.50),
	RN(11.00, 3.50),
	RS(10.00, 3.50),
	RO(15.00, 3.50),
	RR(17.00, 3.50),
	SC(10.00, 3.50),
	SP(11.00, 3.50),
	SE(22.00, 3.50),
	TO(14.00, 3.50);
	
	private Double valorMinimo;
	private Double valorPorLivro;
	
	private Frete(Double valorMinimo, Double valorPorLivro) {
		this.valorMinimo = valorMinimo;
		this.valorPorLivro = valorPorLivro;
	}

	public Double getValorMinimo() {
		return valorMinimo;
	}

	public Double getValorPorLivro() {
		return valorPorLivro;
	}

	
}

	
