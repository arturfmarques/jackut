package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.contratos.Persistidor;
import br.ufal.ic.p2.jackut.dados.DadosJackut;
import br.ufal.ic.p2.jackut.excecoes.JackutException;
import br.ufal.ic.p2.jackut.servicos.AmizadeService;
import br.ufal.ic.p2.jackut.servicos.PerfilService;
import br.ufal.ic.p2.jackut.servicos.RecadoService;
import br.ufal.ic.p2.jackut.servicos.ServicoPersistencia;
import br.ufal.ic.p2.jackut.servicos.SessaoService;
import br.ufal.ic.p2.jackut.servicos.UsuarioService;

/**
 * Fachada do sistema Jackut.
 *
 * <p>Esta classe representa o ponto de entrada utilizado pelos testes de aceitacao
 * do EasyAccept. Ela preserva o contrato publico esperado pelos testes e delega
 * cada operacao para o servico responsavel.</p>
 */
public class Facade {

    private DadosJackut dados;
    private Persistidor persistidor;
    private UsuarioService usuarioService;
    private SessaoService sessaoService;
    private PerfilService perfilService;
    private AmizadeService amizadeService;
    private RecadoService recadoService;

    /**
     * Cria uma nova fachada e inicializa os servicos do sistema.
     */
    public Facade() {
        this.persistidor = new ServicoPersistencia();
        this.dados = persistidor.carregar();
        inicializarServicos();
    }

    /**
     * Apaga todos os dados mantidos pelo sistema.
     */
    public void zerarSistema() {
        dados.limpar();
        persistidor.limpar();
    }

    /**
     * Cria um usuario no Jackut.
     *
     * @param login login unico do usuario.
     * @param senha senha de acesso do usuario.
     * @param nome nome publico do usuario.
     * @throws JackutException se houver violacao de regra de cadastro.
     */
    public void criarUsuario(String login, String senha, String nome) {
        usuarioService.criarUsuario(login, senha, nome);
    }

    /**
     * Abre uma sessao para um usuario cadastrado.
     *
     * @param login login do usuario.
     * @param senha senha do usuario.
     * @return identificador da sessao aberta.
     * @throws JackutException se login ou senha forem invalidos.
     */
    public String abrirSessao(String login, String senha) {
        return sessaoService.abrirSessao(login, senha);
    }

    /**
     * Retorna o valor de um atributo do perfil de um usuario.
     *
     * @param login login do usuario.
     * @param atributo nome do atributo consultado.
     * @return valor do atributo informado.
     * @throws JackutException se houver violacao de regra de perfil.
     */
    public String getAtributoUsuario(String login, String atributo) {
        return perfilService.getAtributoUsuario(login, atributo);
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
        perfilService.editarPerfil(id, atributo, valor);
    }

    /**
     * Solicita a adicao de um usuario como amigo.
     *
     * @param id identificador da sessao do usuario solicitante.
     * @param amigo login do usuario a ser adicionado.
     * @throws JackutException se houver violacao de regra de amizade.
     */
    public void adicionarAmigo(String id, String amigo) {
        amizadeService.adicionarAmigo(id, amigo);
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
        return amizadeService.ehAmigo(login, amigo);
    }

    /**
     * Retorna a lista de amigos de um usuario no formato exigido pelos testes.
     *
     * @param login login do usuario.
     * @return lista de amigos codificada em uma string.
     * @throws JackutException se o usuario nao estiver cadastrado.
     */
    public String getAmigos(String login) {
        return amizadeService.getAmigos(login);
    }

    /**
     * Envia um recado para outro usuario cadastrado.
     *
     * @param id identificador da sessao do remetente.
     * @param destinatario login do usuario destinatario.
     * @param recado texto do recado.
     * @throws JackutException se houver violacao de regra de recado.
     */
    public void enviarRecado(String id, String destinatario, String recado) {
        recadoService.enviarRecado(id, destinatario, recado);
    }

    /**
     * Le o primeiro recado pendente do usuario associado a sessao.
     *
     * @param id identificador da sessao.
     * @return texto do primeiro recado pendente.
     * @throws JackutException se houver violacao de regra de recado.
     */
    public String lerRecado(String id) {
        return recadoService.lerRecado(id);
    }

    /**
     * Salva os dados do sistema em arquivo.
     */
    public void encerrarSistema() {
        persistidor.salvar(dados);
    }

    /**
     * Inicializa os servicos usados pela fachada.
     */
    private void inicializarServicos() {
        this.usuarioService = new UsuarioService(dados);
        this.sessaoService = new SessaoService(dados, usuarioService);
        this.perfilService = new PerfilService(usuarioService, sessaoService);
        this.amizadeService = new AmizadeService(usuarioService, sessaoService);
        this.recadoService = new RecadoService(usuarioService, sessaoService);
    }
}
