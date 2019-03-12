package br.edu.puccampinas.projetoc.labirinto;

/**
 * Essa coordenada representa pares ordenados de números contidos no conjunto dos números naturais.
 * Números negativos ou fracionários não serão aceitos.
 * 
 * O plano representado por coordenadas desse tipo é equivalente ao primeiro quadrante de um plano
 * cartesiano.
 * 
 * @author aleph
 *
 */
public class Coordenada {

  private Integer x;
  private Integer y;

  /**
   * Gera e inicializa uma coordenada na origem do plano cartesiano (0,0)
   */
  public Coordenada() {
    this.x = 0;
    this.y = 0;
  }

  /**
   * Gera e inicializa uma coordenada com a mesma posição de uma coordenada específica
   * 
   * @param c Uma coordenada
   */
  public Coordenada(Coordenada c) {
    this.x = c.getX();
    this.y = c.getY();
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
   * Gera e inicializa uma coordenada em uma posição específica do plano
   * 
   * @param x Representa o plano horizontal da coordenada
   * @param y Representa o plano vertical da coordenada
   * @throws IllegalArgumentException Caso a coordenada tenha números negativos
   */
  public Coordenada(Integer x, Integer y) throws IllegalArgumentException {
    valida(x, y);
    this.x = x;
    this.y = y;
  }


  /**
   * Troca a posição de uma coordenada
   * 
   * @param x Representa o plano horizontal da coordenada
   * @param y Representa o plano vertical da coordenada
   * @throws IllegalArgumentException Caso a coordenada tenha números negativos
   */
  public void setPosicao(Integer x, Integer y) throws IllegalArgumentException {
    valida(x, y);
    this.x = x;
    this.y = y;
  }

  public Integer getX() {
    return x;
  }

  public Integer getY() {
    return y;
  }

  @Override
  public String toString() {
    return "Coordenada [x=" + x + ", y=" + y + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((x == null) ? 0 : x.hashCode());
    result = prime * result + ((y == null) ? 0 : y.hashCode());
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
    if (x == null) {
      if (other.x != null)
        return false;
    } else if (!x.equals(other.x))
      return false;
    if (y == null) {
      if (other.y != null)
        return false;
    } else if (!y.equals(other.y))
      return false;
    return true;
  }

}
