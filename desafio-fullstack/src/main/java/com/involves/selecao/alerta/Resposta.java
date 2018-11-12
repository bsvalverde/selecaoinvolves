package com.involves.selecao.alerta;

public class Resposta {

  public static final String SITUACAO = "Qual a situação do produto?";
  public static final String PRECO = "Qual o preço do produto?";
  public static final String SHARE = "%Share";

  public static final String PRODUTO_AUSENTE = "Produto ausente na gondola";
  
  private String pergunta;
  private String resposta;
  
  public String getPergunta() {
    return pergunta;
  }

  public void setPergunta(String pergunta) {
    this.pergunta = pergunta;
  }

  public String getResposta() {
    return resposta;
  }

  public void setResposta(String resposta) {
    this.resposta = resposta;
  }
}
