package Excecoes;

public class EntidadeDesativadaException extends Exception {
  public EntidadeDesativadaException(String entidade) {
    super("A entidade '" + entidade + "' está desativada e não pode ser utilizada.");
  }
}
