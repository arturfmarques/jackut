package br.ufal.ic.p2.jackut.util;

import java.util.List;

/**
 * Classe utilitaria responsavel por formatar listas no padrao esperado pelos testes.
 */
public final class FormatadorLista {

    /**
     * Construtor privado para impedir instanciacao.
     */
    private FormatadorLista() {
    }

    /**
     * Formata uma lista de textos no formato exigido pelo EasyAccept.
     *
     * @param itens itens que serao formatados.
     * @return representacao textual da lista.
     */
    public static String formatar(List<String> itens) {
        StringBuilder resultado = new StringBuilder();
        resultado.append("{");

        for (int i = 0; i < itens.size(); i++) {
            resultado.append(itens.get(i));

            if (i < itens.size() - 1) {
                resultado.append(",");
            }
        }

        resultado.append("}");
        return resultado.toString();
    }
}
