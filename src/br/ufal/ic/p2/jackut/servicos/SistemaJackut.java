package br.ufal.ic.p2.jackut.servicos;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.ufal.ic.p2.jackut.contratos.Persistidor;
import br.ufal.ic.p2.jackut.excecoes.JackutException;
import br.ufal.ic.p2.jackut.modelos.Recado;
import br.ufal.ic.p2.jackut.modelos.Sessao;
import br.ufal.ic.p2.jackut.modelos.Usuario;

/**
 * Classe central da logica de negocio do sistema Jackut.
 *
 * <p>Concentra as operacoes relativas a usuarios, sessoes, perfis,
 * amizades e recados do Milestone 01.</p>
 */
public class SistemaJackut {

    private Map<String, Usuario> usuarios;
    private Map<String, Sessao> sessoes;
    private Persistidor persistidor;
    private int proximoIdSessao;

    /**
     * Cria o sistema Jackut e carrega os dados persistidos anteriormente.
     */
    public SistemaJackut() {
        this.persistidor = new ServicoPersistencia();
        this.usuarios = persistidor.carregar();
        this.sessoes = new LinkedHashMap<String, Sessao>();
        this.proximoIdSessao = 1;
    }

    /**
     * Apaga todos os dados do sistema e remove os dados persistidos.
     */
    public void zerarSistema() {
        usuarios.clear();
        sessoes.clear();
        proximoIdSessao = 1;
        persistidor.limpar();
    }

    /**
     * Cria uma nova conta de usuario.
     *
     * @param login login unico do usuario.
     * @param senha senha de acesso do usuario.
     * @param nome nome publico do usuario.
     * @throws JackutException se o login for invalido, a senha for invalida
     * ou ja existir conta com o mesmo login.
     */
    public void criarUsuario(String login, String senha, String nome) {
        validarLoginParaCadastro(login);
        validarSenhaParaCadastro(senha);

        if (usuarios.containsKey(login)) {
            throw new JackutException("Conta com esse nome j\u00e1 existe.");
        }

        usuarios.put(login, new Usuario(login, senha, nome));
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
        Usuario usuario = usuarios.get(login);

        if (usuario == null || !usuario.senhaConfere(senha)) {
            throw new JackutException("Login ou senha inv\u00e1lidos.");
        }

        String id = String.valueOf(proximoIdSessao);
        proximoIdSessao++;
        sessoes.put(id, new Sessao(id, login));
        return id;
    }

    /**
     * Retorna um atributo do perfil de um usuario.
     *
     * @param login login do usuario.
     * @param atributo nome do atributo consultado.
     * @return valor do atributo.
     * @throws JackutException se o usuario nao estiver cadastrado ou o atributo
     * nao estiver preenchido.
     */
    public String getAtributoUsuario(String login, String atributo) {
        Usuario usuario = buscarUsuario(login);

        if (!usuario.possuiAtributo(atributo)) {
            throw new JackutException("Atributo n\u00e3o preenchido.");
        }

        return usuario.getAtributo(atributo);
    }

    /**
     * Edita o perfil do usuario associado a sessao informada.
     *
     * @param id identificador da sessao.
     * @param atributo atributo a ser criado ou alterado.
     * @param valor valor do atributo.
     * @throws JackutException se a sessao nao estiver associada a um usuario valido.
     */
    public void editarPerfil(String id, String atributo, String valor) {
        Usuario usuario = buscarUsuarioDaSessao(id);
        usuario.editarPerfil(atributo, valor);
    }

    /**
     * Adiciona um usuario como amigo ou aceita um convite de amizade existente.
     *
     * @param id identificador da sessao do usuario solicitante.
     * @param amigo login do usuario a ser adicionado.
     * @throws JackutException se o usuario nao existir, se a amizade ja estiver
     * efetivada, se ja existir convite pendente ou se o usuario tentar adicionar a si mesmo.
     */
    public void adicionarAmigo(String id, String amigo) {
        Usuario usuario = buscarUsuarioDaSessao(id);
        Usuario usuarioAmigo = buscarUsuario(amigo);
        String login = usuario.getLogin();

        if (login.equals(amigo)) {
            throw new JackutException("Usu\u00e1rio n\u00e3o pode adicionar a si mesmo como amigo.");
        }

        if (usuario.ehAmigo(amigo)) {
            throw new JackutException("Usu\u00e1rio j\u00e1 est\u00e1 adicionado como amigo.");
        }

        if (usuario.possuiConviteEnviadoPara(amigo)) {
            throw new JackutException("Usu\u00e1rio j\u00e1 est\u00e1 adicionado como amigo, esperando aceita\u00e7\u00e3o do convite.");
        }

        if (usuarioAmigo.possuiConviteEnviadoPara(login)) {
            usuario.adicionarAmigoEfetivado(amigo);
            usuarioAmigo.adicionarAmigoEfetivado(login);
            usuarioAmigo.removerConviteEnviado(login);
        } else {
            usuario.adicionarConviteEnviado(amigo);
        }
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
        Usuario usuario = buscarUsuario(login);
        buscarUsuario(amigo);
        return usuario.ehAmigo(amigo);
    }

    /**
     * Retorna a lista de amigos de um usuario no formato esperado pelos testes.
     *
     * @param login login do usuario.
     * @return lista de amigos em formato textual.
     * @throws JackutException se o usuario nao estiver cadastrado.
     */
    public String getAmigos(String login) {
        Usuario usuario = buscarUsuario(login);
        return formatarLista(usuario.getAmigos());
    }

    /**
     * Envia um recado para outro usuario cadastrado.
     *
     * @param id identificador da sessao do remetente.
     * @param destinatario login do destinatario.
     * @param recado texto do recado.
     * @throws JackutException se a sessao for invalida, se o destinatario nao existir
     * ou se o usuario tentar enviar recado para si mesmo.
     */
    public void enviarRecado(String id, String destinatario, String recado) {
        Usuario remetente = buscarUsuarioDaSessao(id);
        Usuario usuarioDestinatario = buscarUsuario(destinatario);

        if (remetente.getLogin().equals(destinatario)) {
            throw new JackutException("Usu\u00e1rio n\u00e3o pode enviar recado para si mesmo.");
        }

        usuarioDestinatario.receberRecado(new Recado(remetente.getLogin(), recado));
    }

    /**
     * Le o primeiro recado pendente do usuario associado a sessao.
     *
     * @param id identificador da sessao.
     * @return texto do primeiro recado pendente.
     * @throws JackutException se a sessao for invalida ou se nao houver recados.
     */
    public String lerRecado(String id) {
        Usuario usuario = buscarUsuarioDaSessao(id);

        if (!usuario.possuiRecados()) {
            throw new JackutException("N\u00e3o h\u00e1 recados.");
        }

        return usuario.removerPrimeiroRecado().getTexto();
    }

    /**
     * Salva os dados do sistema em arquivo.
     */
    public void encerrarSistema() {
        persistidor.salvar(usuarios);
    }

    /**
     * Valida o login informado no cadastro.
     *
     * @param login login a ser validado.
     * @throws JackutException se o login for nulo ou vazio.
     */
    private void validarLoginParaCadastro(String login) {
        if (login == null || login.trim().length() == 0) {
            throw new JackutException("Login inv\u00e1lido.");
        }
    }

    /**
     * Valida a senha informada no cadastro.
     *
     * @param senha senha a ser validada.
     * @throws JackutException se a senha for nula ou vazia.
     */
    private void validarSenhaParaCadastro(String senha) {
        if (senha == null || senha.length() == 0) {
            throw new JackutException("Senha inv\u00e1lida.");
        }
    }

    /**
     * Busca um usuario cadastrado pelo login.
     *
     * @param login login do usuario.
     * @return usuario encontrado.
     * @throws JackutException se o usuario nao estiver cadastrado.
     */
    private Usuario buscarUsuario(String login) {
        Usuario usuario = usuarios.get(login);

        if (usuario == null) {
            throw new JackutException("Usu\u00e1rio n\u00e3o cadastrado.");
        }

        return usuario;
    }

    /**
     * Busca o usuario associado a uma sessao.
     *
     * @param id identificador da sessao.
     * @return usuario associado a sessao.
     * @throws JackutException se a sessao nao existir ou nao estiver associada
     * a um usuario cadastrado.
     */
    private Usuario buscarUsuarioDaSessao(String id) {
        Sessao sessao = sessoes.get(id);

        if (sessao == null) {
            throw new JackutException("Usu\u00e1rio n\u00e3o cadastrado.");
        }

        return buscarUsuario(sessao.getLoginUsuario());
    }

    /**
     * Formata uma lista de strings no padrao esperado pelos testes.
     *
     * @param itens itens a serem formatados.
     * @return representacao textual da lista.
     */
    private String formatarLista(List<String> itens) {
        StringBuilder resultado = new StringBuilder();
        resultado.append("{");

        for (int i = 0; i < itens.size(); i++) {
            resultado.append(itens.get(i));

            if (i < itens.size() - 1) {
                resultado.append(",");
            }
        }

        resultado.append("}");
        return resultado.toString();
    }
}