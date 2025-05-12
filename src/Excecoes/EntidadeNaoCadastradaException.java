package Excecoes;

public class EntidadeNaoCadastradaException extends Exception {
    public EntidadeNaoCadastradaException(String entidade) {
        super("A entidade " + entidade + " não foi cadastrada.");
    }
}
