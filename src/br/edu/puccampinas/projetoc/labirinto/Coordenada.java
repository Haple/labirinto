package br.edu.puccampinas.projetoc.labirinto;

/**
 * É uma posição de um labirinto, representada por uma linha e uma coluna (números do conjunto dos
 * números naturais).
 * 
 * @author aleph
 *
 */
public class Coordenada {

  private Integer linha;
  private Integer coluna;

  /**
   * Constrói uma coordenada na origem (0,0) do plano cartesiano
   */
  public Coordenada() {
    this.linha = 0;
    this.coluna = 0;
  }

  /**
   * Constrói uma coordenada com a mesma posição de uma coordenada específica
   * 
   * @param c Uma coordenada
   */
  public Coordenada(Coordenada c) {
    this.linha = c.getLinha();
    this.coluna = c.getColuna();
  }

  /**
   * Verifica se uma coordenada é válida ou não.
   * 
   * @param x Representa o plano horizontal da coordenada
   * @param y Representa o plano vertical da coordenada
   * @throws IllegalArgumentException Caso X ou Y sejam números negativos
   */
  private void valida(Integer x, Integer y) throws IllegalArgumentException {
    if (x < 0) {
      throw new IllegalArgumentException("Coordenada X inválida: " + x);
    }
    if (y < 0) {
      throw new IllegalArgumentException("Coordenada Y inválida: " + y);
    }
  }

  /**
   * Constrói uma coordenada em uma posição específica do plano
   * 
   * @param x Representa o plano horizontal da coordenada
   * @param y Representa o plano vertical da coordenada
   * @throws IllegalArgumentException Caso a coordenada tenha números negativos
   */
  public Coordenada(Integer x, Integer y) throws IllegalArgumentException {
    valida(x, y);
    this.linha = x;
    this.coluna = y;
  }


  /**
   * Troca a posição de uma coordenada
   * 
   * @param linha Representa o plano horizontal da coordenada
   * @param coluna Representa o plano vertical da coordenada
   * @throws IllegalArgumentException Caso a coordenada tenha números negativos
   */
  public void setPosicao(Integer linha, Integer coluna) throws IllegalArgumentException {
    valida(linha, coluna);
    this.linha = linha;
    this.coluna = coluna;
  }

  public Integer getLinha() {
    return linha;
  }

  public Integer getColuna() {
    return coluna;
  }

  @Override
  public String toString() {
    return "Coordenada [linha=" + linha + ", coluna=" + coluna + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((linha == null) ? 0 : linha.hashCode());
    result = prime * result + ((coluna == null) ? 0 : coluna.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Coordenada other = (Coordenada) obj;
    if (linha == null) {
      if (other.linha != null)
        return false;
    } else if (!linha.equals(other.linha))
      return false;
    if (coluna == null) {
      if (other.coluna != null)
        return false;
    } else if (!coluna.equals(other.coluna))
      return false;
    return true;
  }

}
