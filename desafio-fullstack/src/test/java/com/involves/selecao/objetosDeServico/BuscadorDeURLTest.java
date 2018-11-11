package com.involves.selecao.objetosDeServico;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.involves.selecao.objetosDeServico.BuscadorDeURL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuscadorDeURLTest {

  @Test
  public void requisicaoComURLValida() throws Exception {
    BuscadorDeURL buscador = new BuscadorDeURL("https://www.google.com");
    Assert.assertTrue(buscador.call() instanceof String);
  }

  @Test(expected = java.net.UnknownHostException.class)
  public void requisicaoComURLInexistente() throws Exception {
    new BuscadorDeURL("http://www.siteinexistente.com/home").call();
  }

  @Test(expected = java.net.MalformedURLException.class)
  public void requisicaoComStringTexto() throws Exception {
    new BuscadorDeURL("não é uma URL").call();
  }

  @Test(expected = java.net.MalformedURLException.class)
  public void requisicaoComStringVazia() throws Exception {
    new BuscadorDeURL("").call();
  }

  @Test(expected = java.net.MalformedURLException.class)
  public void requisicaoComNull() throws Exception {
    new BuscadorDeURL(null).call();
  }
}