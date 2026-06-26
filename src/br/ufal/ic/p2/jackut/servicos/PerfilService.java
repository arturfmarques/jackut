package br.ufal.ic.p2.jackut.servicos;

import br.ufal.ic.p2.jackut.excecoes.AtributoNaoPreenchidoException;
import br.ufal.ic.p2.jackut.excecoes.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.modelos.Usuario;

/**
 * Servico responsavel pelas operacoes de perfil dos usuarios.
 */
public class PerfilService {

    private UsuarioService usuarioService;
    private SessaoService sessaoService;

    /**
     * Cria o servico de perfil.
     *
     * @param usuarioService servico de usuarios.
     * @param sessaoService servico de sessoes.
     */
    public PerfilService(UsuarioService usuarioService, SessaoService sessaoService) {
        this.usuarioService = usuarioService;
        this.sessaoService = sessaoService;
    }

    /**
     * Retorna um atributo do perfil de um usuario.
     *
     * @param login login do usuario.
     * @param atributo nome do atributo consultado.
     * @return valor do atributo.
     * @throws UsuarioNaoCadastradoException se o usuario nao estiver cadastrado.
     * @throws AtributoNaoPreenchidoException se o atributo nao estiver preenchido.
     */
    public String getAtributoUsuario(String login, String atributo) {
        Usuario usuario = usuarioService.buscarUsuario(login);

        if (!usuario.possuiAtributo(atributo)) {
            throw new AtributoNaoPreenchidoException();
        }

        return usuario.getAtributo(atributo);
    }

    /**
     * Edita um atributo do perfil do usuario associado a sessao.
     *
     * @param id identificador da sessao.
     * @param atributo atributo a ser criado ou alterado.
     * @param valor valor do atributo.
     * @throws UsuarioNaoCadastradoException se a sessao for invalida.
     */
    public void editarPerfil(String id, String atributo, String valor) {
        Usuario usuario = sessaoService.buscarUsuarioDaSessao(id);
        usuario.editarPerfil(atributo, valor);
    }
}
