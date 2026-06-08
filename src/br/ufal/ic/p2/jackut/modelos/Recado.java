package br.ufal.ic.p2.jackut.modelos;

import java.io.Serializable;

/**
 * Representa um recado enviado entre usuários do Jackut.
 *
 * <p>O recado armazena o login do remetente e o texto enviado. A leitura
 * realizada pelos testes utiliza apenas o texto, mas manter o remetente
 * melhora a representação do domínio.</p>
 */
public class Recado implements Serializable {

    private static final long serialVersionUID = 1L;

    private String remetente;
    private String texto;

    /**
     * Cria um novo recado.
     *
     * @param remetente login do usuário que enviou o recado.
     * @param texto conteúdo textual do recado.
     */
    public Recado(String remetente, String texto) {
        this.remetente = remetente;
        this.texto = texto;
    }

    /**
     * Retorna o login do remetente.
     *
     * @return login do remetente.
     */
    public String getRemetente() {
        return remetente;
    }

    /**
     * Retorna o texto do recado.
     *
     * @return texto do recado.
     */
    public String getTexto() {
        return texto;
    }
}