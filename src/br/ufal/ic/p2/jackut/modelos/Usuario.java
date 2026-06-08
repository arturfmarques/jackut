package br.ufal.ic.p2.jackut.modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Representa um usuario cadastrado no sistema Jackut.
 *
 * <p>O usuario armazena dados de autenticacao, atributos de perfil,
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
     * Cria um novo usuario.
     *
     * @param login login unico do usuario.
     * @param senha senha de acesso do usuario.
     * @param nome nome publico do usuario.
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
     * Retorna o login do usuario.
     *
     * @return login do usuario.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Verifica se a senha informada corresponde a senha cadastrada.
     *
     * @param senha senha a ser conferida.
     * @return {@code true} se a senha estiver correta; {@code false} caso contrario.
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
     * @return {@code true} se o atributo existir; {@code false} caso contrario.
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
     * Verifica se o usuario e amigo de outro usuario.
     *
     * @param loginAmigo login do possivel amigo.
     * @return {@code true} se o usuario informado for amigo; {@code false} caso contrario.
     */
    public boolean ehAmigo(String loginAmigo) {
        return amigos.contains(loginAmigo);
    }

    /**
     * Adiciona um amigo ja efetivado a lista do usuario.
     *
     * @param loginAmigo login do amigo.
     */
    public void adicionarAmigoEfetivado(String loginAmigo) {
        if (!amigos.contains(loginAmigo)) {
            amigos.add(loginAmigo);
        }
    }

    /**
     * Registra um convite de amizade enviado pelo usuario.
     *
     * @param loginConvidado login do usuario convidado.
     */
    public void adicionarConviteEnviado(String loginConvidado) {
        if (!convitesEnviados.contains(loginConvidado)) {
            convitesEnviados.add(loginConvidado);
        }
    }

    /**
     * Remove um convite de amizade enviado anteriormente.
     *
     * @param loginConvidado login do usuario convidado.
     */
    public void removerConviteEnviado(String loginConvidado) {
        convitesEnviados.remove(loginConvidado);
    }

    /**
     * Verifica se existe convite de amizade enviado para um usuario.
     *
     * @param loginConvidado login do usuario convidado.
     * @return {@code true} se existir convite pendente; {@code false} caso contrario.
     */
    public boolean possuiConviteEnviadoPara(String loginConvidado) {
        return convitesEnviados.contains(loginConvidado);
    }

    /**
     * Retorna uma copia da lista de amigos.
     *
     * @return copia da lista de amigos.
     */
    public List<String> getAmigos() {
        return new ArrayList<String>(amigos);
    }

    /**
     * Adiciona um recado a fila de recados recebidos.
     *
     * @param recado recado recebido.
     */
    public void receberRecado(Recado recado) {
        recados.addLast(recado);
    }

    /**
     * Verifica se existem recados pendentes.
     *
     * @return {@code true} se houver recados pendentes; {@code false} caso contrario.
     */
    public boolean possuiRecados() {
        return !recados.isEmpty();
    }

    /**
     * Remove e retorna o primeiro recado da fila.
     *
     * @return primeiro recado recebido ainda nao lido.
     */
    public Recado removerPrimeiroRecado() {
        return recados.removeFirst();
    }
}