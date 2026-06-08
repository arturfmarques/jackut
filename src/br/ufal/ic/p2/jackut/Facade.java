package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.excecoes.JackutException;
import br.ufal.ic.p2.jackut.servicos.SistemaJackut;

/**
 * Fachada do sistema Jackut.
 *
 * <p>Esta classe representa o ponto de entrada utilizado pelos testes de aceitação
 * do EasyAccept. Ela não concentra as regras de negócio, apenas delega as operações
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
     * Cria um usuário no Jackut.
     *
     * @param login login único do usuário.
     * @param senha senha de acesso do usuário.
     * @param nome nome público do usuário.
     * @throws JackutException se o login for inválido, a senha for inválida
     * ou já existir uma conta com o mesmo login.
     */
    public void criarUsuario(String login, String senha, String nome) {
        sistema.criarUsuario(login, senha, nome);
    }

    /**
     * Abre uma sessão para um usuário cadastrado.
     *
     * @param login login do usuário.
     * @param senha senha do usuário.
     * @return identificador da sessão aberta.
     * @throws JackutException se o login ou a senha forem inválidos.
     */
    public String abrirSessao(String login, String senha) {
        return sistema.abrirSessao(login, senha);
    }

    /**
     * Retorna o valor de um atributo do perfil de um usuário.
     *
     * @param login login do usuário.
     * @param atributo nome do atributo consultado.
     * @return valor do atributo informado.
     * @throws JackutException se o usuário não estiver cadastrado ou se o atributo
     * não estiver preenchido.
     */
    public String getAtributoUsuario(String login, String atributo) {
        return sistema.getAtributoUsuario(login, atributo);
    }

    /**
     * Edita um atributo do perfil do usuário associado à sessão informada.
     *
     * @param id identificador da sessão.
     * @param atributo nome do atributo a ser criado ou alterado.
     * @param valor valor do atributo.
     * @throws JackutException se a sessão não estiver associada a um usuário válido.
     */
    public void editarPerfil(String id, String atributo, String valor) {
        sistema.editarPerfil(id, atributo, valor);
    }

    /**
     * Solicita a adição de um usuário como amigo.
     *
     * @param id identificador da sessão do usuário solicitante.
     * @param amigo login do usuário a ser adicionado.
     * @throws JackutException se o usuário não existir, se a amizade já existir,
     * se o convite já estiver pendente ou se o usuário tentar adicionar a si mesmo.
     */
    public void adicionarAmigo(String id, String amigo) {
        sistema.adicionarAmigo(id, amigo);
    }

    /**
     * Verifica se dois usuários são amigos.
     *
     * @param login login do primeiro usuário.
     * @param amigo login do segundo usuário.
     * @return {@code true} se os usuários forem amigos; {@code false} caso contrário.
     * @throws JackutException se algum dos usuários não estiver cadastrado.
     */
    public boolean ehAmigo(String login, String amigo) {
        return sistema.ehAmigo(login, amigo);
    }

    /**
     * Retorna a lista de amigos de um usuário no formato exigido pelos testes.
     *
     * @param login login do usuário.
     * @return lista de amigos codificada em uma string.
     * @throws JackutException se o usuário não estiver cadastrado.
     */
    public String getAmigos(String login) {
        return sistema.getAmigos(login);
    }

    /**
     * Envia um recado para outro usuário cadastrado.
     *
     * @param id identificador da sessão do remetente.
     * @param destinatario login do usuário destinatário.
     * @param recado texto do recado.
     * @throws JackutException se o destinatário não existir, se a sessão for inválida
     * ou se o usuário tentar enviar recado para si mesmo.
     */
    public void enviarRecado(String id, String destinatario, String recado) {
        sistema.enviarRecado(id, destinatario, recado);
    }

    /**
     * Lê o primeiro recado pendente do usuário associado à sessão.
     *
     * @param id identificador da sessão.
     * @return texto do primeiro recado pendente.
     * @throws JackutException se a sessão for inválida ou se não houver recados.
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