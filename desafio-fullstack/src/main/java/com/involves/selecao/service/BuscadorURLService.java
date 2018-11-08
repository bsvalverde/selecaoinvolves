package com.involves.selecao.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class BuscadorURLService {
  
  public String buscar(String src) throws IOException {
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
