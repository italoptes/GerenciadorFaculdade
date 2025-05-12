package Excecoes;

public class EntidadeNullException extends Exception {
  public EntidadeNullException(String campo) {
    super("O campo '" + campo + "' está nulo ou vazio.");
  }
}