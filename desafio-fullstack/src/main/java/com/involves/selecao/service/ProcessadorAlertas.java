package com.involves.selecao.service;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.alerta.TipoAlerta;
import com.involves.selecao.gateway.AlertaGateway;
import com.involves.selecao.service.ColetorPesquisasService;
import com.involves.selecao.service.CriadorAlertasService;

@Service
public class ProcessadorAlertas {

  @Autowired
  private AlertaGateway gateway;

  @Autowired
  private ColetorPesquisasService coletor;

  @Autowired
  private CriadorAlertasService criador;
  
  public void processa() throws IOException {
    Pesquisa[] ps = coletor.coletar();
    for (int i = 0; i < ps.length; i++){
      for (int j = 0; j < ps[i].getRespostas().size(); j++){
        Resposta resposta = ps[i].getRespostas().get(j);
        if (resposta.getPergunta().equals("Qual a situação do produto?")) {
          if(resposta.getResposta().equals("Produto ausente na gondola")){
            Alerta alerta = criador.criar(ps[i], ps[i].getRespostas().get(j), TipoAlerta.RUPTURA);
            gateway.salvar(alerta);
          }
        } else if(resposta.getPergunta().equals("Qual o preço do produto?")) {
          int precoColetado = Integer.parseInt(resposta.getResposta());
          int precoEstipulado = Integer.parseInt(ps[i].getPreco_estipulado());
          if(precoColetado > precoEstipulado){
            Alerta alerta = criador.criar(ps[i], ps[i].getRespostas().get(j), TipoAlerta.PRECO_ACIMA);
            gateway.salvar(alerta);
          } else if(precoColetado < precoEstipulado){
            Alerta alerta = criador.criar(ps[i], ps[i].getRespostas().get(j), TipoAlerta.PRECO_ABAIXO);
            gateway.salvar(alerta);
          }
        } else {
          System.out.println("Alerta ainda não implementado!");
        }
      } 
    }
  }
}
