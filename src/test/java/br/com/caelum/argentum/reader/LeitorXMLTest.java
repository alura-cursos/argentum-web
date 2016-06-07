package br.com.caelum.argentum.reader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXMLTest {

    @Test
    public void testLeitorDeXMlCarregaListaDeNegocio() {
        String xmlDeTeste = 
        		"<list>" 
        		+ "    <negociacao>" 
        		+ "        <preco>43.5</preco>"
                + "        <quantidade>1000</quantidade>" 
        		+ "        <data>"
                + "            <time>1322233344455</time>" 
        		+ "        </data>" 
                + "    </negociacao>"
                + "</list>";

        LeitorXML leitor = new LeitorXML();

        InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
       
        List<Negociacao> negocios = leitor.carrega(xml);

        Assert.assertEquals(negocios.get(0).getPreco(), 43.5, 0.0001);
        Assert.assertEquals(negocios.get(0).getQuantidade(), 1000);
        Assert.assertEquals(negocios.size(), 1);
    }

}

