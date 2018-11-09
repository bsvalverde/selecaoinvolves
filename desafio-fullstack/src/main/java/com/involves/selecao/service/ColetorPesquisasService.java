package com.involves.selecao.service;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.service.BuscadorURLService;

@Service
public class ColetorPesquisasService {

  @Autowired
  private BuscadorURLService buscador;
  
  public Pesquisa[] coletar() throws IOException {
    String conteudo = buscador.buscar("https://selecao-involves.agilepromoter.com/pesquisas");
    
    Gson gson = new Gson();
    Pesquisa[] pesquisas =  gson.fromJson(conteudo, Pesquisa[].class);
    return pesquisas;
  }
}

