package com.involves.selecao.objetosDeServico;
import java.util.ArrayList;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.alerta.TipoAlerta;
import com.involves.selecao.objetosDeServico.CriadorDeAlertas;

public class AnalisadorDePesquisas {

  private Pesquisa pesquisa;
  private ArrayList<Alerta> alertas;

  public AnalisadorDePesquisas(Pesquisa pesquisa) {
    this.pesquisa = pesquisa;
    alertas = new ArrayList<Alerta>();
  }
  
  public ArrayList<Alerta> call() {
    for(Resposta resposta : pesquisa.getRespostas()) {
      analisaResposta(resposta);
    }
    return alertas;
  }

  private void analisaResposta(Resposta resposta) {
    switch(resposta.getPergunta()){
      case Resposta.SITUACAO:
        analisaSituacao(resposta);
        break;
      case Resposta.PRECO:
        analisaPreco(resposta);
        break;
      case Resposta.SHARE:
        analisaShare(resposta);
        break;
      default:
        System.out.println("Alerta ainda não implementado!");
    }
  }

  private void analisaSituacao(Resposta resposta) {
    if(resposta.getResposta().equals(Resposta.PRODUTO_AUSENTE)) {
      CriadorDeAlertas criador = new CriadorDeAlertas(pesquisa, resposta, TipoAlerta.RUPTURA);
      alertas.add(criador.call());
    }
  }

  private void analisaPreco(Resposta resposta) {
    int precoColetado = Integer.parseInt(resposta.getResposta());
    int precoEstipulado = Integer.parseInt(pesquisa.getPreco_estipulado());
    if (precoColetado != precoEstipulado) {
      TipoAlerta tipo = precoColetado > precoEstipulado ? TipoAlerta.PRECO_ACIMA : TipoAlerta.PRECO_ABAIXO;
      CriadorDeAlertas criador = new CriadorDeAlertas(pesquisa, resposta, tipo);
      alertas.add(criador.call());
    }
  }

  private void analisaShare(Resposta resposta) {
    int participacaoColetada = Integer.parseInt(resposta.getResposta());
    int participacaoEstipulada = Integer.parseInt(pesquisa.getParticipacao_estipulada());
    if (participacaoColetada != participacaoEstipulada) {
      TipoAlerta tipo = participacaoColetada > participacaoEstipulada ? TipoAlerta.PARTICIPACAO_SUPERIOR : TipoAlerta.PARTICIPACAO_INFERIOR;
      CriadorDeAlertas criador = new CriadorDeAlertas(pesquisa, resposta, tipo);
      alertas.add(criador.call());
    }
  }
}
