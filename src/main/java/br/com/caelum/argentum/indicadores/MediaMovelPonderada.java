package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderada implements Indicador{

	private Indicador outroIndicador;

	public MediaMovelPonderada(Indicador outroIndicador) {
		this.outroIndicador = outroIndicador;
	}
	
	public double calcula(int posicao, SerieTemporal serie){
		double soma= 0.0;
		int peso = 3;
		
		for(int i=posicao;i>(posicao-3);i--){
			soma+=outroIndicador.calcula(i, serie) * peso;
			peso--;
		}
		
		return soma/6;
	}

	@Override
	public String toString() {
		return "MMP "+outroIndicador;
	}
	
}
