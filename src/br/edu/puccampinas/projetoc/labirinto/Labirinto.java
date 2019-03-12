package br.edu.puccampinas.projetoc.labirinto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Labirinto {

  private Coordenada atual;
  private String[][] mapa;
  private Integer numLinhas;

  /**
   * Cria um labirinto
   * 
   * @param caminho Caminho completo do arquivo que possui o mapa do labirinto
   * @throws CarregamentoException Caso haja alguma inconsistência no arquivo
   */
  public Labirinto(String caminho) throws CarregamentoException {
    this.atual = new Coordenada();
    load(caminho);
    extractEnterDoor();
    System.out.println(Arrays.deepToString(this.mapa));
  }

  /**
   * Carrega o mapa de um labirinto para uma matriz de listas
   * 
   * @param caminho Caminho completo do arquivo que possui o mapa do labirinto
   * @throws CarregamentoException Caso haja alguma inconsistência no arquivo
   */
  private void load(String caminho) throws CarregamentoException {
    try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
      this.numLinhas = Integer.parseInt(br.readLine());
      String[] primeiraLinha = br.readLine().split("");
      this.mapa = new String[numLinhas][primeiraLinha.length];
      this.mapa[0] = primeiraLinha;
      for (int i = 1; i < numLinhas; i++) {
        String[] values = br.readLine().split("");
        if (i > 0 && values.length != this.mapa[0].length) {
          throw new CarregamentoException("A linha número " + (i + 1)
              + " não possui o número correto de colunas (" + this.mapa[0].length + ").");
        }
        this.mapa[i] = values;
      }
    } catch (IOException e) {
      e.printStackTrace();
      throw new CarregamentoException(e.getMessage());
    } catch (NumberFormatException e) {
      throw new CarregamentoException(
          "Não é possível carregar um labirinto sem o número de linhas na primeira linha");
    }
  }

  private void extractEnterDoor() {
    // Primeira coluna
//    for (int i = 0; i < this.numLinhas; i++) {
//      System.out.println(this.mapa[i][0]);
//    }
    // Última coluna
    for (int i = 0; i < this.numLinhas; i++) {
      System.out.println(this.mapa[i][this.mapa.length + 1]);
    }
  }

}
