package br.ufal.ic.p2.jackut.servicos;

import br.ufal.ic.p2.jackut.excecoes.SemRecadosException;
import br.ufal.ic.p2.jackut.excecoes.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.excecoes.UsuarioNaoPodeEnviarRecadoParaSiMesmoException;
import br.ufal.ic.p2.jackut.modelos.Recado;
import br.ufal.ic.p2.jackut.modelos.Usuario;

/**
 * Servico responsavel pelo envio e leitura de recados.
 */
public class RecadoService {

    private UsuarioService usuarioService;
    private SessaoService sessaoService;
    private BloqueioRelacionamentoService bloqueioService;

    /**
     * Cria o servico de recados.
     *
     * @param usuarioService servico de usuarios.
     * @param sessaoService servico de sessoes.
     * @param bloqueioService servico de bloqueios por inimizade.
     */
    public RecadoService(UsuarioService usuarioService, SessaoService sessaoService,
                         BloqueioRelacionamentoService bloqueioService) {
        this.usuarioService = usuarioService;
        this.sessaoService = sessaoService;
        this.bloqueioService = bloqueioService;
    }

    /**
     * Envia um recado para outro usuario cadastrado.
     *
     * @param id identificador da sessao do remetente.
     * @param destinatario login do destinatario.
     * @param recado texto do recado.
     * @throws UsuarioNaoCadastradoException se a sessao ou o destinatario forem invalidos.
     * @throws UsuarioNaoPodeEnviarRecadoParaSiMesmoException se houver tentativa de autorecado.
     */
    public void enviarRecado(String id, String destinatario, String recado) {
        Usuario remetente = sessaoService.buscarUsuarioDaSessao(id);
        Usuario usuarioDestinatario = usuarioService.buscarUsuario(destinatario);

        bloqueioService.validarBloqueioPorInimizade(remetente, usuarioDestinatario);

        if (remetente.getLogin().equals(destinatario)) {
            throw new UsuarioNaoPodeEnviarRecadoParaSiMesmoException();
        }

        usuarioDestinatario.receberRecado(new Recado(remetente.getLogin(), recado));
    }

    /**
     * Le o primeiro recado pendente do usuario associado a sessao.
     *
     * @param id identificador da sessao.
     * @return texto do primeiro recado pendente.
     * @throws UsuarioNaoCadastradoException se a sessao for invalida.
     * @throws SemRecadosException se nao houver recados.
     */
    public String lerRecado(String id) {
        Usuario usuario = sessaoService.buscarUsuarioDaSessao(id);

        if (!usuario.possuiRecados()) {
            throw new SemRecadosException();
        }

        return usuario.removerPrimeiroRecado().getTexto();
    }
}
