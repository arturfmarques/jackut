import easyaccept.EasyAccept;

public class Main {

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

    private static void executarTeste(String caminhoTeste) {
        String[] argumentos = {
                "br.ufal.ic.p2.jackut.Facade",
                caminhoTeste
        };

        EasyAccept.main(argumentos);
    }
}