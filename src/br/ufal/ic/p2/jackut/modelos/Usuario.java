package br.ufal.ic.p2.jackut.modelos;

import java.io.Serializable;
import java.util.List;

import br.ufal.ic.p2.jackut.contratos.Identificavel;

/**
 * Representa um usuario cadastrado no sistema Jackut.
 *
 * <p>A classe atua como entidade principal do usuario e delega detalhes internos
 * de perfil, amizades, participacoes, relacionamentos e caixa de entrada para
 * objetos de apoio mais coesos.</p>
 */
public class Usuario implements Identificavel, Serializable {

    private static final long serialVersionUID = 1L;

    private String login;
    private String senha;
    private PerfilUsuario perfil;
    private AmizadesUsuario amizades;
    private ParticipacoesUsuario participacoes;
    private RelacionamentosUsuario relacionamentos;
    private CaixaEntrada caixaEntrada;

    /**
     * Cria um novo usuario.
     *
     * @param login login unico do usuario.
     * @param senha senha de acesso do usuario.
     * @param nome nome publico do usuario.
     */
    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.perfil = new PerfilUsuario(nome);
        this.amizades = new AmizadesUsuario();
        this.participacoes = new ParticipacoesUsuario();
        this.relacionamentos = new RelacionamentosUsuario();
        this.caixaEntrada = new CaixaEntrada();
    }

    /**
     * Retorna o identificador do usuario.
     *
     * @return login do usuario.
     */
    public String getId() {
        return login;
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
        perfil.editar(atributo, valor);
    }

    /**
     * Verifica se o perfil possui determinado atributo preenchido.
     *
     * @param atributo nome do atributo.
     * @return {@code true} se o atributo existir; {@code false} caso contrario.
     */
    public boolean possuiAtributo(String atributo) {
        return perfil.possuiAtributo(atributo);
    }

    /**
     * Retorna o valor de um atributo do perfil.
     *
     * @param atributo nome do atributo.
     * @return valor do atributo.
     */
    public String getAtributo(String atributo) {
        return perfil.getAtributo(atributo);
    }

    /**
     * Verifica se o usuario e amigo de outro usuario.
     *
     * @param loginAmigo login do possivel amigo.
     * @return {@code true} se o usuario informado for amigo; {@code false} caso contrario.
     */
    public boolean ehAmigo(String loginAmigo) {
        return amizades.ehAmigo(loginAmigo);
    }

    /**
     * Adiciona um amigo efetivado.
     *
     * @param loginAmigo login do amigo.
     */
    public void adicionarAmigoEfetivado(String loginAmigo) {
        amizades.adicionarAmigoEfetivado(loginAmigo);
    }

    /**
     * Registra um convite de amizade enviado.
     *
     * @param loginConvidado login do usuario convidado.
     */
    public void adicionarConviteEnviado(String loginConvidado) {
        amizades.adicionarConviteEnviado(loginConvidado);
    }

    /**
     * Remove um convite de amizade enviado.
     *
     * @param loginConvidado login do usuario convidado.
     */
    public void removerConviteEnviado(String loginConvidado) {
        amizades.removerConviteEnviado(loginConvidado);
    }

    /**
     * Verifica se existe convite enviado para um usuario.
     *
     * @param loginConvidado login do usuario convidado.
     * @return {@code true} se existir convite pendente; {@code false} caso contrario.
     */
    public boolean possuiConviteEnviadoPara(String loginConvidado) {
        return amizades.possuiConviteEnviadoPara(loginConvidado);
    }

    /**
     * Retorna uma copia dos amigos.
     *
     * @return lista de amigos.
     */
    public List<String> getAmigos() {
        return amizades.getAmigos();
    }

    /**
     * Adiciona uma comunidade ao usuario.
     *
     * @param nomeComunidade nome da comunidade.
     */
    public void adicionarComunidade(String nomeComunidade) {
        participacoes.adicionarComunidade(nomeComunidade);
    }

    /**
     * Remove uma comunidade do usuario.
     *
     * @param nomeComunidade nome da comunidade.
     */
    public void removerComunidade(String nomeComunidade) {
        participacoes.removerComunidade(nomeComunidade);
    }

    /**
     * Verifica se o usuario participa de uma comunidade.
     *
     * @param nomeComunidade nome da comunidade.
     * @return {@code true} se participa; {@code false} caso contrario.
     */
    public boolean participaDaComunidade(String nomeComunidade) {
        return participacoes.participaDaComunidade(nomeComunidade);
    }

    /**
     * Retorna as comunidades do usuario.
     *
     * @return lista de comunidades.
     */
    public List<String> getComunidades() {
        return participacoes.getComunidades();
    }

    /**
     * Adiciona um idolo ao usuario.
     *
     * @param loginIdolo login do idolo.
     */
    public void adicionarIdolo(String loginIdolo) {
        relacionamentos.adicionarIdolo(loginIdolo);
    }

    /**
     * Verifica se o usuario e fa de outro usuario.
     *
     * @param loginIdolo login do idolo.
     * @return {@code true} se for fa; {@code false} caso contrario.
     */
    public boolean ehFaDe(String loginIdolo) {
        return relacionamentos.ehFaDe(loginIdolo);
    }

    /**
     * Adiciona um fa ao usuario.
     *
     * @param loginFa login do fa.
     */
    public void adicionarFa(String loginFa) {
        relacionamentos.adicionarFa(loginFa);
    }

    /**
     * Retorna os fas do usuario.
     *
     * @return lista de fas.
     */
    public List<String> getFas() {
        return relacionamentos.getFas();
    }

    /**
     * Adiciona uma paquera ao usuario.
     *
     * @param loginPaquera login da paquera.
     */
    public void adicionarPaquera(String loginPaquera) {
        relacionamentos.adicionarPaquera(loginPaquera);
    }

    /**
     * Verifica se o usuario possui uma paquera.
     *
     * @param loginPaquera login da paquera.
     * @return {@code true} se for paquera; {@code false} caso contrario.
     */
    public boolean ehPaqueraDe(String loginPaquera) {
        return relacionamentos.ehPaqueraDe(loginPaquera);
    }

    /**
     * Retorna as paqueras do usuario.
     *
     * @return lista de paqueras.
     */
    public List<String> getPaqueras() {
        return relacionamentos.getPaqueras();
    }

    /**
     * Adiciona um inimigo ao usuario.
     *
     * @param loginInimigo login do inimigo.
     */
    public void adicionarInimigo(String loginInimigo) {
        relacionamentos.adicionarInimigo(loginInimigo);
    }

    /**
     * Verifica se o usuario possui um inimigo.
     *
     * @param loginInimigo login do inimigo.
     * @return {@code true} se for inimigo; {@code false} caso contrario.
     */
    public boolean possuiInimigo(String loginInimigo) {
        return relacionamentos.possuiInimigo(loginInimigo);
    }

    /**
     * Recebe um recado.
     *
     * @param recado recado recebido.
     */
    public void receberRecado(Recado recado) {
        caixaEntrada.receberRecado(recado);
    }

    /**
     * Verifica se existem recados pendentes.
     *
     * @return {@code true} se houver recados; {@code false} caso contrario.
     */
    public boolean possuiRecados() {
        return caixaEntrada.possuiRecados();
    }

    /**
     * Remove o primeiro recado pendente.
     *
     * @return primeiro recado da fila.
     */
    public Recado removerPrimeiroRecado() {
        return caixaEntrada.removerPrimeiroRecado();
    }

    /**
     * Recebe uma mensagem de comunidade.
     *
     * @param mensagem mensagem recebida.
     */
    public void receberMensagem(MensagemComunidade mensagem) {
        caixaEntrada.receberMensagem(mensagem);
    }

    /**
     * Verifica se existem mensagens pendentes.
     *
     * @return {@code true} se houver mensagens; {@code false} caso contrario.
     */
    public boolean possuiMensagens() {
        return caixaEntrada.possuiMensagens();
    }

    /**
     * Remove a primeira mensagem pendente.
     *
     * @return primeira mensagem da fila.
     */
    public MensagemComunidade removerPrimeiraMensagem() {
        return caixaEntrada.removerPrimeiraMensagem();
    }

    /**
     * Remove todas as referencias internas a outro usuario.
     *
     * @param loginRemovido login removido do sistema.
     */
    public void removerReferenciasAoUsuario(String loginRemovido) {
        amizades.removerReferenciasAoUsuario(loginRemovido);
        relacionamentos.removerReferenciasAoUsuario(loginRemovido);
        caixaEntrada.removerRecadosDoRemetente(loginRemovido);
        caixaEntrada.removerMensagensDoRemetente(loginRemovido);
    }

    /**
     * Remove recados recebidos de um usuario.
     *
     * @param loginRemovido login removido do sistema.
     */
    public void removerRecadosDoRemetente(String loginRemovido) {
        caixaEntrada.removerRecadosDoRemetente(loginRemovido);
    }

    /**
     * Remove mensagens recebidas de um usuario.
     *
     * @param loginRemovido login removido do sistema.
     */
    public void removerMensagensDoRemetente(String loginRemovido) {
        caixaEntrada.removerMensagensDoRemetente(loginRemovido);
    }
}
