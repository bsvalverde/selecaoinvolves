package com.involves.selecao.service;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.alerta.Pesquisa;
import com.involves.selecao.objetosDeServico.BuscadorDeURL;

@Service
public class ColetorPesquisasService {

  public Pesquisa[] coletar() throws IOException {
    String conteudo = new BuscadorDeURL("https://selecao-involves.agilepromoter.com/pesquisas").call();
    
    Gson gson = new Gson();
    Pesquisa[] pesquisas =  gson.fromJson(conteudo, Pesquisa[].class);
    return pesquisas;
  }
}

