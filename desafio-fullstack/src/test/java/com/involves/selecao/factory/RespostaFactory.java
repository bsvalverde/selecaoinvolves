package com.involves.selecao.factory;

import com.involves.selecao.alerta.Resposta;

public class RespostaFactory {

  public static Resposta produtoPresente() {
    Resposta resposta = new Resposta();
    resposta.setPergunta(Resposta.SITUACAO);
    resposta.setResposta("Produto está na gondola");
    return resposta;
  }

  public static Resposta produtoAusente() {
    Resposta resposta = new Resposta();
    resposta.setPergunta(Resposta.SITUACAO);
    resposta.setResposta(Resposta.PRODUTO_AUSENTE);
    return resposta;
  }

  public static Resposta preco() {
    Resposta resposta = new Resposta();
    resposta.setPergunta(Resposta.PRECO);
    resposta.setResposta("");
    return resposta;
  }

  public static Resposta share() {
    Resposta resposta = new Resposta();
    resposta.setPergunta(Resposta.SHARE);
    resposta.setResposta("");
    return resposta;
  }

  public static Resposta outraPergunta() {
    Resposta resposta = new Resposta();
    resposta.setPergunta("Outra pergunta");
    resposta.setResposta("Qualquer resposta");
    return resposta;
  }
}
