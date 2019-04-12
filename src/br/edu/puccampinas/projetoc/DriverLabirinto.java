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
  public void start() {
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((arquivoSolucao == null) ? 0 : arquivoSolucao.hashCode());
    result = prime * result + ((mapaLabirinto == null) ? 0 : mapaLabirinto.hashCode());
    result = prime * result + ((nomeArquivo == null) ? 0 : nomeArquivo.hashCode());
    result = prime * result + ((pastaSolucao == null) ? 0 : pastaSolucao.hashCode());
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
    DriverLabirinto other = (DriverLabirinto) obj;
    if (arquivoSolucao == null) {
      if (other.arquivoSolucao != null)
        return false;
    } else if (!arquivoSolucao.equals(other.arquivoSolucao))
      return false;
    if (mapaLabirinto == null) {
      if (other.mapaLabirinto != null)
        return false;
    } else if (!mapaLabirinto.equals(other.mapaLabirinto))
      return false;
    if (nomeArquivo == null) {
      if (other.nomeArquivo != null)
        return false;
    } else if (!nomeArquivo.equals(other.nomeArquivo))
      return false;
    if (pastaSolucao == null) {
      if (other.pastaSolucao != null)
        return false;
    } else if (!pastaSolucao.equals(other.pastaSolucao))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "DriverLabirinto [mapaLabirinto=" + mapaLabirinto + ", pastaSolucao=" + pastaSolucao
        + ", nomeArquivo=" + nomeArquivo + ", arquivoSolucao=" + arquivoSolucao + "]";
  }

}
