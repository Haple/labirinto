package br.edu.puccampinas.projetoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * Driver de testes do Labirinto
 * 
 * @author aleph
 *
 */
public class ProjetoC {

  private static final Logger LOGGER = Logger.getLogger(ProjetoC.class.getName());

  /**
   * Cria um driver de testes do labirinto
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("Digite o caminho completo do mapa do labirinto: ");
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String mapaLabirinto = "";
    try {
      mapaLabirinto = br.readLine();
    } catch (IOException e) {
      LOGGER.severe("Erro ao processar caminho do mapa: " + e.getMessage());
    }
    DriverLabirinto driverLabirinto = new DriverLabirinto(mapaLabirinto);
    driverLabirinto.start();
  }

}
