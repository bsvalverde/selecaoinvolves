package com.involves.selecao.factory;
import java.util.ArrayList;

import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.factory.RespostaFactory;

public class PesquisaFactory {

  private static Pesquisa basePesquisa() {
    Pesquisa pesquisa = new Pesquisa();
    pesquisa.setRotulo("Campanha de Natal");
    pesquisa.setNotificante("Papai Noel");
    pesquisa.setPonto_de_venda("Polo Norte");
    pesquisa.setRespostas(new ArrayList<Resposta>());
    return pesquisa;
  }

  public static Pesquisa produtoPresente() {
    Pesquisa pesquisa = basePesquisa();
    pesquisa.setProduto("Trenó");
    pesquisa.getRespostas().add(RespostaFactory.produtoPresente());
    return pesquisa;
  }

  public static Pesquisa produtoAusente() {
    Pesquisa pesquisa = basePesquisa();
    pesquisa.setProduto("Trenó");
    pesquisa.getRespostas().add(RespostaFactory.produtoAusente());
    return pesquisa;
  }

  public static Pesquisa precoCorreto() {
    Pesquisa pesquisa = basePesquisa();
    pesquisa.setProduto("Trenó");
    pesquisa.setPreco_estipulado("50");
    Resposta resposta = RespostaFactory.preco();
    resposta.setResposta("50");
    pesquisa.getRespostas().add(resposta);
    return pesquisa;
  }

  public static Pesquisa precoAlto() {
    Pesquisa pesquisa = basePesquisa();
    pesquisa.setProduto("Trenó");
    pesquisa.setPreco_estipulado("50");
    Resposta resposta = RespostaFactory.preco();
    resposta.setResposta("60");
    pesquisa.getRespostas().add(resposta);
    return pesquisa;
  }

  public static Pesquisa precoBaixo() {
    Pesquisa pesquisa = basePesquisa();
    pesquisa.setProduto("Trenó");
    pesquisa.setPreco_estipulado("50");
    Resposta resposta = RespostaFactory.preco();
    resposta.setResposta("45");
    pesquisa.getRespostas().add(resposta);
    return pesquisa;
  }

  public static Pesquisa shareCorreto() {
    Pesquisa pesquisa = basePesquisa();
    pesquisa.setCategoria("Automóveis");
    pesquisa.setParticipacao_estipulada("50");
    Resposta resposta = RespostaFactory.share();
    resposta.setResposta("50");
    pesquisa.getRespostas().add(resposta);
    return pesquisa;
  }

  public static Pesquisa shareAlto() {
    Pesquisa pesquisa = basePesquisa();
    pesquisa.setCategoria("Automóveis");
    pesquisa.setParticipacao_estipulada("50");
    Resposta resposta = RespostaFactory.share();
    resposta.setResposta("60");
    pesquisa.getRespostas().add(resposta);
    return pesquisa;
  }

  public static Pesquisa shareBaixo() {
    Pesquisa pesquisa = basePesquisa();
    pesquisa.setCategoria("Automóveis");
    pesquisa.setParticipacao_estipulada("50");
    Resposta resposta = RespostaFactory.share();
    resposta.setResposta("40");
    pesquisa.getRespostas().add(resposta);
    return pesquisa;
  }

  public static Pesquisa outraPergunta() {
    Pesquisa pesquisa = basePesquisa();
    Resposta resposta = RespostaFactory.outraPergunta();
    pesquisa.getRespostas().add(resposta);
    return pesquisa;
  }

  public static Pesquisa nenhumaPergunta() {
    Pesquisa pesquisa = basePesquisa();
    return pesquisa;
  }
}
