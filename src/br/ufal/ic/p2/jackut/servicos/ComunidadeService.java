package br.ufal.ic.p2.jackut.servicos;

import java.util.ArrayList;
import java.util.List;

import br.ufal.ic.p2.jackut.dados.DadosJackut;
import br.ufal.ic.p2.jackut.excecoes.ComunidadeJaExistenteException;
import br.ufal.ic.p2.jackut.excecoes.ComunidadeNaoExisteException;
import br.ufal.ic.p2.jackut.excecoes.UsuarioJaFazParteDaComunidadeException;
import br.ufal.ic.p2.jackut.modelos.Comunidade;
import br.ufal.ic.p2.jackut.modelos.Usuario;
import br.ufal.ic.p2.jackut.util.FormatadorLista;

/**
 * Servico responsavel pelas regras de comunidades.
 */
public class ComunidadeService {

    private DadosJackut dados;
    private UsuarioService usuarioService;
    private SessaoService sessaoService;

    /**
     * Cria o servico de comunidades.
     *
     * @param dados dados centrais do sistema.
     * @param usuarioService servico de usuarios.
     * @param sessaoService servico de sessoes.
     */
    public ComunidadeService(DadosJackut dados, UsuarioService usuarioService,
                             SessaoService sessaoService) {
        this.dados = dados;
        this.usuarioService = usuarioService;
        this.sessaoService = sessaoService;
    }

    /**
     * Cria uma comunidade.
     *
     * @param sessao identificador da sessao do dono.
     * @param nome nome da comunidade.
     * @param descricao descricao da comunidade.
     */
    public void criarComunidade(String sessao, String nome, String descricao) {
        Usuario dono = sessaoService.buscarUsuarioDaSessao(sessao);

        if (dados.getComunidades().containsKey(nome)) {
            throw new ComunidadeJaExistenteException();
        }

        Comunidade comunidade = new Comunidade(nome, descricao, dono.getLogin());
        dados.getComunidades().put(nome, comunidade);
        dono.adicionarComunidade(nome);
    }

    /**
     * Retorna a descricao de uma comunidade.
     *
     * @param nome nome da comunidade.
     * @return descricao da comunidade.
     */
    public String getDescricaoComunidade(String nome) {
        return buscarComunidade(nome).getDescricao();
    }

    /**
     * Retorna o dono de uma comunidade.
     *
     * @param nome nome da comunidade.
     * @return login do dono.
     */
    public String getDonoComunidade(String nome) {
        return buscarComunidade(nome).getDono();
    }

    /**
     * Retorna os membros de uma comunidade.
     *
     * @param nome nome da comunidade.
     * @return membros formatados.
     */
    public String getMembrosComunidade(String nome) {
        return FormatadorLista.formatar(buscarComunidade(nome).getMembros());
    }

    /**
     * Adiciona o usuario da sessao a uma comunidade.
     *
     * @param sessao identificador da sessao.
     * @param nome nome da comunidade.
     */
    public void adicionarComunidade(String sessao, String nome) {
        Usuario usuario = sessaoService.buscarUsuarioDaSessao(sessao);
        Comunidade comunidade = buscarComunidade(nome);

        if (usuario.participaDaComunidade(nome)) {
            throw new UsuarioJaFazParteDaComunidadeException();
        }

        comunidade.adicionarMembro(usuario.getLogin());
        usuario.adicionarComunidade(nome);
    }

    /**
     * Retorna as comunidades de um usuario pelo login.
     *
     * @param login login do usuario.
     * @return comunidades formatadas.
     */
    public String getComunidades(String login) {
        Usuario usuario = usuarioService.buscarUsuario(login);
        return FormatadorLista.formatar(usuario.getComunidades());
    }

    /**
     * Retorna as comunidades do usuario associado a sessao.
     *
     * @param sessao identificador da sessao.
     * @return comunidades formatadas.
     */
    public String getComunidadesPorSessao(String sessao) {
        Usuario usuario = sessaoService.buscarUsuarioDaSessao(sessao);
        return FormatadorLista.formatar(usuario.getComunidades());
    }

    /**
     * Busca uma comunidade pelo nome.
     *
     * @param nome nome da comunidade.
     * @return comunidade encontrada.
     */
    public Comunidade buscarComunidade(String nome) {
        Comunidade comunidade = dados.getComunidades().get(nome);

        if (comunidade == null) {
            throw new ComunidadeNaoExisteException();
        }

        return comunidade;
    }

    /**
     * Remove uma comunidade do sistema.
     *
     * @param nome nome da comunidade.
     */
    public void removerComunidade(String nome) {
        Comunidade comunidade = buscarComunidade(nome);

        for (String membro : comunidade.getMembros()) {
            Usuario usuario = dados.getUsuarios().get(membro);

            if (usuario != null) {
                usuario.removerComunidade(nome);
            }
        }

        dados.getComunidades().remove(nome);
    }

    /**
     * Retorna os nomes das comunidades criadas por um usuario.
     *
     * @param login login do dono.
     * @return lista de nomes de comunidades.
     */
    public List<String> getComunidadesDoDono(String login) {
        List<String> resultado = new ArrayList<String>();

        for (Comunidade comunidade : dados.getComunidades().values()) {
            if (login.equals(comunidade.getDono())) {
                resultado.add(comunidade.getNome());
            }
        }

        return resultado;
    }
}
