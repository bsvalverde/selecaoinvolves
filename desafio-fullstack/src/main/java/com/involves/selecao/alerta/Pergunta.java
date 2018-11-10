package com.involves.selecao.alerta;

public enum Pergunta {
  SITUACAO("Qual a situação do produto?"),
  PRECO("Qual o preço do produto?"),
  SHARE("%Share");

  private String texto;

  Pergunta(String texto) {
    this.texto = texto;
  }

  public String getTexto() {
    return texto;
  }
}
