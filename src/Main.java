import easyaccept.EasyAccept;

import java.io.File;

/**
 * Classe principal responsável por executar os testes de aceitação do projeto Jackut.
 *
 * <p>A classe utiliza a biblioteca EasyAccept para executar os arquivos de teste
 * disponibilizados para o Milestone 01.</p>
 *
 * <p>A localização da pasta de testes é feita de forma flexível para evitar
 * falhas quando o projeto é executado a partir de diretórios diferentes.</p>
 */
public class Main {

    private static final String FACADE = "br.ufal.ic.p2.jackut.Facade";

    /**
     * Método principal da aplicação.
     *
     * @param args argumentos de linha de comando não utilizados diretamente.
     */
    public static void main(String[] args) {
        String baseTestes = localizarPastaTestes();

        executarTeste(baseTestes + "us1_1.txt");
        executarTeste(baseTestes + "us1_2.txt");

        executarTeste(baseTestes + "us2_1.txt");
        executarTeste(baseTestes + "us2_2.txt");

        executarTeste(baseTestes + "us3_1.txt");
        executarTeste(baseTestes + "us3_2.txt");

        executarTeste(baseTestes + "us4_1.txt");
        executarTeste(baseTestes + "us4_2.txt");
    }

    /**
     * Executa um arquivo de teste de aceitação usando a fachada do sistema.
     *
     * @param caminhoTeste caminho relativo do arquivo de teste.
     */
    private static void executarTeste(String caminhoTeste) {
        String[] argumentos = {
                FACADE,
                caminhoTeste
        };

        EasyAccept.main(argumentos);
    }

    /**
     * Localiza a pasta dos testes a partir do diretório de execução.
     *
     * @return caminho da pasta que contém os testes.
     */
    private static String localizarPastaTestes() {
        File pastaTestes = procurarPastaTestes(new File("."), 0);

        if (pastaTestes == null) {
            throw new IllegalStateException(
                    "Nao foi possivel localizar a pasta de testes. Diretorio atual: "
                            + new File(".").getAbsolutePath()
            );
        }

        return pastaTestes.getPath() + File.separator;
    }

    /**
     * Procura recursivamente a pasta que contém o arquivo us1_1.txt.
     *
     * @param diretorio diretório inicial da busca.
     * @param profundidade profundidade atual da busca.
     * @return pasta dos testes, se encontrada.
     */
    private static File procurarPastaTestes(File diretorio, int profundidade) {
        if (diretorio == null || !diretorio.isDirectory() || profundidade > 4) {
            return null;
        }

        File pastaTests = new File(diretorio, "tests");
        File arquivoTeste = new File(pastaTests, "us1_1.txt");

        if (arquivoTeste.exists()) {
            return pastaTests;
        }

        File[] arquivos = diretorio.listFiles();

        if (arquivos == null) {
            return null;
        }

        for (int i = 0; i < arquivos.length; i++) {
            File arquivo = arquivos[i];

            if (arquivo.isDirectory()) {
                File resultado = procurarPastaTestes(arquivo, profundidade + 1);

                if (resultado != null) {
                    return resultado;
                }
            }
        }

        return null;
    }
}