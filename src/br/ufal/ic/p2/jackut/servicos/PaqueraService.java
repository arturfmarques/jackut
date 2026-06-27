package br.ufal.ic.p2.jackut.servicos;

import br.ufal.ic.p2.jackut.excecoes.JackutException;
import br.ufal.ic.p2.jackut.excecoes.PaqueraJaExistenteException;
import br.ufal.ic.p2.jackut.excecoes.UsuarioNaoPodeSerPaqueraDeSiMesmoException;
import br.ufal.ic.p2.jackut.modelos.Recado;
import br.ufal.ic.p2.jackut.modelos.Usuario;
import br.ufal.ic.p2.jackut.util.FormatadorLista;

/**
 * Servico responsavel pelas regras da relacao de paquera.
 */
public class PaqueraService {

    private UsuarioService usuarioService;
    private SessaoService sessaoService;
    private BloqueioRelacionamentoService bloqueioService;

    /**
     * Cria o servico de paqueras.
     *
     * @param usuarioService servico de usuarios.
     * @param sessaoService servico de sessoes.
     * @param bloqueioService servico de bloqueios por inimizade.
     */
    public PaqueraService(UsuarioService usuarioService, SessaoService sessaoService,
                          BloqueioRelacionamentoService bloqueioService) {
        this.usuarioService = usuarioService;
        this.sessaoService = sessaoService;
        this.bloqueioService = bloqueioService;
    }

    /**
     * Adiciona uma paquera ao usuario da sessao.
     *
     * @param id identificador da sessao.
     * @param paquera login da paquera.
     */
    public void adicionarPaquera(String id, String paquera) {
        Usuario usuario = sessaoService.buscarUsuarioDaSessao(id);
        Usuario usuarioPaquera = usuarioService.buscarUsuario(paquera);

        bloqueioService.validarBloqueioPorInimizade(usuario, usuarioPaquera);

        if (usuario.getLogin().equals(paquera)) {
            throw new UsuarioNaoPodeSerPaqueraDeSiMesmoException();
        }

        if (usuario.ehPaqueraDe(paquera)) {
            throw new PaqueraJaExistenteException();
        }

        usuario.adicionarPaquera(paquera);

        if (usuarioPaquera.ehPaqueraDe(usuario.getLogin())) {
            enviarRecadosDePaquera(usuario, usuarioPaquera);
        }
    }

    /**
     * Verifica se a paquera pertence ao usuario da sessao.
     *
     * @param id identificador da sessao.
     * @param paquera login da paquera.
     * @return {@code true} se for paquera; {@code false} caso contrario.
     */
    public boolean ehPaquera(String id, String paquera) {
        Usuario usuario = sessaoService.buscarUsuarioDaSessao(id);
        usuarioService.buscarUsuario(paquera);
        return usuario.ehPaqueraDe(paquera);
    }

    /**
     * Retorna as paqueras do usuario da sessao.
     *
     * @param id identificador da sessao.
     * @return paqueras formatadas.
     */
    public String getPaqueras(String id) {
        Usuario usuario = sessaoService.buscarUsuarioDaSessao(id);
        return FormatadorLista.formatar(usuario.getPaqueras());
    }

    /**
     * Envia recados automaticos quando ha paquera reciproca.
     *
     * @param usuario usuario que acabou de adicionar a paquera.
     * @param usuarioPaquera usuario que ja havia adicionado o primeiro.
     */
    private void enviarRecadosDePaquera(Usuario usuario, Usuario usuarioPaquera) {
        String nomeUsuario = usuario.getAtributo("nome");
        String nomePaquera = usuarioPaquera.getAtributo("nome");

        String recadoParaUsuario = JackutException.ajustarTexto(
                nomePaquera + " \u00e9 seu paquera - Recado do Jackut."
        );

        String recadoParaPaquera = JackutException.ajustarTexto(
                nomeUsuario + " \u00e9 seu paquera - Recado do Jackut."
        );

        usuario.receberRecado(new Recado("Jackut", recadoParaUsuario));
        usuarioPaquera.receberRecado(new Recado("Jackut", recadoParaPaquera));
    }
}
