package br.edu.puccampinas.projetoc;

import java.util.ArrayList;

import br.edu.puccampinas.projetoc.pilha.Pilha;

public class ProjetoC {

  /**
   * Driver
   * 
   * @param args
   */
  public static void main(String[] args) {
    Pilha<String> p = new Pilha<String>(3);
    p.empilhar("A");
    p.empilhar("B");
    p.empilhar("C");
    while (!p.pilhaVazia()) {
      System.out.println(p.desempilhar());
    }
     ArrayList<String> l = new ArrayList<>(2);
     l.add("A");
     l.add("B");
     l.add("C");
     l.add("D");
     System.out.println(l);
    // try {
    // Labirinto l = new Labirinto("/home/aleph/Documentos/labirinto1.txt");
    // } catch (CarregamentoException e) {
    // System.err.println("Não foi possível carregar o labirinto: " + e.getMessage());
    // }

  }

}
