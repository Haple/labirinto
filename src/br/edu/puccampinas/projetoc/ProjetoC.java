package br.edu.puccampinas.projetoc;

import br.edu.puccampinas.projetoc.labirinto.CarregamentoException;
import br.edu.puccampinas.projetoc.labirinto.Labirinto;

public class ProjetoC {

  /**
   * Driver
   * 
   * @param args
   */
  public static void main(String[] args) {
//    Pilha<String> p = new Pilha<String>(3);
//    p.empilhar("A");
//    p.empilhar("B");
//    p.empilhar("C");
//    while (!p.pilhaVazia()) {
//      System.out.println(p.desempilhar());
//    }
//    System.out.println("Topo: " + p.exibeTopo());
    
//     ArrayList<String> l = new ArrayList<>(2);
//     l.add("A");
//     l.add("B");
//     l.add("C");
//     l.add("D");
//     System.out.println(l);
    try {
      Labirinto l = new Labirinto("/home/aleph/Documentos/labirinto1.txt");
      System.out.println(l);
    } catch (CarregamentoException e) {
      System.err.println("Não foi possível carregar o labirinto: " + e.getMessage());
    }

  }

}
