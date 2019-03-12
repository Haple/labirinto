package br.edu.puccampinas.projetoc;

import br.edu.puccampinas.projetoc.labirinto.Coordenada;
import br.edu.puccampinas.projetoc.labirinto.Labirinto;

public class ProjetoC {

  public static void main(String[] args) {
    Coordenada c = new Coordenada(2, 0);
    System.out.println(c);

    Labirinto l = new Labirinto("/home/aleph/Documentos/labirinto1.txt");

  }

}
