package com.involves.selecao.service;

//import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuscadorURLServiceTest {

  @Autowired
  private BuscadorURLService buscador;

  @Test
  public void retornaOConteudoDoLinkFornecido() throws Exception {
    //String content = buscador.busca("https://www.google.com");
    //Assert.equalsTrue(true);
  }
}