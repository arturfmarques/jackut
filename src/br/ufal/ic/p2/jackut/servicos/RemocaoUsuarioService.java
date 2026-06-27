package br.ufal.ic.p2.jackut.servicos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.ufal.ic.p2.jackut.dados.DadosJackut;
import br.ufal.ic.p2.jackut.excecoes.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.modelos.Comunidade;
import br.ufal.ic.p2.jackut.modelos.Sessao;
import br.ufal.ic.p2.jackut.modelos.Usuario;

/**
 * Servico responsavel pela remocao de contas do Jackut.
 */
public class RemocaoUsuarioService {

    private DadosJackut dados;
    private SessaoService sessaoService;
    private ComunidadeService comunidadeService;

    /**
     * Cria o servico de remocao de usuarios.
     *
     * @param dados dados centrais do sistema.
     * @param sessaoService servico de sessoes.
     * @param comunidadeService servico de comunidades.
     */
    public RemocaoUsuarioService(DadosJackut dados, SessaoService sessaoService,
                                 ComunidadeService comunidadeService) {
        this.dados = dados;
        this.sessaoService = sessaoService;
        this.comunidadeService = comunidadeService;
    }

    /**
     * Remove a conta associada a sessao informada.
     *
     * @param id identificador da sessao.
     * @throws UsuarioNaoCadastradoException se a sessao for invalida.
     */
    public void removerUsuario(String id) {
        Usuario usuario = sessaoService.buscarUsuarioDaSessao(id);
        String login = usuario.getLogin();

        removerComunidadesDoUsuario(login);
        removerReferenciasEmUsuarios(login);
        removerSessoesDoUsuario(login);
        dados.getUsuarios().remove(login);
    }

    /**
     * Remove comunidades do usuario ou sua participacao nelas.
     *
     * @param login login removido.
     */
    private void removerComunidadesDoUsuario(String login) {
        List<String> comunidadesDoDono = comunidadeService.getComunidadesDoDono(login);

        for (String nomeComunidade : comunidadesDoDono) {
            comunidadeService.removerComunidade(nomeComunidade);
        }

        for (Comunidade comunidade : dados.getComunidades().values()) {
            comunidade.removerMembro(login);
        }
    }

    /**
     * Remove todas as referencias ao usuario removido nos demais usuarios.
     *
     * @param login login removido.
     */
    private void removerReferenciasEmUsuarios(String login) {
        for (Usuario outroUsuario : new ArrayList<Usuario>(dados.getUsuarios().values())) {
            if (!login.equals(outroUsuario.getLogin())) {
                outroUsuario.removerReferenciasAoUsuario(login);
            }
        }
    }

    /**
     * Remove todas as sessoes abertas do usuario removido.
     *
     * @param login login removido.
     */
    private void removerSessoesDoUsuario(String login) {
        Iterator<Map.Entry<String, Sessao>> iterador = dados.getSessoes().entrySet().iterator();

        while (iterador.hasNext()) {
            Map.Entry<String, Sessao> entrada = iterador.next();

            if (login.equals(entrada.getValue().getLoginUsuario())) {
                iterador.remove();
            }
        }
    }
}
