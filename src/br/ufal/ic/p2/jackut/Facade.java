package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.excecoes.JackutException;
import br.ufal.ic.p2.jackut.servicos.SistemaJackut;

/**
 * Fachada do sistema Jackut.
 *
 * <p>Esta classe representa o ponto de entrada utilizado pelos testes de aceitacao
 * do EasyAccept. Ela nao concentra as regras de negocio, apenas delega as operacoes
 * para a classe {@link SistemaJackut}.</p>
 */
public class Facade {

    private SistemaJackut sistema;

    /**
     * Cria uma nova fachada e inicializa o sistema Jackut.
     */
    public Facade() {
        this.sistema = new SistemaJackut();
    }

    /**
     * Apaga todos os dados mantidos pelo sistema.
     */
    public void zerarSistema() {
        sistema.zerarSistema();
    }

    /**
     * Cria um usuario no Jackut.
     *
     * @param login login unico do usuario.
     * @param senha senha de acesso do usuario.
     * @param nome nome publico do usuario.
     * @throws JackutException se o login for invalido, a senha for invalida
     * ou ja existir uma conta com o mesmo login.
     */
    public void criarUsuario(String login, String senha, String nome) {
        sistema.criarUsuario(login, senha, nome);
    }

    /**
     * Abre uma sessao para um usuario cadastrado.
     *
     * @param login login do usuario.
     * @param senha senha do usuario.
     * @return identificador da sessao aberta.
     * @throws JackutException se o login ou a senha forem invalidos.
     */
    public String abrirSessao(String login, String senha) {
        return sistema.abrirSessao(login, senha);
    }

    /**
     * Retorna o valor de um atributo do perfil de um usuario.
     *
     * @param login login do usuario.
     * @param atributo nome do atributo consultado.
     * @return valor do atributo informado.
     * @throws JackutException se o usuario nao estiver cadastrado ou se o atributo
     * nao estiver preenchido.
     */
    public String getAtributoUsuario(String login, String atributo) {
        return sistema.getAtributoUsuario(login, atributo);
    }

    /**
     * Edita um atributo do perfil do usuario associado a sessao informada.
     *
     * @param id identificador da sessao.
     * @param atributo nome do atributo a ser criado ou alterado.
     * @param valor valor do atributo.
     * @throws JackutException se a sessao nao estiver associada a um usuario valido.
     */
    public void editarPerfil(String id, String atributo, String valor) {
        sistema.editarPerfil(id, atributo, valor);
    }

    /**
     * Solicita a adicao de um usuario como amigo.
     *
     * @param id identificador da sessao do usuario solicitante.
     * @param amigo login do usuario a ser adicionado.
     * @throws JackutException se o usuario nao existir, se a amizade ja existir,
     * se o convite ja estiver pendente ou se o usuario tentar adicionar a si mesmo.
     */
    public void adicionarAmigo(String id, String amigo) {
        sistema.adicionarAmigo(id, amigo);
    }

    /**
     * Verifica se dois usuarios sao amigos.
     *
     * @param login login do primeiro usuario.
     * @param amigo login do segundo usuario.
     * @return {@code true} se os usuarios forem amigos; {@code false} caso contrario.
     * @throws JackutException se algum dos usuarios nao estiver cadastrado.
     */
    public boolean ehAmigo(String login, String amigo) {
        return sistema.ehAmigo(login, amigo);
    }

    /**
     * Retorna a lista de amigos de um usuario no formato exigido pelos testes.
     *
     * @param login login do usuario.
     * @return lista de amigos codificada em uma string.
     * @throws JackutException se o usuario nao estiver cadastrado.
     */
    public String getAmigos(String login) {
        return sistema.getAmigos(login);
    }

    /**
     * Envia um recado para outro usuario cadastrado.
     *
     * @param id identificador da sessao do remetente.
     * @param destinatario login do usuario destinatario.
     * @param recado texto do recado.
     * @throws JackutException se o destinatario nao existir, se a sessao for invalida
     * ou se o usuario tentar enviar recado para si mesmo.
     */
    public void enviarRecado(String id, String destinatario, String recado) {
        sistema.enviarRecado(id, destinatario, recado);
    }

    /**
     * Le o primeiro recado pendente do usuario associado a sessao.
     *
     * @param id identificador da sessao.
     * @return texto do primeiro recado pendente.
     * @throws JackutException se a sessao for invalida ou se nao houver recados.
     */
    public String lerRecado(String id) {
        return sistema.lerRecado(id);
    }

    /**
     * Salva os dados do sistema em arquivo.
     */
    public void encerrarSistema() {
        sistema.encerrarSistema();
    }
}