package br.ufal.ic.p2.jackut.contratos;

import java.util.Map;

import br.ufal.ic.p2.jackut.modelos.Usuario;

/**
 * Interface responsável por definir o contrato de persistência dos dados do sistema.
 *
 * <p>Ela permite que a lógica de negócio dependa de uma abstração, e não
 * diretamente de uma implementação concreta de persistência.</p>
 */
public interface Persistidor {

    /**
     * Salva o mapa de usuários do sistema.
     *
     * @param usuarios mapa de usuários indexados pelo login.
     */
    void salvar(Map<String, Usuario> usuarios);

    /**
     * Carrega o mapa de usuários salvo anteriormente.
     *
     * @return mapa de usuários carregado.
     */
    Map<String, Usuario> carregar();

    /**
     * Remove os dados persistidos.
     */
    void limpar();
}