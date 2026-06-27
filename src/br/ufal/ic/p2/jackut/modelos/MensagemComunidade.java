package br.ufal.ic.p2.jackut.modelos;

/**
 * Representa uma mensagem enviada para uma comunidade.
 */
public class MensagemComunidade extends Mensagem {

    private static final long serialVersionUID = 1L;

    private String comunidade;

    /**
     * Cria uma mensagem de comunidade.
     *
     * @param remetente login do remetente.
     * @param texto texto da mensagem.
     * @param comunidade nome da comunidade.
     */
    public MensagemComunidade(String remetente, String texto, String comunidade) {
        super(remetente, texto);
        this.comunidade = comunidade;
    }

    /**
     * Retorna o nome da comunidade.
     *
     * @return nome da comunidade.
     */
    public String getComunidade() {
        return comunidade;
    }
}
