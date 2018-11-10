package com.involves.selecao.service;

import org.springframework.stereotype.Service;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.alerta.TipoAlerta;

@Service
public class CriadorAlertasService {

  private Pesquisa pesquisa;
  private Resposta resposta;
  private TipoAlerta tipo;

  private Alerta alerta;

  public Alerta criar(Pesquisa pesquisa, Resposta resposta, TipoAlerta tipo) {
    this.pesquisa = pesquisa;
    this.resposta = resposta;
    this.tipo = tipo;

    alerta = new Alerta();
    alerta.setPontoDeVenda(pesquisa.getPonto_de_venda());
    alerta.setDescricao(tipo.getDescricao());
    alerta.setProduto(pesquisa.getProduto());
    alerta.setFlTipo(tipo.getFlTipo());
    calcularMargem();
    return alerta;
  }

  private void calcularMargem() {
    switch(tipo){
      case PRECO_ABAIXO: case PRECO_ACIMA:
        alerta.setMargem(calcularMargemPreco());
      default:
        break;
    }
  }

  private int calcularMargemPreco() {
    int precoEstipulado = Integer.parseInt(pesquisa.getPreco_estipulado());
    int precoColetado = Integer.parseInt(resposta.getResposta());
    return precoEstipulado - precoColetado;
  }
}
