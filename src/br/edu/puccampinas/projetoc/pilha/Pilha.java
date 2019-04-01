package br.edu.puccampinas.projetoc.pilha;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Representa a estrutura de uma pilha, onde novos elementos são adicionados ao topo da fila.
 * 
 * @author aleph
 *
 * @param <T> Tipo da pilha
 */
public class Pilha<T> {


  private No primeiro;
  private int total;

  /**
   * Constrói uma pilha
   */
  public Pilha() {
    super();
    this.primeiro = null;
    this.total = 0;
  }

  /**
   * Constrói uma pilha baseado
   * 
   * @param outraPilha A pilha que deve ser copiada
   */
  public Pilha(Pilha<T> outraPilha) {
    super();
    this.primeiro = outraPilha.primeiro;
    this.total = outraPilha.total;
  }

  /**
   * Adiciona um valor no topo da pilha
   * 
   * @param valor Correspondente ao tipo da pilha
   * @return Devolve o valor empilhado
   * @throws NullPointerException Caso o valor empilhado seja nulo
   */
  public Pilha<T> empilhar(T valor) {
    if (valor == null) {
      throw new NullPointerException("Não é possível empilhar um item nulo!");
    }
    this.primeiro = new No(valor, this.primeiro);
    this.total++;
    return this;
  }

  /**
   * Desempilha o valor no topo da pilha
   * 
   * @return devolve o valor desempilhado
   * @throws NoSuchElementException Caso a pilha esteja vazia
   */
  public T desempilhar() throws NoSuchElementException {
    if (this.total == 0) {
      throw new NoSuchElementException("Não é possível desempilhar uma pilha vazia!");
    }
    T atual = this.primeiro.getItem();
    this.primeiro = this.primeiro.getProximo();
    this.total--;
    return atual;
  }

  /**
   * Verifica se a pilha está vazia
   * 
   * @return true caso a pilha esteja vazia, false caso contrário
   */
  public boolean pilhaVazia() {
    return (primeiro == null);
  }

  /**
   * Exibe o elemento no topo da pilha
   * 
   * @return devolve o valor no topo da pilha, sem deletá-lo
   * @throws NoSuchElementException Caso a pilha esteja vazia
   */
  public T exibeTopo() throws NoSuchElementException {
    if (this.total == 0) {
      throw new NoSuchElementException("Não é possível exibir o topo de uma pilha vazia!");
    }
    return this.primeiro.getItem();
  }

  public int getTotal() {
    return total;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((primeiro == null) ? 0 : primeiro.hashCode());
    result = prime * result + total;
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
    Pilha<T> other = (Pilha<T>) obj;
    if (primeiro == null) {
      if (other.primeiro != null)
        return false;
    } else if (!primeiro.equals(other.primeiro))
      return false;
    if (total != other.total)
      return false;
    return true;
  }

  /**
   * Extrai para um vetor os elementos da pilha
   * 
   * @return Devolve um vetor com os elementos
   */
  private T[] getElements() {
    T[] elements = (T[]) new Object[this.total];
    No aux = this.primeiro;
    for (int i = elements.length - 1; i >= 0; i--) {
      elements[i] = aux.getItem();
      aux = aux.proximo;
    }
    return elements;
  }

  @Override
  public String toString() {
    return Arrays.toString(getElements());
  }

  /**
   * Representa um nó de uma pilha
   * 
   * @author aleph
   *
   */
  private class No {
    T item;
    No proximo;

    public No(T item, No proximo) {
      super();
      this.item = item;
      this.proximo = proximo;
    }

    public T getItem() {
      return item;
    }

    public No getProximo() {
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
      No other = (No) obj;
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

}
