package br.ufal.ic.p2.jackut.servicos;

import br.ufal.ic.p2.jackut.excecoes.SemMensagensException;
import br.ufal.ic.p2.jackut.modelos.Comunidade;
import br.ufal.ic.p2.jackut.modelos.MensagemComunidade;
import br.ufal.ic.p2.jackut.modelos.Usuario;

/**
 * Servico responsavel pelo envio e leitura de mensagens de comunidades.
 */
public class MensagemService {

    private UsuarioService usuarioService;
    private SessaoService sessaoService;
    private ComunidadeService comunidadeService;

    /**
     * Cria o servico de mensagens.
     *
     * @param usuarioService servico de usuarios.
     * @param sessaoService servico de sessoes.
     * @param comunidadeService servico de comunidades.
     */
    public MensagemService(UsuarioService usuarioService, SessaoService sessaoService,
                           ComunidadeService comunidadeService) {
        this.usuarioService = usuarioService;
        this.sessaoService = sessaoService;
        this.comunidadeService = comunidadeService;
    }

    /**
     * Envia uma mensagem para todos os membros de uma comunidade.
     *
     * @param id identificador da sessao do remetente.
     * @param comunidade nome da comunidade.
     * @param mensagem texto da mensagem.
     */
    public void enviarMensagem(String id, String comunidade, String mensagem) {
        Usuario remetente = sessaoService.buscarUsuarioDaSessao(id);
        Comunidade comunidadeEncontrada = comunidadeService.buscarComunidade(comunidade);
        MensagemComunidade mensagemComunidade =
                new MensagemComunidade(remetente.getLogin(), mensagem, comunidade);

        for (String loginMembro : comunidadeEncontrada.getMembros()) {
            Usuario membro = usuarioService.buscarUsuario(loginMembro);
            membro.receberMensagem(mensagemComunidade);
        }
    }

    /**
     * Le a primeira mensagem pendente do usuario da sessao.
     *
     * @param id identificador da sessao.
     * @return texto da mensagem.
     */
    public String lerMensagem(String id) {
        Usuario usuario = sessaoService.buscarUsuarioDaSessao(id);

        if (!usuario.possuiMensagens()) {
            throw new SemMensagensException();
        }

        return usuario.removerPrimeiraMensagem().getTexto();
    }
}
