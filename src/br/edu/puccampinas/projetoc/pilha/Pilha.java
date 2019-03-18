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
  private T[] vetor;
  private int total;
  private final int capacidadePadrao = 10;

  /**
   * Constrói uma pilha com a capacidade inicial especificada
   * 
   * @param capacidade Quantidade de posições que a pilha deve ter
   */
  @SuppressWarnings("unchecked")
  public Pilha(int capacidade) {
    this.vetor = (T[]) new Object[capacidade];
    this.total = 0;
  }

  /**
   * Constrói uma pilha com a capacidade inicial especificada
   * 
   * @param capacidade Quantidade de posições que a pilha deve ter
   */
  @SuppressWarnings("unchecked")
  public Pilha() {
    this.vetor = (T[]) new Object[this.capacidadePadrao];
    this.total = 0;
  }

  /**
   * Adiciona um valor no topo da pilha
   * 
   * @param valor valor correspondente ao tipo da pilha
   * @return true se o valor foi empilhado com sucesso, false caso contrário
   */
  public T empilhar(T valor) {
    if (this.total == this.vetor.length) {
      this.setCapacidade(this.vetor.length * 2);
    }
    this.vetor[this.total++] = valor;
    return valor;
  }

  /**
   * 
   * @param capacidade throws IllegalArgumentException
   */
  @SuppressWarnings("unchecked")
  private void setCapacidade(Integer capacidade) throws IllegalArgumentException {
    if (capacidade < 1) {
      throw new IllegalArgumentException(
          "Não é possível criar uma pilha com capacidade menor que 1.");
    }
    T[] vetorTemp = (T[]) new Object[capacidade];
    System.arraycopy(this.vetor, 0, vetorTemp, 0, this.total);
    this.vetor = vetorTemp;
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
    return this.vetor[--this.total];
  }

  /**
   * Verifica se a pilha está vazia
   * 
   * @return true caso a pilha esteja vazia, false caso contrário
   */
  public boolean pilhaVazia() {
    return (this.total == 0);
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
    return this.vetor[this.total - 1];
  }

  public int getTotal() {
    return total;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.deepHashCode(vetor);
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
    Pilha<?> other = (Pilha<?>) obj;
    if (!Arrays.deepEquals(vetor, other.vetor))
      return false;
    if (total != other.total)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Pilha [total=" + total + ", topo=" + this.exibeTopo() + "]";
  }



}
