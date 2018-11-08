package com.involves.selecao.service;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.gateway.AlertaGateway;
import com.involves.selecao.service.BuscadorURLService;

@Service
public class ProcessadorAlertas {

  @Autowired
  private AlertaGateway gateway;

  @Autowired
  private BuscadorURLService buscador;
  
  public void processa() throws IOException {
    String content = buscador.buscar("https://selecao-involves.agilepromoter.com/pesquisas");
    
    Gson gson = new Gson();
    Pesquisa[] ps =  gson.fromJson(content, Pesquisa[].class);
    for (int i = 0; i < ps.length; i++){
      for (int j = 0; j < ps[i].getRespostas().size(); j++){
        Resposta resposta = ps[i].getRespostas().get(j);
        if (resposta.getPergunta().equals("Qual a situação do produto?")) {
          if(resposta.getResposta().equals("Produto ausente na gondola")){
              Alerta alerta = new Alerta();
              alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
              alerta.setDescricao("Ruptura detectada!");
              alerta.setProduto(ps[i].getProduto());
              alerta.setFlTipo(1);
              gateway.salvar(alerta);
          }
        } else if(resposta.getPergunta().equals("Qual o preço do produto?")) {
          int precoColetado = Integer.parseInt(resposta.getResposta());
          int precoEstipulado = Integer.parseInt(ps[i].getPreco_estipulado());
          if(precoColetado > precoEstipulado){
              Alerta alerta = new Alerta();
              int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
              alerta.setMargem(margem);
              alerta.setDescricao("Preço acima do estipulado!");
              alerta.setProduto(ps[i].getProduto());
              alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
              alerta.setFlTipo(2);
              gateway.salvar(alerta);
          } else if(precoColetado < precoEstipulado){
            Alerta alerta = new Alerta();
              int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
              alerta.setMargem(margem);
              alerta.setDescricao("Preço abaixo do estipulado!");
              alerta.setProduto(ps[i].getProduto());
              alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
              alerta.setFlTipo(3);
              gateway.salvar(alerta);
          }
        } else {
          System.out.println("Alerta ainda não implementado!");
        }
      } 
    }
  }
}

