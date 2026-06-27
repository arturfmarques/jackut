package br.ufal.ic.p2.jackut.dados;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import br.ufal.ic.p2.jackut.modelos.Comunidade;
import br.ufal.ic.p2.jackut.modelos.Sessao;
import br.ufal.ic.p2.jackut.modelos.Usuario;

/**
 * Representa o estado central do sistema Jackut.
 */
public class DadosJackut implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Usuario> usuarios;
    private Map<String, Sessao> sessoes;
    private Map<String, Comunidade> comunidades;
    private int proximoIdSessao;

    /**
     * Cria uma nova estrutura de dados vazia para o Jackut.
     */
    public DadosJackut() {
        this.usuarios = new LinkedHashMap<String, Usuario>();
        this.sessoes = new LinkedHashMap<String, Sessao>();
        this.comunidades = new LinkedHashMap<String, Comunidade>();
        this.proximoIdSessao = 1;
    }

    /**
     * Retorna o mapa de usuarios cadastrados.
     *
     * @return mapa de usuarios indexados pelo login.
     */
    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Retorna o mapa de sessoes abertas.
     *
     * @return mapa de sessoes indexadas pelo identificador.
     */
    public Map<String, Sessao> getSessoes() {
        return sessoes;
    }

    /**
     * Retorna o mapa de comunidades cadastradas.
     *
     * @return mapa de comunidades indexadas pelo nome.
     */
    public Map<String, Comunidade> getComunidades() {
        if (comunidades == null) {
            comunidades = new LinkedHashMap<String, Comunidade>();
        }

        return comunidades;
    }

    /**
     * Gera o proximo identificador de sessao.
     *
     * @return identificador gerado para a sessao.
     */
    public String gerarIdSessao() {
        String id = String.valueOf(proximoIdSessao);
        proximoIdSessao++;
        return id;
    }

    /**
     * Remove todas as sessoes abertas e reinicia o contador de sessoes.
     */
    public void reiniciarSessoes() {
        sessoes.clear();
        proximoIdSessao = 1;
    }

    /**
     * Remove todos os dados do sistema.
     */
    public void limpar() {
        usuarios.clear();
        reiniciarSessoes();
        getComunidades().clear();
    }
}
