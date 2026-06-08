package br.ufal.ic.p2.jackut.excecoes;

public class JackutException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public JackutException(String mensagem) {
        super(mensagem);
    }
}