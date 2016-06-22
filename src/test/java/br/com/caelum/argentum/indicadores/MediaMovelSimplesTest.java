package br.com.caelum.argentum.indicadores;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelSimplesTest {

	@Test
	public void sequenciaSimplesDeCandles() throws Exception {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 3, 4, 5, 4,
				3);
		Indicador mms = new MediaMovelSimples(new IndicadorFechamento());

		assertEquals(2.0, mms.calcula(2, serie),      0.000001);
		assertEquals(3.0, mms.calcula(3, serie),      0.000001);
		assertEquals(10.0 / 3, mms.calcula(4, serie), 0.000001);
		assertEquals(11.0 / 3, mms.calcula(5, serie), 0.000001);
		assertEquals(4.0, mms.calcula(6, serie),      0.000001);
		assertEquals(13.0 / 3, mms.calcula(7, serie), 0.000001);
		assertEquals(4.0, mms.calcula(8, serie),      0.000001);
	}

}
