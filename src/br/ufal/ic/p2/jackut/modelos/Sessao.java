package br.ufal.ic.p2.jackut.modelos;

public class Sessao extends Entidade {

    private static final long serialVersionUID = 1L;

    private String loginUsuario;

    public Sessao(String id, String loginUsuario) {
        super(id);
        this.loginUsuario = loginUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }
}