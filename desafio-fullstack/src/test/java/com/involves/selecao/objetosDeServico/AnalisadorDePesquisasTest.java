package com.involves.selecao.objetosDeServico;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.TipoAlerta;
import com.involves.selecao.factory.PesquisaFactory;
import com.involves.selecao.objetosDeServico.AnalisadorDePesquisas;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalisadorDePesquisasTest {

  @Test
  public void naoCriaAlertaQuandoProdutoEstaNaGondola() throws Exception {
    Pesquisa pesquisa = PesquisaFactory.produtoPresente();
    ArrayList<Alerta> alertas = new AnalisadorDePesquisas(pesquisa).call();
    Assert.assertTrue(alertas.size() == 0);
  }

  @Test
  public void criaAlertaQuandoProdutoEstaAusente() throws Exception {
    Pesquisa pesquisa = PesquisaFactory.produtoAusente();
    ArrayList<Alerta> alertas = new AnalisadorDePesquisas(pesquisa).call();
    Alerta alerta = alertas.get(0);
    Assert.assertTrue(alerta.getFlTipo() == TipoAlerta.RUPTURA.getFlTipo());
  }

  @Test
  public void naoCriaAlertaQuandoPrecoEstaCorreto() throws Exception {
    Pesquisa pesquisa = PesquisaFactory.precoCorreto();
    ArrayList<Alerta> alertas = new AnalisadorDePesquisas(pesquisa).call();
    Assert.assertTrue(alertas.size() == 0);
  }

  @Test
  public void criaAlertaQuandoPrecoEstaAcima() throws Exception {
    Pesquisa pesquisa = PesquisaFactory.precoAlto();
    ArrayList<Alerta> alertas = new AnalisadorDePesquisas(pesquisa).call();
    Alerta alerta = alertas.get(0);
    Assert.assertTrue(alerta.getFlTipo() == TipoAlerta.PRECO_ACIMA.getFlTipo());
  }

  @Test
  public void criaAlertaQuandoPrecoEstaAbaixo() throws Exception {
    Pesquisa pesquisa = PesquisaFactory.precoBaixo();
    ArrayList<Alerta> alertas = new AnalisadorDePesquisas(pesquisa).call();
    Alerta alerta = alertas.get(0);
    Assert.assertTrue(alerta.getFlTipo() == TipoAlerta.PRECO_ABAIXO.getFlTipo());
  }

  @Test
  public void naoCriaAlertaQuandoShareEstaCorreto() throws Exception {
    Pesquisa pesquisa = PesquisaFactory.shareCorreto();
    ArrayList<Alerta> alertas = new AnalisadorDePesquisas(pesquisa).call();
    Assert.assertTrue(alertas.size() == 0);
  }

  @Test
  public void criaAlertaQuandoShareEstaAcima() throws Exception {
    Pesquisa pesquisa = PesquisaFactory.shareAlto();
    ArrayList<Alerta> alertas = new AnalisadorDePesquisas(pesquisa).call();
    Alerta alerta = alertas.get(0);
    Assert.assertTrue(alerta.getFlTipo() == TipoAlerta.PARTICIPACAO_SUPERIOR.getFlTipo());
  }

  @Test
  public void criaAlertaQuandoShareEstaAbaixo() throws Exception {
    Pesquisa pesquisa = PesquisaFactory.shareBaixo();
    ArrayList<Alerta> alertas = new AnalisadorDePesquisas(pesquisa).call();
    Alerta alerta = alertas.get(0);
    Assert.assertTrue(alerta.getFlTipo() == TipoAlerta.PARTICIPACAO_INFERIOR.getFlTipo());
  }

  @Test
  public void naoCriaAlertaQuandoPerguntaNaoFoiImplementada() throws Exception {
    Pesquisa pesquisa = PesquisaFactory.outraPergunta();
    ArrayList<Alerta> alertas = new AnalisadorDePesquisas(pesquisa).call();
    Assert.assertTrue(alertas.size() == 0);
  }

  @Test
  public void naoCriaAlertaQuandoNaoHaPerguntas() throws Exception {
    Pesquisa pesquisa = PesquisaFactory.nenhumaPergunta();
    ArrayList<Alerta> alertas = new AnalisadorDePesquisas(pesquisa).call();
    Assert.assertTrue(alertas.size() == 0);
  }
}