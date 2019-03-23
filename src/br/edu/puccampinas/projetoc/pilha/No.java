package br.edu.puccampinas.projetoc.pilha;

/**
 * Representa um nó de uma pilha
 * 
 * @author aleph
 *
 */
class No<T> {
  T item;
  No<T> proximo;

  public No(T item, No<T> proximo) {
    super();
    this.item = item;
    this.proximo = proximo;
  }

  public No(No<T> no) {
    super();
    this.item = no.item;
    this.proximo = no.proximo;
  }

  public T getItem() {
    return item;
  }

  public No<T> getProximo() {
    return proximo;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((item == null) ? 0 : item.hashCode());
    result = prime * result + ((proximo == null) ? 0 : proximo.hashCode());
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
    @SuppressWarnings("unchecked")
    No<T> other = (No<T>) obj;
    if (item == null) {
      if (other.item != null)
        return false;
    } else if (!item.equals(other.item))
      return false;
    if (proximo == null) {
      if (other.proximo != null)
        return false;
    } else if (!proximo.equals(other.proximo))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "No [item=" + item + ", proximo=" + proximo + "]";
  }
}
