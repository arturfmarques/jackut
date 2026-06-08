package br.ufal.ic.p2.jackut.modelos;

/**
 * Representa uma sessão aberta por um usuário autenticado.
 *
 * <p>A sessão armazena um identificador e o login do usuário autenticado,
 * permitindo que operações privadas sejam realizadas apenas por usuários
 * com sessão aberta.</p>
 */
public class Sessao extends Entidade {

    private static final long serialVersionUID = 1L;

    private String loginUsuario;

    /**
     * Cria uma nova sessão.
     *
     * @param id identificador da sessão.
     * @param loginUsuario login do usuário associado à sessão.
     */
    public Sessao(String id, String loginUsuario) {
        super(id);
        this.loginUsuario = loginUsuario;
    }

    /**
     * Retorna o login do usuário associado à sessão.
     *
     * @return login do usuário da sessão.
     */
    public String getLoginUsuario() {
        return loginUsuario;
    }
}