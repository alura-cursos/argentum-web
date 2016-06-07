package br.com.caelum.argentum.modelo;

import java.util.List;

public class SerieTemporal {

	private final List<Candle> candles;
	
	public SerieTemporal(List<Candle> candles) {
		this.candles = candles;
	}
	
	public Candle getCandle(int i) {
		return this.candles.get(i);
	}
	
	public int getUltimaPosicao(){
		return this.candles.size()-1;
	}
}
