package br.ufal.ic.p2.jackut.excecoes;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Excecao personalizada do sistema Jackut.
 *
 * <p>E utilizada para representar erros de regra de negocio identificados
 * durante a execucao das funcionalidades do sistema.</p>
 *
 * <p>A mensagem e adaptada para a codificacao padrao da JVM para manter
 * compatibilidade com os scripts do EasyAccept, que podem ser lidos em
 * codificacoes diferentes dependendo do ambiente.</p>
 */
public class JackutException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String CODIFICACAO_TESTES = "ISO-8859-1";

    /**
     * Cria uma excecao com a mensagem informada.
     *
     * @param mensagem mensagem descritiva do erro.
     */
    public JackutException(String mensagem) {
        super(ajustarMensagem(mensagem));
    }

    /**
     * Ajusta a mensagem para a codificacao padrao do ambiente de execucao.
     *
     * @param mensagem mensagem original do contrato dos testes.
     * @return mensagem ajustada para comparacao pelo EasyAccept.
     */
    private static String ajustarMensagem(String mensagem) {
        if (mensagem == null) {
            return null;
        }

        try {
            byte[] bytesIso = mensagem.getBytes(CODIFICACAO_TESTES);
            return new String(bytesIso, Charset.defaultCharset().name());
        } catch (UnsupportedEncodingException erro) {
            return mensagem;
        }
    }
}