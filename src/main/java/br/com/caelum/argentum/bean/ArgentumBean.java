package br.com.caelum.argentum.bean;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.ChartModel;

import br.com.caelum.argentum.grafico.GeradorModeloGrafico;
import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.indicadores.IndicadorFechamento;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.ws.ClienteWebService;

@ViewScoped
@ManagedBean
public class ArgentumBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Negociacao> negociacoes;
	private ChartModel modeloGrafico;
	private String nomeMedia;
	private String nomeIndicadorBase;

	private Date filtroDataDe;
	private Date filtroDataAte;

	public ArgentumBean() {
		this.negociacoes = new ClienteWebService().getNegociacoes();
		geraGrafico();
	}

	public void geraGrafico() {
		List<Candle> candles = new CandlestickFactory().constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);
		GeradorModeloGrafico geradorGrafico = new GeradorModeloGrafico(serie, 2, serie.getUltimaPosicao());
		geradorGrafico.plotaMediaMovelSimples(defineIndicador());
		this.modeloGrafico = geradorGrafico.getModeloGrafico();
	}

	public void filtra() {
		aplicaFiltro();
		geraGrafico();
	}

	private void aplicaFiltro() {
		Calendar de = Calendar.getInstance();
		if (filtroDataDe != null) {
			de.setTime(filtroDataDe);
		} else {
			de.add(Calendar.MONTH, -12);
		}
		
		Calendar ate = Calendar.getInstance();
		if (filtroDataAte != null) {
			ate.setTime(filtroDataAte);
		} else {
			ate.add(Calendar.MONTH, 12);
		}
		
		negociacoes = new ClienteWebService().getNegociacoes();
		
		negociacoes = negociacoes
						.stream()
						.filter(n -> n.getData().after(de) && n.getData().before(ate))
						.collect(Collectors.toList());
	}
	
	private Indicador defineIndicador() {
		if (nomeIndicadorBase == null || nomeIndicadorBase.isEmpty() || nomeMedia == null || nomeMedia.isEmpty()) {
			return new MediaMovelSimples(new IndicadorFechamento());
		}

		String pacote = "br.com.caelum.argentum.indicadores.";
		try {
			Class<?> classeIndicadorBase = Class.forName(pacote + nomeIndicadorBase);
			Indicador indicadorBase = (Indicador) classeIndicadorBase.newInstance();

			Class<?> classeMedia = Class.forName(pacote + nomeMedia);
			Constructor<?> construtorMedia = classeMedia.getConstructor(Indicador.class);
			Indicador indicador = (Indicador) construtorMedia.newInstance(indicadorBase);
			return indicador;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public ChartModel getModeloGrafico() {
		return modeloGrafico;
	}

	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

	public String getNomeIndicadorBase() {
		return nomeIndicadorBase;
	}

	public String getNomeMedia() {
		return nomeMedia;
	}

	public void setNomeIndicadorBase(String nomeIndicadorBase) {
		this.nomeIndicadorBase = nomeIndicadorBase;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

	public Date getFiltroDataDe() {
		return filtroDataDe;
	}

	public void setFiltroDataDe(Date filtroDataDe) {
		this.filtroDataDe = filtroDataDe;
	}

	public Date getFiltroDataAte() {
		return filtroDataAte;
	}

	public void setFiltroDataAte(Date filtroDataAte) {
		this.filtroDataAte = filtroDataAte;
	}

}
