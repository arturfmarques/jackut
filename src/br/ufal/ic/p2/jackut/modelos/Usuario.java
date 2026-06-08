package br.ufal.ic.p2.jackut.modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Representa um usuário cadastrado no sistema Jackut.
 *
 * <p>O usuário armazena dados de autenticação, atributos de perfil,
 * amigos efetivados, convites de amizade enviados e recados recebidos.</p>
 */
public class Usuario extends Entidade {

    private static final long serialVersionUID = 1L;

    private String login;
    private String senha;
    private Map<String, String> perfil;
    private List<String> amigos;
    private List<String> convitesEnviados;
    private LinkedList<Recado> recados;

    /**
     * Cria um novo usuário.
     *
     * @param login login único do usuário.
     * @param senha senha de acesso do usuário.
     * @param nome nome público do usuário.
     */
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

    /**
     * Retorna o login do usuário.
     *
     * @return login do usuário.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Verifica se a senha informada corresponde à senha cadastrada.
     *
     * @param senha senha a ser conferida.
     * @return {@code true} se a senha estiver correta; {@code false} caso contrário.
     */
    public boolean senhaConfere(String senha) {
        return this.senha.equals(senha);
    }

    /**
     * Cria ou altera um atributo do perfil.
     *
     * @param atributo nome do atributo.
     * @param valor valor do atributo.
     */
    public void editarPerfil(String atributo, String valor) {
        perfil.put(atributo, valor);
    }

    /**
     * Verifica se o perfil possui determinado atributo preenchido.
     *
     * @param atributo nome do atributo.
     * @return {@code true} se o atributo existir; {@code false} caso contrário.
     */
    public boolean possuiAtributo(String atributo) {
        return perfil.containsKey(atributo);
    }

    /**
     * Retorna o valor de um atributo do perfil.
     *
     * @param atributo nome do atributo.
     * @return valor do atributo.
     */
    public String getAtributo(String atributo) {
        return perfil.get(atributo);
    }

    /**
     * Verifica se o usuário é amigo de outro usuário.
     *
     * @param loginAmigo login do possível amigo.
     * @return {@code true} se o usuário informado for amigo; {@code false} caso contrário.
     */
    public boolean ehAmigo(String loginAmigo) {
        return amigos.contains(loginAmigo);
    }

    /**
     * Adiciona um amigo já efetivado à lista do usuário.
     *
     * @param loginAmigo login do amigo.
     */
    public void adicionarAmigoEfetivado(String loginAmigo) {
        if (!amigos.contains(loginAmigo)) {
            amigos.add(loginAmigo);
        }
    }

    /**
     * Registra um convite de amizade enviado pelo usuário.
     *
     * @param loginConvidado login do usuário convidado.
     */
    public void adicionarConviteEnviado(String loginConvidado) {
        if (!convitesEnviados.contains(loginConvidado)) {
            convitesEnviados.add(loginConvidado);
        }
    }

    /**
     * Remove um convite de amizade enviado anteriormente.
     *
     * @param loginConvidado login do usuário convidado.
     */
    public void removerConviteEnviado(String loginConvidado) {
        convitesEnviados.remove(loginConvidado);
    }

    /**
     * Verifica se existe convite de amizade enviado para um usuário.
     *
     * @param loginConvidado login do usuário convidado.
     * @return {@code true} se existir convite pendente; {@code false} caso contrário.
     */
    public boolean possuiConviteEnviadoPara(String loginConvidado) {
        return convitesEnviados.contains(loginConvidado);
    }

    /**
     * Retorna uma cópia da lista de amigos.
     *
     * @return cópia da lista de amigos.
     */
    public List<String> getAmigos() {
        return new ArrayList<String>(amigos);
    }

    /**
     * Adiciona um recado à fila de recados recebidos.
     *
     * @param recado recado recebido.
     */
    public void receberRecado(Recado recado) {
        recados.addLast(recado);
    }

    /**
     * Verifica se existem recados pendentes.
     *
     * @return {@code true} se houver recados pendentes; {@code false} caso contrário.
     */
    public boolean possuiRecados() {
        return !recados.isEmpty();
    }

    /**
     * Remove e retorna o primeiro recado da fila.
     *
     * @return primeiro recado recebido ainda não lido.
     */
    public Recado removerPrimeiroRecado() {
        return recados.removeFirst();
    }
}