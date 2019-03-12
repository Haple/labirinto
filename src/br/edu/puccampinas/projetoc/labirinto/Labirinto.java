package br.edu.puccampinas.projetoc.labirinto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Labirinto {

  private Coordenada atual;

  public Labirinto(String caminho) {
    List<List<String>> records = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
      Integer linhas = Integer.parseInt(br.readLine());
      for (int i = 0; i < linhas; i++) {
        String[] values = br.readLine().split("");
        records.add(Arrays.asList(values));
      }
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    System.out.println("Records: " + records);
  }



}
