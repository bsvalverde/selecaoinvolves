package com.involves.selecao.objetosDeServico;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BuscadorDeURL {

  private String src;

  public BuscadorDeURL(String src) {
    this.src = src;
  }
  
  public String call() throws IOException {
    URL url = new URL(src);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
    String inputLine;
    StringBuffer content = new StringBuffer();
    
    while ((inputLine = in.readLine()) != null) {
      content.append(inputLine);
    }
    in.close();

    return content.toString();
  }
}
