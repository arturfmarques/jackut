package br.ufal.ic.p2.jackut.servicos;

import br.ufal.ic.p2.jackut.excecoes.FuncaoInvalidaException;
import br.ufal.ic.p2.jackut.modelos.Usuario;

/**
 * Servico responsavel por validar bloqueios causados por inimizade.
 */
public class BloqueioRelacionamentoService {

    /**
     * Valida se uma acao esta bloqueada por inimizade.
     *
     * @param usuario usuario que deseja executar a acao.
     * @param alvo usuario alvo da acao.
     */
    public void validarBloqueioPorInimizade(Usuario usuario, Usuario alvo) {
        if (alvo.possuiInimigo(usuario.getLogin())) {
            throw new FuncaoInvalidaException(alvo.getAtributo("nome"));
        }
    }
}
