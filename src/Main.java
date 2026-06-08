import easyaccept.EasyAccept;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * Classe principal responsável por executar os testes de aceitação do projeto Jackut.
 *
 * <p>A classe utiliza a biblioteca EasyAccept para executar os arquivos de teste
 * disponibilizados para o Milestone 01.</p>
 *
 * <p>Os arquivos de teste originais usam codificação ISO-8859-1. Para evitar
 * problemas de acentuação em máquinas configuradas com UTF-8, esta classe gera
 * cópias temporárias dos testes usando a codificação padrão do ambiente antes
 * de executá-los.</p>
 */
public class Main {

    private static final String CODIFICACAO_TESTES_ORIGINAIS = "ISO-8859-1";
    private static final String DIRETORIO_TESTES_GERADOS = "tests-gerados";

    /**
     * Método principal da aplicação.
     *
     * @param args argumentos de linha de comando não utilizados diretamente.
     */
    public static void main(String[] args) {
        executarTeste("tests/us1_1.txt");
        executarTeste("tests/us1_2.txt");

        executarTeste("tests/us2_1.txt");
        executarTeste("tests/us2_2.txt");

        executarTeste("tests/us3_1.txt");
        executarTeste("tests/us3_2.txt");

        executarTeste("tests/us4_1.txt");
        executarTeste("tests/us4_2.txt");
    }

    /**
     * Executa um arquivo de teste de aceitação usando a fachada do sistema.
     *
     * @param caminhoTeste caminho relativo do arquivo de teste original.
     */
    private static void executarTeste(String caminhoTeste) {
        String caminhoTestePreparado = prepararTesteParaExecucao(caminhoTeste);

        String[] argumentos = {
                "br.ufal.ic.p2.jackut.Facade",
                caminhoTestePreparado
        };

        EasyAccept.main(argumentos);
    }

    /**
     * Prepara uma cópia do arquivo de teste na codificação padrão do ambiente.
     *
     * @param caminhoTeste caminho relativo do arquivo de teste original.
     * @return caminho relativo do arquivo de teste preparado.
     */
    private static String prepararTesteParaExecucao(String caminhoTeste) {
        File diretorio = new File(DIRETORIO_TESTES_GERADOS);

        if (!diretorio.exists() && !diretorio.mkdirs()) {
            throw new RuntimeException("Não foi possível criar o diretório de testes gerados.");
        }

        File arquivoOriginal = new File(caminhoTeste);
        File arquivoGerado = new File(diretorio, arquivoOriginal.getName());

        copiarArquivoComCodificacao(arquivoOriginal, arquivoGerado);

        return arquivoGerado.getPath();
    }

    /**
     * Copia um arquivo de teste ISO-8859-1 para a codificação padrão do ambiente.
     *
     * @param origem arquivo de teste original.
     * @param destino arquivo de teste preparado.
     */
    private static void copiarArquivoComCodificacao(File origem, File destino) {
        BufferedReader leitor = null;
        BufferedWriter escritor = null;

        try {
            leitor = new BufferedReader(new InputStreamReader(
                    new FileInputStream(origem),
                    CODIFICACAO_TESTES_ORIGINAIS
            ));

            escritor = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(destino),
                    Charset.defaultCharset().name()
            ));

            String linha = leitor.readLine();

            while (linha != null) {
                escritor.write(linha);
                escritor.newLine();
                linha = leitor.readLine();
            }
        } catch (IOException erro) {
            throw new RuntimeException("Erro ao preparar arquivo de teste.", erro);
        } finally {
            fecharLeitor(leitor);
            fecharEscritor(escritor);
        }
    }

    /**
     * Fecha o leitor utilizado na preparação dos testes.
     *
     * @param leitor leitor a ser fechado.
     */
    private static void fecharLeitor(BufferedReader leitor) {
        if (leitor != null) {
            try {
                leitor.close();
            } catch (IOException erro) {
                throw new RuntimeException("Erro ao fechar o arquivo de teste original.", erro);
            }
        }
    }

    /**
     * Fecha o escritor utilizado na preparação dos testes.
     *
     * @param escritor escritor a ser fechado.
     */
    private static void fecharEscritor(BufferedWriter escritor) {
        if (escritor != null) {
            try {
                escritor.close();
            } catch (IOException erro) {
                throw new RuntimeException("Erro ao fechar o arquivo de teste gerado.", erro);
            }
        }
    }
}