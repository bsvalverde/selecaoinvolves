package com.involves.selecao.objetosDeServico;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.alerta.TipoAlerta;
import com.involves.selecao.factory.PesquisaFactory;
import com.involves.selecao.objetosDeServico.CriadorDeAlertas;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriadorDeAlertasTest {

  @Test
  public void criaAlertaDeRuptura() throws Exception {
    Pesquisa pesquisa = PesquisaFactory.produtoAusente();
    Alerta alerta = new CriadorDeAlertas(pesquisa, pesquisa.getRespostas().get(0), TipoAlerta.RUPTURA).call();
    Assert.assertEquals(alerta.getPontoDeVenda(), pesquisa.getPonto_de_venda());
    Assert.assertEquals(alerta.getDescricao(), TipoAlerta.RUPTURA.getDescricao());
    Assert.assertEquals(alerta.getProduto(), pesquisa.getProduto());
    Assert.assertEquals(alerta.getCategoria(), pesquisa.getCategoria());
    Assert.assertTrue(alerta.getFlTipo() == TipoAlerta.RUPTURA.getFlTipo());
    Assert.assertNull(alerta.getMargem());
  }

  @Test
  public void criaAlertaDeRupturaComDadosFaltantes() throws Exception {
    Pesquisa pesquisa = PesquisaFactory.produtoAusente();
    pesquisa.setPonto_de_venda(null);
    pesquisa.setProduto(null);
    pesquisa.setCategoria(null);
    Alerta alerta = new CriadorDeAlertas(pesquisa, pesquisa.getRespostas().get(0), TipoAlerta.RUPTURA).call();
    Assert.assertEquals(alerta.getPontoDeVenda(), pesquisa.getPonto_de_venda());
    Assert.assertEquals(alerta.getDescricao(), TipoAlerta.RUPTURA.getDescricao());
    Assert.assertEquals(alerta.getProduto(), pesquisa.getProduto());
    Assert.assertEquals(alerta.getCategoria(), pesquisa.getCategoria());
    Assert.assertTrue(alerta.getFlTipo() == TipoAlerta.RUPTURA.getFlTipo());
    Assert.assertNull(alerta.getMargem());
  }
}