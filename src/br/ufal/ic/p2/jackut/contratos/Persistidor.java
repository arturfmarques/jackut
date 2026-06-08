package br.ufal.ic.p2.jackut.contratos;

import java.util.Map;

import br.ufal.ic.p2.jackut.modelos.Usuario;

/**
 * Interface responsavel por definir o contrato de persistencia dos dados do sistema.
 *
 * <p>Ela permite que a logica de negocio dependa de uma abstracao, e nao
 * diretamente de uma implementacao concreta de persistencia.</p>
 */
public interface Persistidor {

    /**
     * Salva o mapa de usuarios do sistema.
     *
     * @param usuarios mapa de usuarios indexados pelo login.
     */
    void salvar(Map<String, Usuario> usuarios);

    /**
     * Carrega o mapa de usuarios salvo anteriormente.
     *
     * @return mapa de usuarios carregado.
     */
    Map<String, Usuario> carregar();

    /**
     * Remove os dados persistidos.
     */
    void limpar();
}