package br.ufal.ic.p2.jackut.contratos;

import br.ufal.ic.p2.jackut.dados.DadosJackut;

/**
 * Interface responsavel por definir o contrato de persistencia dos dados do sistema.
 */
public interface Persistidor {

    /**
     * Salva os dados do sistema.
     *
     * @param dados dados que serao salvos.
     */
    void salvar(DadosJackut dados);

    /**
     * Carrega os dados salvos anteriormente.
     *
     * @return dados carregados.
     */
    DadosJackut carregar();

    /**
     * Remove os dados persistidos.
     */
    void limpar();
}
