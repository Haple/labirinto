package br.edu.puccampinas.projetoc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import br.edu.puccampinas.projetoc.labirinto.BuscaException;
import br.edu.puccampinas.projetoc.labirinto.CarregamentoException;
import br.edu.puccampinas.projetoc.labirinto.Labirinto;

/**
 * Serviço de testes do labirinto
 * 
 * @author vinicius
 *
 */
public class DriverLabirinto {
  private static final Logger LOGGER = Logger.getLogger(DriverLabirinto.class.getName());

  private String mapaLabirinto;
  private String pastaSolucao;
  private String nomeArquivo;
  private String arquivoSolucao;

  /**
   * Constrói um driver em linha de comando para os testes do labirinto
   * 
   * @param mapaLabirinto Caminho do arquivo da entrada do labirinto
   */
  public DriverLabirinto(String mapaLabirinto) {
    super();
    if (mapaLabirinto == null || mapaLabirinto.trim() == "") {
      throw new NullPointerException("Indique um caminho de labirinto válido!");
    }
    this.mapaLabirinto = mapaLabirinto;
    this.pastaSolucao = this.mapaLabirinto.substring(0, mapaLabirinto.lastIndexOf(File.separator));
    this.nomeArquivo = this.mapaLabirinto.substring(
        this.mapaLabirinto.lastIndexOf(File.separator) + 1, this.mapaLabirinto.lastIndexOf("."));
    this.arquivoSolucao = this.pastaSolucao + "/" + this.nomeArquivo + "(solucionado).txt";
  }

  /**
   * Inicializa o processamento do labirinto
   */
  public void run() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoSolucao))) {
      Labirinto l = new Labirinto(mapaLabirinto);
      l.findExit();
      System.out.println("Labirinto: \n" + l);
      System.out.println("Coordenada da saída: " + l.getAtual());
      bw.write(l.toString());
      System.out.println("Labirinto solucionado com sucesso! Confira o arquivo: " + arquivoSolucao);
    } catch (CarregamentoException e) {
      cleanOutput();
      LOGGER.severe("Falha ao carregar o labirinto: " + e.getMessage());
    } catch (IOException e) {
      LOGGER.severe("Erro ao criar arquivo de saída: " + e.getMessage());
    } catch (BuscaException e) {
      cleanOutput();
      LOGGER.severe("O labirinto fornecido não possui solução!");
    }
  }

  /**
   * Remove o arquivo de saída, em caso de erro
   */
  private void cleanOutput() {
    File saida = new File(this.arquivoSolucao);
    saida.delete();
  }

}
