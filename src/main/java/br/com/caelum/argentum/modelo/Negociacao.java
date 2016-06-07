package br.com.caelum.argentum.modelo;

import java.io.Serializable;
import java.util.Calendar;

public final class Negociacao implements Serializable{

	private static final long serialVersionUID = 1L;

	private final double preco;
	private final int quantidade;
	private final Calendar data;

	public Negociacao(double preco, int quantidade, Calendar data) {
		
		if (data == null) {
			throw new IllegalArgumentException("data nao pode ser nula");
		}
		
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = (Calendar) data.clone();
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		return (Calendar) data.clone();
	}

	public double getVolume() {
		return preco * quantidade;
	}

	public boolean isMesmoDia(Calendar outraData) {
		return this.data.get(Calendar.DATE) == outraData.get(Calendar.DATE) &&
				   this.data.get(Calendar.MONTH) == outraData.get(Calendar.MONTH) &&
				   this.data.get(Calendar.YEAR) == outraData.get(Calendar.YEAR);
	}

}
