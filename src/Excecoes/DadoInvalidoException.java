package Excecoes;

public class DadoInvalidoException extends Exception {
    public DadoInvalidoException(String campo) {
        super("Dado inválido: o campo '" + campo + "' contém um valor incorreto.");
    }
}
