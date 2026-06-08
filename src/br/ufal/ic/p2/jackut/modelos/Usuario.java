package br.ufal.ic.p2.jackut.modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Usuario extends Entidade {

    private static final long serialVersionUID = 1L;

    private String login;
    private String senha;
    private Map<String, String> perfil;
    private List<String> amigos;
    private List<String> convitesEnviados;
    private LinkedList<Recado> recados;

    public Usuario(String login, String senha, String nome) {
        super(login);
        this.login = login;
        this.senha = senha;
        this.perfil = new HashMap<String, String>();
        this.amigos = new ArrayList<String>();
        this.convitesEnviados = new ArrayList<String>();
        this.recados = new LinkedList<Recado>();
        this.perfil.put("nome", nome);
    }

    public String getLogin() {
        return login;
    }

    public boolean senhaConfere(String senha) {
        return this.senha.equals(senha);
    }

    public void editarPerfil(String atributo, String valor) {
        perfil.put(atributo, valor);
    }

    public boolean possuiAtributo(String atributo) {
        return perfil.containsKey(atributo);
    }

    public String getAtributo(String atributo) {
        return perfil.get(atributo);
    }

    public boolean ehAmigo(String loginAmigo) {
        return amigos.contains(loginAmigo);
    }

    public void adicionarAmigoEfetivado(String loginAmigo) {
        if (!amigos.contains(loginAmigo)) {
            amigos.add(loginAmigo);
        }
    }

    public void adicionarConviteEnviado(String loginConvidado) {
        if (!convitesEnviados.contains(loginConvidado)) {
            convitesEnviados.add(loginConvidado);
        }
    }

    public void removerConviteEnviado(String loginConvidado) {
        convitesEnviados.remove(loginConvidado);
    }

    public boolean possuiConviteEnviadoPara(String loginConvidado) {
        return convitesEnviados.contains(loginConvidado);
    }

    public List<String> getAmigos() {
        return new ArrayList<String>(amigos);
    }

    public void receberRecado(Recado recado) {
        recados.addLast(recado);
    }

    public boolean possuiRecados() {
        return !recados.isEmpty();
    }

    public Recado removerPrimeiroRecado() {
        return recados.removeFirst();
    }
}