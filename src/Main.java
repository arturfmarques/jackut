import easyaccept.EasyAccept;

import java.io.File;

/**
 * Classe principal responsavel por executar os testes de aceitacao do projeto Jackut.
 *
 * <p>A classe utiliza a biblioteca EasyAccept para executar os arquivos de teste
 * disponibilizados para o Milestone 01.</p>
 *
 * <p>A localizacao da pasta de testes e feita de forma flexivel para evitar
 * falhas quando o projeto e executado a partir de diretorios diferentes.</p>
 */
public class Main {

    private static final String FACADE = "br.ufal.ic.p2.jackut.Facade";

    /**
     * Metodo principal da aplicacao.
     *
     * @param args argumentos de linha de comando nao utilizados diretamente.
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
     * Executa um arquivo de teste de aceitacao usando a fachada do sistema.
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
     * Localiza a pasta dos testes a partir do diretorio de execucao.
     *
     * @return caminho da pasta que contem os testes.
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
     * Procura recursivamente a pasta que contem o arquivo us1_1.txt.
     *
     * @param diretorio diretorio inicial da busca.
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
