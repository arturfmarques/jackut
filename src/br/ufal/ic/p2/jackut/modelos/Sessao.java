package br.ufal.ic.p2.jackut.modelos;

/**
 * Representa uma sessao aberta por um usuario autenticado.
 *
 * <p>A sessao armazena um identificador e o login do usuario autenticado,
 * permitindo que operacoes privadas sejam realizadas apenas por usuarios
 * com sessao aberta.</p>
 */
public class Sessao extends Entidade {

    private static final long serialVersionUID = 1L;

    private String loginUsuario;

    /**
     * Cria uma nova sessao.
     *
     * @param id identificador da sessao.
     * @param loginUsuario login do usuario associado a sessao.
     */
    public Sessao(String id, String loginUsuario) {
        super(id);
        this.loginUsuario = loginUsuario;
    }

    /**
     * Retorna o login do usuario associado a sessao.
     *
     * @return login do usuario da sessao.
     */
    public String getLoginUsuario() {
        return loginUsuario;
    }
}