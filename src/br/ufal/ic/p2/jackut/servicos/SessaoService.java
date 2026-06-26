package br.ufal.ic.p2.jackut.servicos;

import br.ufal.ic.p2.jackut.dados.DadosJackut;
import br.ufal.ic.p2.jackut.excecoes.LoginOuSenhaInvalidosException;
import br.ufal.ic.p2.jackut.excecoes.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.modelos.Sessao;
import br.ufal.ic.p2.jackut.modelos.Usuario;

/**
 * Servico responsavel pela autenticacao e controle de sessoes.
 */
public class SessaoService {

    private DadosJackut dados;
    private UsuarioService usuarioService;

    /**
     * Cria o servico de sessoes.
     *
     * @param dados dados centrais do sistema.
     * @param usuarioService servico de usuarios.
     */
    public SessaoService(DadosJackut dados, UsuarioService usuarioService) {
        this.dados = dados;
        this.usuarioService = usuarioService;
    }

    /**
     * Abre uma sessao para um usuario cadastrado.
     *
     * @param login login do usuario.
     * @param senha senha do usuario.
     * @return identificador da sessao aberta.
     * @throws LoginOuSenhaInvalidosException se login ou senha forem invalidos.
     */
    public String abrirSessao(String login, String senha) {
        Usuario usuario;

        try {
            usuario = usuarioService.buscarUsuario(login);
        } catch (UsuarioNaoCadastradoException erro) {
            throw new LoginOuSenhaInvalidosException();
        }

        if (!usuario.senhaConfere(senha)) {
            throw new LoginOuSenhaInvalidosException();
        }

        String id = dados.gerarIdSessao();
        dados.getSessoes().put(id, new Sessao(id, login));
        return id;
    }

    /**
     * Busca o usuario associado a uma sessao.
     *
     * @param id identificador da sessao.
     * @return usuario associado a sessao.
     * @throws UsuarioNaoCadastradoException se a sessao for invalida.
     */
    public Usuario buscarUsuarioDaSessao(String id) {
        Sessao sessao = dados.getSessoes().get(id);

        if (sessao == null) {
            throw new UsuarioNaoCadastradoException();
        }

        return usuarioService.buscarUsuario(sessao.getLoginUsuario());
    }
}
