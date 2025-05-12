package Excecoes;

public class EntidadeNaoEncontradaException extends Exception {
    public EntidadeNaoEncontradaException(String entidade) {
        super("Entidade '" + entidade + "' não encontrada.");
    }
}
