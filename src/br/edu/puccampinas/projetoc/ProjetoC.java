package br.edu.puccampinas.projetoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import br.edu.puccampinas.projetoc.labirinto.BuscaException;
import br.edu.puccampinas.projetoc.labirinto.CarregamentoException;
import br.edu.puccampinas.projetoc.labirinto.Labirinto;

/**
 * Driver de testes do Labirinto
 * 
 * @author aleph
 *
 */
public class ProjetoC {

  private static final Logger LOGGER = Logger.getLogger(ProjetoC.class.getName());

  /**
   * Driver
   * 
   * @param args
   */
  public static void main(String[] args) {
    // Entrada da aplicação
    String mapaLabirinto = "";
    String pastaSolucao = "";
    String nomeArquivo = "";
    String arquivoSolucao = "";
    try {
      System.out.println("Digite o caminho completo do mapa do labirinto: ");
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      mapaLabirinto = br.readLine();
      pastaSolucao = mapaLabirinto.substring(0, mapaLabirinto.lastIndexOf(File.separator));
      nomeArquivo = mapaLabirinto.substring(mapaLabirinto.lastIndexOf(File.separator) + 1,
          mapaLabirinto.lastIndexOf("."));
      arquivoSolucao = pastaSolucao + "/" + nomeArquivo + "(solucionado).txt";
    } catch (Exception e) {
      LOGGER.severe("Não foi possível ler o caminho do arquivo: " + e.getMessage());
    }
    // Processamento do labirinto
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoSolucao))) {
      Labirinto l = new Labirinto(mapaLabirinto);
      l.findExit();
      System.out.println("Labirinto: \n" + l);
      System.out.println("Coordenada da saída: " + l.getAtual());
      bw.write(l.toString());
    } catch (CarregamentoException e) {
      LOGGER.severe("Falha ao carregar o labirinto: " + e.getMessage());
    } catch (IOException e) {
      LOGGER.severe("Erro ao criar arquivo de saída: " + e.getMessage());
    } catch (BuscaException e) {
      LOGGER.severe("O labirinto fornecido não possui solução!");
    }
    System.out.println("Labirinto solucionado com sucesso! Confira o arquivo: " + arquivoSolucao);

  }

}
