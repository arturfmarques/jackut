import easyaccept.EasyAccept;

/**
 * Classe principal responsável por executar os testes de aceitação do projeto Jackut.
 *
 * <p>A classe utiliza a biblioteca EasyAccept para executar os arquivos de teste
 * disponibilizados para o Milestone 01.</p>
 */
public class Main {

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
     * @param caminhoTeste caminho relativo do arquivo de teste.
     */
    private static void executarTeste(String caminhoTeste) {
        String[] argumentos = {
                "br.ufal.ic.p2.jackut.Facade",
                caminhoTeste
        };

        EasyAccept.main(argumentos);
    }
}