package calculadora;

public class Calculadora {

    static boolean continuaCalculadora = true, registroNoHistorico = false, continuaMenuInicial = true,
            umaLinha = false, passoAPasso = false;
    static int i, opcao, menuInicial;
    static double resultado = 0, n1, n2, n3, n4;
    static String result;

    public static void main(String[] args) {

        Metodos.conectarBanco();
        Metodos.limparBanco();

        while (continuaCalculadora) { // começo calculadora

            // menu inicial da calculadora
            Metodos.menuInicial(continuaMenuInicial, menuInicial, passoAPasso, umaLinha, registroNoHistorico,
                    continuaCalculadora);

            if (umaLinha) {// inicia calculadora na função uma linha
                Unica.unica(resultado, umaLinha, continuaCalculadora);
            } else if (passoAPasso) { // inicia calculadora na função passo a passo
                Sequencial.sequencial(resultado, umaLinha, continuaCalculadora, i, passoAPasso, opcao, n1, n2, n3, n4,
                        result);
            } else {
                System.out.println("Erro");
            }
        }

        Metodos.encerrarConexaoBanco();
        Metodos.encerrarCalculadora(continuaCalculadora);

    }

}
