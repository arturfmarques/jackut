package br.ufal.ic.p2.jackut.servicos;

import br.ufal.ic.p2.jackut.excecoes.IdoloJaExistenteException;
import br.ufal.ic.p2.jackut.excecoes.UsuarioNaoPodeSerFaDeSiMesmoException;
import br.ufal.ic.p2.jackut.modelos.Usuario;
import br.ufal.ic.p2.jackut.util.FormatadorLista;

/**
 * Servico responsavel pelas regras da relacao fa e idolo.
 */
public class IdoloService {

    private UsuarioService usuarioService;
    private SessaoService sessaoService;
    private BloqueioRelacionamentoService bloqueioService;

    /**
     * Cria o servico de idolos.
     *
     * @param usuarioService servico de usuarios.
     * @param sessaoService servico de sessoes.
     * @param bloqueioService servico de bloqueios por inimizade.
     */
    public IdoloService(UsuarioService usuarioService, SessaoService sessaoService,
                        BloqueioRelacionamentoService bloqueioService) {
        this.usuarioService = usuarioService;
        this.sessaoService = sessaoService;
        this.bloqueioService = bloqueioService;
    }

    /**
     * Adiciona um idolo ao usuario da sessao.
     *
     * @param id identificador da sessao.
     * @param idolo login do idolo.
     */
    public void adicionarIdolo(String id, String idolo) {
        Usuario usuario = sessaoService.buscarUsuarioDaSessao(id);
        Usuario usuarioIdolo = usuarioService.buscarUsuario(idolo);

        bloqueioService.validarBloqueioPorInimizade(usuario, usuarioIdolo);

        if (usuario.getLogin().equals(idolo)) {
            throw new UsuarioNaoPodeSerFaDeSiMesmoException();
        }

        if (usuario.ehFaDe(idolo)) {
            throw new IdoloJaExistenteException();
        }

        usuario.adicionarIdolo(idolo);
        usuarioIdolo.adicionarFa(usuario.getLogin());
    }

    /**
     * Verifica se um usuario e fa de outro.
     *
     * @param login login do fa.
     * @param idolo login do idolo.
     * @return {@code true} se for fa; {@code false} caso contrario.
     */
    public boolean ehFa(String login, String idolo) {
        Usuario usuario = usuarioService.buscarUsuario(login);
        usuarioService.buscarUsuario(idolo);
        return usuario.ehFaDe(idolo);
    }

    /**
     * Retorna os fas de um usuario.
     *
     * @param login login do usuario.
     * @return fas formatados.
     */
    public String getFas(String login) {
        Usuario usuario = usuarioService.buscarUsuario(login);
        return FormatadorLista.formatar(usuario.getFas());
    }
}
