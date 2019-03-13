package br.edu.puccampinas.projetoc.labirinto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Labirinto {

  private Coordenada atual;
  private String[][] mapa;
  private Integer numLinhas;
  private Integer numColunas;

  /**
   * Cria um labirinto
   * 
   * @param caminho Caminho completo do arquivo que possui o mapa do labirinto
   * @throws CarregamentoException Caso haja alguma inconsistência no arquivo
   */
  public Labirinto(String caminho) throws CarregamentoException {
    this.atual = new Coordenada();
    load(caminho);
    loadEntrance();
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
      this.numColunas = primeiraLinha.length;
      this.mapa = new String[this.numLinhas][this.numColunas];
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

  /**
   * Carrega a entrada do labirinto
   */
  private void loadEntrance() {
    for (int i = 0; i < this.numLinhas; i++) {
      for (int j = 0; j < this.numColunas; j++) {
        if (i == 0 || j == 0 || i == this.numLinhas - 1 || j == this.numColunas - 1) {
          String atual = this.mapa[i][j];
          if (atual == "E") {
            this.atual = new Coordenada(i, j);
          }
        }
      }
    }
  }

  @Override
  public String toString() {
    String mapa = "";
    for (String[] linha : this.mapa) {
      mapa += Arrays.toString(linha) + "\n";
    }
    return mapa;
  }



}
