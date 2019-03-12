package br.edu.puccampinas.projetoc.pilha;

import java.util.Arrays;

/**
 * Representa a estrutura de uma pilha, onde novos elementos são adicionados ao topo da fila.
 * 
 * @author aleph
 *
 * @param <T> Tipo da pilha
 */
public class Pilha<T> {
  private T[] pilha; // vetor de objetos
  private int tamanho; // indicador de topo

  /**
   * Construtor de uma Pilha
   * 
   * @param qtde Quantidade de posições que a pilha deve ter
   */
  @SuppressWarnings("unchecked")
  public Pilha(int qtde) {
    // indica que está vazia, pois o topo é a primeira posição
    this.tamanho = 0;
    // criando uma pilha com a quantidade de posições informada
    this.pilha = (T[]) new Object[qtde];
  }

  /**
   * Adiciona um valor no topo da pilha
   * 
   * @param valor valor correspondente ao tipo da pilha
   * @return true se o valor foi empilhado com sucesso, false caso contrário
   */
  public T empilhar(T valor) {
    if (!this.pilhaCheia()) {
      this.pilha[this.tamanho] = valor;
      this.tamanho++;
      return valor;
    }
    throw new PilhaCheiaException("Não é possível empilhar novos itens em uma pilha cheia!");
  }

  /**
   * Desempilha o valor no topo da pilha
   * 
   * @return devolve o valor desempilhado
   * @throws PilhaVaziaException
   */
  public T desempilhar() throws PilhaVaziaException {
    if (this.pilhaVazia()) {
      throw new PilhaVaziaException("Não é possível desempilhar uma pilha vazia!");
    }
    this.tamanho--;
    return this.pilha[this.tamanho];
  }

  /**
   * Verifica se a pilha está vazia
   * 
   * @return true caso a pilha esteja vazia, false caso contrário
   */
  public boolean pilhaVazia() {
    if (this.tamanho == 0) {
      return true;
    }
    return false;
  }

  /**
   * Verifica se a pilha está cheia
   * 
   * @return true caso a pilha esteja cheia, false caso contrário
   */
  public boolean pilhaCheia() {
    if (this.tamanho > this.pilha.length - 1) {
      return true;
    }
    return false;
  }

  /**
   * Exibe o elemento no topo da pilha
   * 
   * @return devolve o valor no topo da pilha, sem deletá-lo
   * @throws PilhaVaziaException
   */
  public T exibeTopo() throws PilhaVaziaException {
    if (this.pilhaVazia()) {
      throw new PilhaVaziaException("Não é possível exibir o topo de uma pilha vazia!");
    }
    return this.pilha[this.tamanho - 1];
  }

  /**
   * Verifica quantos elementos estão na pilha
   * 
   * @return devolve a quantidade de elementos
   */
  public int getTamanho() {
    return this.tamanho;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.deepHashCode(pilha);
    result = prime * result + tamanho;
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
    if (!Arrays.deepEquals(pilha, other.pilha))
      return false;
    if (tamanho != other.tamanho)
      return false;
    return true;
  }



}
