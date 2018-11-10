package com.involves.selecao.service;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.alerta.Resposta;
import com.involves.selecao.alerta.TipoAlerta;
import com.involves.selecao.gateway.AlertaGateway;
import com.involves.selecao.objetosDeServico.AnalisadorDePesquisas;
import com.involves.selecao.service.ColetorPesquisasService;

@Service
public class ProcessadorAlertas {

  @Autowired
  private AlertaGateway gateway;

  @Autowired
  private ColetorPesquisasService coletor;
  
  public void processa() throws IOException {
    Pesquisa[] pesquisas = coletor.coletar();

    for(Pesquisa pesquisa : pesquisas) {
      List<Alerta> alertas = new AnalisadorDePesquisas(pesquisa).call();
      for(Alerta alerta : alertas) {
        gateway.salvar(alerta);
      }
    }
  }
}
