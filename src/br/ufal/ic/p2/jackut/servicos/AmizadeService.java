package br.ufal.ic.p2.jackut.servicos;

import br.ufal.ic.p2.jackut.excecoes.AmizadeJaExistenteException;
import br.ufal.ic.p2.jackut.excecoes.ConviteAmizadePendenteException;
import br.ufal.ic.p2.jackut.excecoes.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.excecoes.UsuarioNaoPodeAdicionarASiMesmoException;
import br.ufal.ic.p2.jackut.modelos.Usuario;
import br.ufal.ic.p2.jackut.util.FormatadorLista;

/**
 * Servico responsavel pelas regras de amizade entre usuarios.
 */
public class AmizadeService {

    private UsuarioService usuarioService;
    private SessaoService sessaoService;

    /**
     * Cria o servico de amizades.
     *
     * @param usuarioService servico de usuarios.
     * @param sessaoService servico de sessoes.
     */
    public AmizadeService(UsuarioService usuarioService, SessaoService sessaoService) {
        this.usuarioService = usuarioService;
        this.sessaoService = sessaoService;
    }

    /**
     * Adiciona um usuario como amigo ou aceita um convite de amizade existente.
     *
     * @param id identificador da sessao do usuario solicitante.
     * @param amigo login do usuario a ser adicionado.
     * @throws UsuarioNaoCadastradoException se a sessao ou o amigo forem invalidos.
     * @throws UsuarioNaoPodeAdicionarASiMesmoException se houver tentativa de autoamizade.
     * @throws AmizadeJaExistenteException se a amizade ja existir.
     * @throws ConviteAmizadePendenteException se ja houver convite pendente.
     */
    public void adicionarAmigo(String id, String amigo) {
        Usuario usuario = sessaoService.buscarUsuarioDaSessao(id);
        Usuario usuarioAmigo = usuarioService.buscarUsuario(amigo);
        String login = usuario.getLogin();

        if (login.equals(amigo)) {
            throw new UsuarioNaoPodeAdicionarASiMesmoException();
        }

        if (usuario.ehAmigo(amigo)) {
            throw new AmizadeJaExistenteException();
        }

        if (usuario.possuiConviteEnviadoPara(amigo)) {
            throw new ConviteAmizadePendenteException();
        }

        if (usuarioAmigo.possuiConviteEnviadoPara(login)) {
            efetivarAmizade(usuario, usuarioAmigo);
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
     * @throws UsuarioNaoCadastradoException se algum usuario nao estiver cadastrado.
     */
    public boolean ehAmigo(String login, String amigo) {
        Usuario usuario = usuarioService.buscarUsuario(login);
        usuarioService.buscarUsuario(amigo);
        return usuario.ehAmigo(amigo);
    }

    /**
     * Retorna a lista de amigos de um usuario no formato esperado pelos testes.
     *
     * @param login login do usuario.
     * @return lista de amigos em formato textual.
     * @throws UsuarioNaoCadastradoException se o usuario nao estiver cadastrado.
     */
    public String getAmigos(String login) {
        Usuario usuario = usuarioService.buscarUsuario(login);
        return FormatadorLista.formatar(usuario.getAmigos());
    }

    /**
     * Efetiva a amizade entre dois usuarios.
     *
     * @param usuario usuario solicitante.
     * @param usuarioAmigo usuario que havia enviado convite anteriormente.
     */
    private void efetivarAmizade(Usuario usuario, Usuario usuarioAmigo) {
        usuario.adicionarAmigoEfetivado(usuarioAmigo.getLogin());
        usuarioAmigo.adicionarAmigoEfetivado(usuario.getLogin());
        usuarioAmigo.removerConviteEnviado(usuario.getLogin());
    }
}
