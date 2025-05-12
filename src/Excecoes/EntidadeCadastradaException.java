package Excecoes;

public class EntidadeCadastradaException extends Exception {
  public EntidadeCadastradaException(String entidade) {
    super("A entidade '" + entidade + "' já está cadastrada.");
  }
}
