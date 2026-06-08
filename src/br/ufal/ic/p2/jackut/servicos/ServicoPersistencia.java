package br.ufal.ic.p2.jackut.servicos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import br.ufal.ic.p2.jackut.contratos.Persistidor;
import br.ufal.ic.p2.jackut.modelos.Usuario;

/**
 * Servico responsavel pela persistencia dos dados do Jackut em arquivo.
 *
 * <p>A classe implementa o contrato {@link Persistidor}, permitindo salvar,
 * carregar e limpar os usuarios cadastrados no sistema.</p>
 */
public class ServicoPersistencia implements Persistidor {

    private static final String NOME_ARQUIVO = "dados-jackut.ser";

    /**
     * Salva o mapa de usuarios em arquivo.
     *
     * @param usuarios mapa de usuarios indexados pelo login.
     * @throws RuntimeException se ocorrer erro durante a gravacao do arquivo.
     */
    public void salvar(Map<String, Usuario> usuarios) {
        ObjectOutputStream saida = null;

        try {
            saida = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO));
            saida.writeObject(usuarios);
        } catch (IOException erro) {
            throw new RuntimeException("Erro ao salvar os dados do sistema.", erro);
        } finally {
            if (saida != null) {
                try {
                    saida.close();
                } catch (IOException erro) {
                    throw new RuntimeException("Erro ao fechar o arquivo de dados.", erro);
                }
            }
        }
    }

    /**
     * Carrega o mapa de usuarios salvo anteriormente.
     *
     * @return mapa de usuarios carregado ou mapa vazio caso nao exista arquivo valido.
     * @throws RuntimeException se ocorrer erro ao fechar o arquivo de dados.
     */
    public Map<String, Usuario> carregar() {
        File arquivo = new File(NOME_ARQUIVO);

        if (!arquivo.exists()) {
            return new LinkedHashMap<String, Usuario>();
        }

        ObjectInputStream entrada = null;

        try {
            entrada = new ObjectInputStream(new FileInputStream(arquivo));
            Object objeto = entrada.readObject();
            return converterParaMapa(objeto);
        } catch (IOException erro) {
            return new LinkedHashMap<String, Usuario>();
        } catch (ClassNotFoundException erro) {
            return new LinkedHashMap<String, Usuario>();
        } finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException erro) {
                    throw new RuntimeException("Erro ao fechar o arquivo de dados.", erro);
                }
            }
        }
    }

    /**
     * Remove o arquivo de dados persistidos.
     */
    public void limpar() {
        File arquivo = new File(NOME_ARQUIVO);

        if (arquivo.exists()) {
            arquivo.delete();
        }
    }

    /**
     * Converte um objeto carregado do arquivo para um mapa de usuarios valido.
     *
     * @param objeto objeto carregado do arquivo.
     * @return mapa de usuarios extraido do objeto.
     */
    private Map<String, Usuario> converterParaMapa(Object objeto) {
        Map<String, Usuario> usuarios = new LinkedHashMap<String, Usuario>();

        if (objeto instanceof Map<?, ?>) {
            Map<?, ?> mapa = (Map<?, ?>) objeto;

            for (Object chave : mapa.keySet()) {
                Object valor = mapa.get(chave);

                if (chave instanceof String && valor instanceof Usuario) {
                    usuarios.put((String) chave, (Usuario) valor);
                }
            }
        }

        return usuarios;
    }
}