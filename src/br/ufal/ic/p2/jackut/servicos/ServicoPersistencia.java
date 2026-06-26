package br.ufal.ic.p2.jackut.servicos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import br.ufal.ic.p2.jackut.contratos.Persistidor;
import br.ufal.ic.p2.jackut.dados.DadosJackut;
import br.ufal.ic.p2.jackut.excecoes.ErroPersistenciaException;
import br.ufal.ic.p2.jackut.modelos.Usuario;

/**
 * Servico responsavel pela persistencia dos dados do Jackut em arquivo.
 */
public class ServicoPersistencia implements Persistidor {

    private static final String NOME_ARQUIVO = "dados-jackut.ser";

    /**
     * Salva os dados do sistema em arquivo.
     *
     * @param dados dados que serao salvos.
     * @throws ErroPersistenciaException se ocorrer falha durante a gravacao.
     */
    public void salvar(DadosJackut dados) {
        ObjectOutputStream saida = null;

        try {
            saida = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO));
            saida.writeObject(dados);
        } catch (IOException erro) {
            throw new ErroPersistenciaException("Erro ao salvar os dados do sistema.", erro);
        } finally {
            fecharSaida(saida);
        }
    }

    /**
     * Carrega os dados salvos anteriormente.
     *
     * @return dados carregados ou dados vazios caso nao exista arquivo valido.
     */
    public DadosJackut carregar() {
        File arquivo = new File(NOME_ARQUIVO);

        if (!arquivo.exists()) {
            return new DadosJackut();
        }

        ObjectInputStream entrada = null;

        try {
            entrada = new ObjectInputStream(new FileInputStream(arquivo));
            Object objeto = entrada.readObject();
            return converterObjeto(objeto);
        } catch (IOException erro) {
            return new DadosJackut();
        } catch (ClassNotFoundException erro) {
            return new DadosJackut();
        } finally {
            fecharEntrada(entrada);
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
     * Converte o objeto carregado para o formato atual de dados.
     *
     * @param objeto objeto carregado do arquivo.
     * @return dados convertidos.
     */
    private DadosJackut converterObjeto(Object objeto) {
        if (objeto instanceof DadosJackut) {
            DadosJackut dados = (DadosJackut) objeto;
            dados.reiniciarSessoes();
            return dados;
        }

        if (objeto instanceof Map<?, ?>) {
            DadosJackut dados = new DadosJackut();
            Map<?, ?> mapa = (Map<?, ?>) objeto;

            for (Object chave : mapa.keySet()) {
                Object valor = mapa.get(chave);

                if (chave instanceof String && valor instanceof Usuario) {
                    dados.getUsuarios().put((String) chave, (Usuario) valor);
                }
            }

            return dados;
        }

        return new DadosJackut();
    }

    /**
     * Fecha o fluxo de saida usado na gravacao.
     *
     * @param saida fluxo de saida.
     * @throws ErroPersistenciaException se ocorrer erro ao fechar o arquivo.
     */
    private void fecharSaida(ObjectOutputStream saida) {
        if (saida != null) {
            try {
                saida.close();
            } catch (IOException erro) {
                throw new ErroPersistenciaException("Erro ao fechar o arquivo de dados.", erro);
            }
        }
    }

    /**
     * Fecha o fluxo de entrada usado na leitura.
     *
     * @param entrada fluxo de entrada.
     * @throws ErroPersistenciaException se ocorrer erro ao fechar o arquivo.
     */
    private void fecharEntrada(ObjectInputStream entrada) {
        if (entrada != null) {
            try {
                entrada.close();
            } catch (IOException erro) {
                throw new ErroPersistenciaException("Erro ao fechar o arquivo de dados.", erro);
            }
        }
    }
}
