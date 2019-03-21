package br.edu.puccampinas.projetoc.labirinto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import br.edu.puccampinas.projetoc.pilha.Pilha;

/**
 * Soluciona um labirinto carregado através de um arquivo de texto
 * 
 * @author aleph
 *
 */
public class Labirinto {

  private final static char ENTRADA = 'E';
  private final static char SAIDA = 'S';
  private final static char PASSAGEM = ' ';
  private final static char PAREDE = '#';
  private static char PASSO = '*';
  private boolean avancar = true;

  private Pilha<Coordenada> caminho;
  private Pilha<Pilha<Coordenada>> possibilidades;
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
      this.atual = findOnBorders(ENTRADA);
    } catch (NoSuchElementException e) {
      throw new CarregamentoException("Não foi possível encontrar a entrada (E) do labirinto.");
    }
    try {
      findOnBorders(SAIDA);
    } catch (NoSuchElementException e) {
      throw new CarregamentoException("Não foi possível encontrar a saída (S) do labirinto.");
    }
    this.caminho = new Pilha<Coordenada>();
    this.possibilidades = new Pilha<Pilha<Coordenada>>();
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
        checkRowCells(values);
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
   * Verifica se as células de uma linha possuem caracteres válidos
   * 
   * @param values Células de uma linha do labirinto
   * @throws CarregamentoException Caso seja encontrado um caracter inválido
   */
  private void checkRowCells(char[] values) throws CarregamentoException {
    for (char c : values) {
      if (c != PAREDE && c != PASSAGEM && c != ENTRADA && c != SAIDA) {
        throw new CarregamentoException("O caracter \"" + c + "\" não é permitido.");
      }
    }
  }

  /**
   * Busca um elemento nas bordas do labirinto
   * 
   * @param elemento Um caracter que represente um elemento do labirinto
   * @return Devolve a @Coordenada com a posição do elemento encontrado
   * @throws CarregamentoException Caso haja espaços nas paredes
   * @throws NoSuchElementException Caso o elemento não seja encontrado
   */
  private Coordenada findOnBorders(char elemento) throws CarregamentoException {
    for (int i = 0; i < this.numLinhas; i++) {
      for (int j = 0; j < this.numColunas; j++) {
        if (i == 0 || j == 0 || i == this.numLinhas - 1 || j == this.numColunas - 1) {
          char atual = this.mapa[i][j];
          if (atual == PASSAGEM) {
            throw new CarregamentoException(
                "Retire a passagem da parede lateral " + new Coordenada(i, j) + ".");
          }
          if (atual == elemento) {
            return new Coordenada(i, j);
          }
        }
      }
    }
    throw new NoSuchElementException(
        "Não foi possível encontrar o elemento " + elemento + " no labirinto.");
  }

  /**
   * Busca a saída do labirinto
   * 
   * @throws BuscaException Caso não seja possível encontrar uma saída
   */
  public void findExit() throws BuscaException {
    char casaAtual;
    do {
      System.out.println(this.toString());
      Pilha<Coordenada> adjacentes = lookAround();
      if (this.avancar) {
        forward(adjacentes);
      } else {
        reverse();
      }
      casaAtual = mapa[this.atual.getLinha()][this.atual.getColuna()];
    } while (casaAtual != SAIDA);
  }

  /**
   * Olha ao redor da casa atual procurando casas que indiquem caminho livre ou saída
   * 
   * @return Devolve uma pilha com as cordenadas das casas adjacentes e válidas.
   */
  private Pilha<Coordenada> lookAround() {
    Pilha<Coordenada> adjacentes = new Pilha<Coordenada>();
    int xAtual = this.atual.getLinha();
    int yAtual = this.atual.getColuna();
    char acima = xAtual == 0 ? PAREDE : this.mapa[xAtual - 1][yAtual];
    if (acima == PASSAGEM || acima == SAIDA) {
      adjacentes.empilhar(new Coordenada(xAtual - 1, yAtual));
      PASSO = '↑';
    }
    char abaixo = xAtual == this.numLinhas - 1 ? PAREDE : this.mapa[xAtual + 1][yAtual];
    if (abaixo == PASSAGEM || abaixo == SAIDA) {
      adjacentes.empilhar(new Coordenada(xAtual + 1, yAtual));
      PASSO = '↓';
    }
    char esquerda = yAtual == 0 ? PAREDE : this.mapa[xAtual][yAtual - 1];
    if (esquerda == PASSAGEM || esquerda == SAIDA) {
      adjacentes.empilhar(new Coordenada(xAtual, yAtual - 1));
      PASSO = '←';
    }
    char direita = yAtual == this.numColunas - 1 ? PAREDE : this.mapa[xAtual][yAtual + 1];
    if (direita == PASSAGEM || direita == SAIDA) {
      adjacentes.empilhar(new Coordenada(xAtual, yAtual + 1));
      PASSO = '→';
    }
    return adjacentes;
  }

  /**
   * Modo progressivo do labirinto
   * 
   * Avança uma casa no labirinto
   * 
   * @param adjacentes Recebe as casas ao redor da casa atual
   */
  private void forward(Pilha<Coordenada> adjacentes) {
    try {
      this.atual = adjacentes.desempilhar();
    } catch (NoSuchElementException e) {
      this.avancar = false;
    }
    char casaAtual = this.mapa[this.atual.getLinha()][this.atual.getColuna()];
    if (casaAtual == SAIDA) {
      return;
    }
    this.mapa[this.atual.getLinha()][this.atual.getColuna()] = PASSO;
    this.caminho.empilhar(this.atual);
    this.possibilidades.empilhar(adjacentes);
  }

  /**
   * Modo regressivo do labirinto
   * 
   * Volta uma casa no labirinto
   * 
   * @throws BuscaException Caso não exista caminho que leve a saída do labirinto
   */
  private void reverse() throws BuscaException {
    try {
      this.atual = this.caminho.desempilhar();
    } catch (NoSuchElementException e) {
      throw new BuscaException("Não há caminho que leve à saída!");
    }
    this.mapa[this.atual.getLinha()][this.atual.getColuna()] = PASSAGEM;
    Pilha<Coordenada> adjacentes = this.possibilidades.exibeTopo();
    if (adjacentes.pilhaVazia()) {
      this.possibilidades.desempilhar();
    } else {
      this.atual = adjacentes.desempilhar();
      this.lookAround();
      this.mapa[this.atual.getLinha()][this.atual.getColuna()] = PASSO;
      this.avancar = true;
    }
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
