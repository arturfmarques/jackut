package br.ufal.ic.p2.jackut.modelos;

/**
 * Representa um recado enviado entre usuarios do Jackut.
 */
public class Recado extends Mensagem {

    private static final long serialVersionUID = 1L;

    /**
     * Cria um novo recado.
     *
     * @param remetente login do usuario que enviou o recado.
     * @param texto conteudo textual do recado.
     */
    public Recado(String remetente, String texto) {
        super(remetente, texto);
    }
}
