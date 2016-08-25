package br.com.caelum.argentum.ws;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;

import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.reader.LeitorXML;

public class ClienteWebService {

	private static final String URL_WEBSERVICE = "http://argentumws.caelum.com.br/negociacoes";

	public List<Negociacao> getNegociacoes() {

		HttpURLConnection connection = null;

		try {
			URL url = new URL(URL_WEBSERVICE);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxylatam.indra.es", 8080));
			connection = (HttpURLConnection) url.openConnection(proxy);
			InputStream content = connection.getInputStream();
			return new LeitorXML().carrega(content);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			connection.disconnect();
		}
	}
}
