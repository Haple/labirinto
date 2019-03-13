package br.edu.puccampinas.projetoc.labirinto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Labirinto {

  private Coordenada atual;
  private char[][] mapa;
  private Integer numLinhas;
  private Integer numColunas;

  /**
   * Constrói um labirinto baseado no mapeamento de um arquivo de texto
   * 
   * @param caminho Caminho completo do arquivo que possui o mapa do labirinto
   * @throws CarregamentoException Caso haja alguma inconsistência no arquivo
   */
  public Labirinto(String caminho) throws CarregamentoException {
    loadMap(caminho);
    try {
      this.atual = findOnBorders('E');
    } catch (NoSuchElementException e) {
      throw new CarregamentoException("Não foi possível encontrar a entrada (E) do labirinto.");
    }
    try {
      findOnBorders('S');
    } catch (NoSuchElementException e) {
      throw new CarregamentoException("Não foi possível encontrar a saída (S) do labirinto.");
    }
  }

  /**
   * Carrega o mapa de um labirinto para uma matriz de listas
   * 
   * @param caminho Caminho completo do arquivo que possui o mapa do labirinto
   * @throws CarregamentoException Caso haja alguma inconsistência no arquivo
   */
  private void loadMap(String caminho) throws CarregamentoException {
    try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
      this.numLinhas = Integer.parseInt(br.readLine());
      char[] primeiraLinha = br.readLine().toCharArray();
      this.numColunas = primeiraLinha.length;
      this.mapa = new char[this.numLinhas][this.numColunas];
      this.mapa[0] = primeiraLinha;
      for (int i = 1; i < this.numLinhas; i++) {
        if (!br.ready()) {
          throw new CarregamentoException("O número de linhas informado (" + this.numLinhas
              + ") é maior do que a realidade (" + i + ").");
        }
        char[] values = br.readLine().toCharArray();
        if (i > 0 && values.length != this.mapa[0].length) {
          throw new CarregamentoException("A linha número " + (i + 1)
              + " não possui o número correto de colunas (" + this.mapa[0].length + ").");
        }
        this.mapa[i] = values;
      }
    } catch (IOException e) {
      throw new CarregamentoException(e.getMessage());
    } catch (NumberFormatException e) {
      throw new CarregamentoException(
          "Não é possível carregar um labirinto sem o número de linhas na primeira linha.");
    }
  }

  /**
   * Busca um elemento nas bordas do labirinto
   * 
   * @param elemento
   * @return Devolve a @Coordenada com a posição do elemento encontrado
   * @throws NoSuchElementException Caso o elemento não seja encontrado
   */
  private Coordenada findOnBorders(char elemento) {
    for (int i = 0; i < this.numLinhas; i++) {
      for (int j = 0; j < this.numColunas; j++) {
        if (i == 0 || j == 0 || i == this.numLinhas - 1 || j == this.numColunas - 1) {
          char atual = this.mapa[i][j];
          if (atual == elemento) {
            return new Coordenada(i, j);
          }
        }
      }
    }
    throw new NoSuchElementException(
        "Não foi possível encontrar o elemento " + elemento + " no labirinto.");
  }

  public Coordenada getAtual() {
    return atual;
  }

  @Override
  public String toString() {
    String mapa = "";
    for (char[] linha : this.mapa) {
      mapa += Arrays.toString(linha) + "\n";
    }
    return mapa;
  }

}
