package br.ufal.ic.p2.jackut.servicos;

import br.ufal.ic.p2.jackut.excecoes.InimigoJaExistenteException;
import br.ufal.ic.p2.jackut.excecoes.UsuarioNaoPodeSerInimigoDeSiMesmoException;
import br.ufal.ic.p2.jackut.modelos.Usuario;

/**
 * Servico responsavel pelas regras da relacao de inimizade.
 */
public class InimigoService {

    private UsuarioService usuarioService;
    private SessaoService sessaoService;

    /**
     * Cria o servico de inimigos.
     *
     * @param usuarioService servico de usuarios.
     * @param sessaoService servico de sessoes.
     */
    public InimigoService(UsuarioService usuarioService, SessaoService sessaoService) {
        this.usuarioService = usuarioService;
        this.sessaoService = sessaoService;
    }

    /**
     * Adiciona um inimigo ao usuario da sessao.
     *
     * @param id identificador da sessao.
     * @param inimigo login do inimigo.
     */
    public void adicionarInimigo(String id, String inimigo) {
        Usuario usuario = sessaoService.buscarUsuarioDaSessao(id);
        usuarioService.buscarUsuario(inimigo);

        if (usuario.getLogin().equals(inimigo)) {
            throw new UsuarioNaoPodeSerInimigoDeSiMesmoException();
        }

        if (usuario.possuiInimigo(inimigo)) {
            throw new InimigoJaExistenteException();
        }

        usuario.adicionarInimigo(inimigo);
    }
}
