package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class NegociacaoTest {
	
	@Test
	public void dataDaNegociacaoEhImutavel() {
		Calendar data = Calendar.getInstance();
		data.set(Calendar.DAY_OF_MONTH, 15);
		Negociacao negociacao = new Negociacao(10, 5, data);

		negociacao.getData().set(Calendar.DAY_OF_MONTH, 20);

		Assert.assertEquals(15, negociacao.getData().get(Calendar.DAY_OF_MONTH));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(10, 5, null);
	}
	
	@Test
	public void mesmoMilissegundoEhDoMesmoDia() {
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();

		Negociacao negociacao = new Negociacao(40.0, 100, agora);
		Assert.assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}

	@Test
	public void comHorariosDiferentesEhNoMesmoDia() {
		
		// usando GregorianCalendar(ano, mes, dia, hora, minuto)
		Calendar manha = new GregorianCalendar(2011, 10, 20, 8, 30);
		Calendar tarde = new GregorianCalendar(2011, 10, 20, 15, 30);

		Negociacao negociacao = new Negociacao(40.0, 100, manha);
		Assert.assertTrue(negociacao.isMesmoDia(tarde));
	}
	
	@Test
	public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia() {
		Calendar junho = new GregorianCalendar(2011, 5, 20);
		Calendar setembro = new GregorianCalendar(2011, 8, 20);

		Negociacao negociacao = new Negociacao(40.0, 100, junho);
		Assert.assertFalse(negociacao.isMesmoDia(setembro));
	}
	
	@Test
	public void mesmoDiaEMesMasAnosDiferentesNaoSaoDoMesmoDia() {
		Calendar maio2011 = new GregorianCalendar(2011, 4, 20);
		Calendar maio2012 = new GregorianCalendar(2012, 4, 20);

		Negociacao negociacao = new Negociacao(40.0, 100, maio2011);
		Assert.assertFalse(negociacao.isMesmoDia(maio2012));
	}
	

}
