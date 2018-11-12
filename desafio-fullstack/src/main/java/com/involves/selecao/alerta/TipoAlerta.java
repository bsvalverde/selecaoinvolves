package com.involves.selecao.alerta;

public enum TipoAlerta {
  RUPTURA("Ruptura detectada!", 1),
  PRECO_ACIMA("Preço acima do estipulado!", 2),
  PRECO_ABAIXO("Preço abaixo do estipulado!", 3),
  PARTICIPACAO_SUPERIOR("Participação superior à estipulada!", 4),
  PARTICIPACAO_INFERIOR("Participação inferior à estipulada!", 5);

  private String descricao;
  private int flTipo;

  TipoAlerta(String descricao, int flTipo) {
    this.descricao = descricao;
    this.flTipo = flTipo;
  }

  public String getDescricao() {
    return descricao;
  }

  public int getFlTipo() {
    return flTipo;
  }
}
