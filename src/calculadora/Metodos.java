package calculadora;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Metodos {

    static String sql;

    static Connection conexao = null;
    static PreparedStatement pstmt = null;
    static Scanner sca = new Scanner(System.in);
    static Scanner scb = new Scanner(System.in);
    static DecimalFormat df = new DecimalFormat("0.#");

    public static void print(String texto) {
        System.out.println(texto);
    }

    public static void conectarBanco() {
        try { // inicia a conexão com o banco
              // Carrega a classe do driver JDBC
            Class.forName("oracle.jdbc.OracleDriver");
            // Conecta ao banco de dados
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//localhost:1521/XEPDB1", "gabriel", "123");
        } catch (ClassNotFoundException e) {
            print("Não foi possível carregar o driver JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            print("Não foi possível conectar ao banco de dados.");
            e.printStackTrace();
        }
    }

    public static void limparBanco() {
        try { // apaga o registro de cálculos com data inferior a 12 horas atrás
            sql = "DELETE FROM UMA_LINHA WHERE DATA_HORA < (SYSDATE - INTERVAL '12' HOUR)";
            pstmt = conexao.prepareStatement(sql);
            pstmt.executeUpdate();
            sql = "DELETE FROM PASSO_A_PASSO WHERE DATA_HORA < (SYSDATE - INTERVAL '12' HOUR)";
            pstmt = conexao.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            print("Erro ao limpar os registros.");
            e.printStackTrace();
        }
    }

    public static void menuInicial(boolean continuaMenuInicial, int menuInicial, boolean passoAPasso, boolean umaLinha,
            boolean registroNoHistorico, boolean continuaCalculadora) {

        continuaMenuInicial = true;
        while (continuaMenuInicial) {
            exibirMenuInicial();
            try {
                menuInicial = sca.nextInt();
            } catch (InputMismatchException erro) {
                print("");
                print("O valor deve ser um número!");
                sca.nextLine();
                continue;
            }
            if (menuInicial == 1) {
                passoAPasso = true;
                continuaMenuInicial = false;
            } else if (menuInicial == 2) {
                umaLinha = true;
                continuaMenuInicial = false;
            } else if (menuInicial == 3) {
                Metodos.obterHistorico(registroNoHistorico);
                continue;
            } else if (menuInicial == 4) {
                continuaCalculadora = false;
                continuaMenuInicial = false;
            } else {
                print("");
                print("Opção inválida.");
                continue;
            }
        }
        Calculadora.continuaMenuInicial = continuaMenuInicial;
        Calculadora.menuInicial = menuInicial;
        Calculadora.passoAPasso = passoAPasso;
        Calculadora.umaLinha = umaLinha;
        Calculadora.registroNoHistorico = registroNoHistorico;
        Calculadora.continuaCalculadora = continuaCalculadora;
    }

    public static void obterHistorico(boolean registroNoHistorico) {
        sql = "COMMIT";
        try {
            pstmt = conexao.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            print("Erro ao executar o commit.");
            e.printStackTrace();
        }
        sql = "SELECT A.NUM1 || ' ' || B.DS_OPERACAO || ' ' || A.NUM2 CALCULO, A.RESULTADO RESULTADO, TO_CHAR(A.DATA_HORA, 'DD/MM/YYYY HH24:MI:SS') DATA_HORA FROM PASSO_A_PASSO A LEFT JOIN OPERACOES B ON A.OPERACAO = B.ID_OPERACAO UNION ALL SELECT C.CALCULO CALCULO, C.RESULTADO RESULTADO, TO_CHAR(C.DATA_HORA, 'DD/MM/YYYY HH24:MI:SS') DATA_HORA FROM UMA_LINHA C ORDER BY 3";
        try {
            pstmt = conexao.prepareStatement(sql);
            ResultSet historico = pstmt.executeQuery();
            print("");
            print("Cálculo | Resultado | Data e hora do registro");
            while (historico.next()) {
                registroNoHistorico = true;
                String calculo = historico.getString("CALCULO");
                String resultado2 = historico.getString("RESULTADO");
                String data_hora = historico.getString("DATA_HORA");
                System.out.print(calculo + " | ");
                System.out.print("= " + resultado2 + " | ");
                System.out.print(data_hora);
                print("");
            }
            if (registroNoHistorico == false) {
                print("Não há cálculos no histórico.");
            }
        } catch (SQLException e) {
            print("Erro ao obter o histórico.");
            e.printStackTrace();
        }
        Calculadora.registroNoHistorico = registroNoHistorico;
    }

    public static void inserirResultados(double n1, int opcao, double n2, double resultado) {
        try { // insere os resultados da função passo a passo
            String sql = "INSERT INTO PASSO_A_PASSO (ID, NUM1, OPERACAO, NUM2, RESULTADO, DATA_HORA) VALUES (idcalculo.nextval, ?, ?, ?, ?, SYSDATE)";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setDouble(1, n1);
            pstmt.setInt(2, opcao);
            pstmt.setDouble(3, n2);
            pstmt.setDouble(4, resultado);
            pstmt.executeUpdate();
            sql = "COMMIT";
            pstmt = conexao.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            print("Erro ao inserir os resultados da função passo a passo.");
            e.printStackTrace();
        }
    }

    public static void inserirResultados2(String calculo, double resultado) {
        try { // insere os resultados da função uma linha
            String sql = "INSERT INTO UMA_LINHA (ID, CALCULO, RESULTADO, DATA_HORA) VALUES (idcalculo.nextval, ?, ?, SYSDATE)";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, calculo);
            pstmt.setDouble(2, resultado);
            pstmt.executeUpdate();
            sql = "COMMIT";
            pstmt = conexao.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            print("Erro ao inserir os resultados da função uma linha.");
            e.printStackTrace();
        }
    }

    public static void inserirResultadosImparPar(double n1, int opcao, String result) {
        try { // insere os resultados da função passo a passo
            String sql = "INSERT INTO PASSO_A_PASSO (ID, NUM1, OPERACAO, RESULTADO, DATA_HORA) VALUES (idcalculo.nextval, ?, ?, ?, SYSDATE)";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setDouble(1, n1);
            pstmt.setInt(2, opcao);
            pstmt.setString(3, result);
            pstmt.executeUpdate();
            sql = "COMMIT";
            pstmt = conexao.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            print("Erro ao inserir os resultados da função Ímpar / par.");
            e.printStackTrace();
        }
    }

    public static void inserirResultadosRaizes(double n1, int opcao, Double resultado) {
        try { // insere os resultados da função passo a passo
            String sql = "INSERT INTO PASSO_A_PASSO (ID, NUM1, OPERACAO, RESULTADO, DATA_HORA) VALUES (idcalculo.nextval, ?, ?, ?, SYSDATE)";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setDouble(1, n1);
            pstmt.setInt(2, opcao);
            pstmt.setDouble(3, resultado);
            pstmt.executeUpdate();
            sql = "COMMIT";
            pstmt = conexao.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            print("Erro ao inserir os resultados da função raízes.");
            e.printStackTrace();
        }
    }

    public static void encerrarConexaoBanco() {
        sql = "COMMIT";
        try {
            pstmt = conexao.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            print("Erro ao executar o commit.");
            e.printStackTrace();
        }
        try { // encerra a conexão com o banco e o método prepareStatement
            conexao.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Não foi possível encerrar a conexão com o Banco de dados.");
            e.printStackTrace();
        }
    }

    public static void encerrarCalculadora() {
        print("");
        print("Calculadora encerrada.");
        Calculadora.continuaCalculadora = false;
        sca.close();
        scb.close();
    }

    public static void exibirMenuOperacao() {
        print("");
        print("1 - Somar");
        print("2 - Subtrair");
        print("3 - Multiplicar");
        print("4 - Dividir");
        print("5 - Raiz quadrada");
        print("6 - Raiz cúbica");
        print("7 - Ímpar/par");
        print("8 - Voltar para o menu inicial");
        print("");
        System.out.print("Digite o número da operação desejada: ");
    }

    public static void exibirMenuInicial() {
        print("");
        print("1 - Fazer cálculo passo a passo");
        print("2 - Fazer cálculo por conta própria em uma linha");
        print("3 - Obter histórico de cálculos");
        print("4 - Encerrar calculadora");
    }

    public static void exibirMenuContinuar() {
        print("");
        print("1 - Continuar");
        print("2 - Continuar, mas com o resultado anterior");
        print("3 - Voltar para o menu inicial");
        print("4 - Encerrar calculadora");
    }

    public static void exibirMenuContinuar2() {
        print("");
        print("1 - Continuar");
        print("2 - Voltar para o menu inicial");
        print("3 - Encerrar calculadora");
    }

    public static void exibirMenuInvalido() {
        print("");
        print("Opção inválida.");
        print("1 - Continuar");
        print("2 - Continuar, mas com o último número");
        print("3 - Encerrar calculadora");
    }

    public static void exibirMenuUnicaLinha() {
        print("");
        print("Exemplo de como o cálculo deve ser digitado: '10 + 10'; '2 * 10 + 70 / 3'; '1,5 * 10 - 4'.");
        print("Sempre com espaços entre cada número/operador, ao escrever todo o cálculo pressione enter.");
        print("Operadores aceitos: +, -, *, /.");
        print("O cálculo é realizado em ordem sequencial, sem obedecer a regra matemática de ordem das operações.");
    }

    public static void obterRaizQuadrada(double num, Double resultado, int i) {
        print("");
        if (num - (int) num == 0) {
            resultado = Math.sqrt(num);
            print("A raiz quadrada de " + df.format(num) + " é " + df.format(resultado) + ".");
        } else {
            print("Só é possível calcular a raiz quadrada de um número inteiro.");
        }
        boolean continuaRaizQuadrada = true;
        while (continuaRaizQuadrada) {
            int x;
            print("");
            print("1 - Continuar");
            print("2 - Voltar para o menu inicial");
            print("3 - Encerrar calculadora");
            try {
                x = sca.nextInt();
            } catch (InputMismatchException erro) {
                print("");
                print("O valor deve ser um número!");
                sca.nextLine();
                continue;
            }
            if (x == 1) {
                i = 1;
                continuaRaizQuadrada = false;
            } else if (x == 2) {
                i = 3;
                continuaRaizQuadrada = false;

            } else if (x == 3) {
                i = 4;
                continuaRaizQuadrada = false;

            } else {
                print("");
                print("Opção inválida.");
                continue;
            }
        }
        Calculadora.n1 = num;
        Calculadora.resultado = resultado;
        Calculadora.i = i;
    }

    public static void obterRaizCubica(double num, Double resultado, int i) {
        print("");
        if (num - (int) num == 0) {
            resultado = Math.cbrt(num);
            print("A raiz cúbica de " + df.format(num) + " é " + df.format(resultado) + ".");
        } else {
            print("Só é possível calcular a raiz cúbica de um número inteiro.");
        }
        boolean continuaRaizCubica = true;
        while (continuaRaizCubica) {
            double x;
            print("");
            print("1 - Continuar");
            print("2 - Voltar para o menu inicial");
            print("3 - Encerrar calculadora");
            try {
                x = sca.nextDouble();
            } catch (InputMismatchException erro) {
                print("");
                print("O valor deve ser um número!");
                sca.nextLine();
                continue;
            }
            if (x == 1) {
                i = 1;
                continuaRaizCubica = false;
            } else if (x == 2) {
                i = 3;
                continuaRaizCubica = false;

            } else if (x == 3) {
                i = 4;
                continuaRaizCubica = false;

            } else {
                print("");
                print("Opção inválida.");
                continue;
            }
        }
        Calculadora.n1 = num;
        Calculadora.resultado = resultado;
        Calculadora.i = i;
    }

    public static void obterImparPar(double num, String result, int i) {

        boolean continuaImparPar = true;
        double n0 = num % 2;
        print("");

        if (n0 == 1) {
            print("O número " + df.format(num) + " é ímpar.");
            result = "ímpar";
        } else if (n0 == 0) {
            print("O número " + df.format(num) + " é par.");
            result = "par";
        } else {
            print("A definição de ímpar ou par se aplica apenas a números inteiros.");
        }

        while (continuaImparPar) {
            double x;
            print("");
            print("1 - Continuar");
            print("2 - Voltar para o menu inicial");
            print("3 - Encerrar calculadora");
            try {
                x = sca.nextDouble();
            } catch (InputMismatchException erro) {
                print("");
                print("O valor deve ser um número!");
                sca.nextLine();
                continue;
            }
            if (x == 1) {
                i = 1;
                continuaImparPar = false;
            } else if (x == 2) {
                i = 3;
                continuaImparPar = false;
            } else if (x == 3) {
                i = 4;
                continuaImparPar = false;

            } else {
                print("");
                print("Opção inválida.");
                continue;
            }
        }
        Calculadora.n1 = num;
        Calculadora.result = result;
        Calculadora.i = i;

    }

    public static void obterResultado(double num1, double num2, int opcao, double resultado, int i) {
        print("");
        switch (opcao) {
            case 1:
                resultado = num1 + num2;
                print("O resultado de " + df.format(num1) + " + " + df.format(num2) + " é igual a: "
                        + df.format(resultado));
                break;
            case 2:
                resultado = num1 - num2;
                print("O resultado de " + df.format(num1) + " - " + df.format(num2) + " é igual a: "
                        + df.format(resultado));
                break;
            case 3:
                resultado = num1 * num2;
                print("O resultado de " + df.format(num1) + " * " + df.format(num2) + " é igual a: "
                        + df.format(resultado));
                break;
            case 4:
                if (num2 == 0) {
                    throw new IllegalArgumentException("Não é possível dividir por zero");
                } else {
                    resultado = num1 / num2;
                    print("O resultado de " + df.format(num1) + " / " + df.format(num2) + " é igual a: "
                            + df.format(resultado));
                }
                break;

            default:
                print("Inválido.");
                i = 4;
        }
        Calculadora.n1 = num1;
        Calculadora.n2 = num2;
        Calculadora.opcao = opcao;
        Calculadora.resultado = resultado;
        Calculadora.i = i;
    }

    public static void obterResultado2(double num1, double num2, int opcao, double resultado, int i) {
        print("");
        switch (opcao) {
            case 1:
                resultado = resultado + num2;
                print("O resultado de " + df.format(num1) + " + " + df.format(num2) + " é igual a: "
                        + df.format(resultado));
                break;
            case 2:
                resultado = resultado - num2;
                print("O resultado de " + df.format(num1) + " - " + df.format(num2) + " é igual a: "
                        + df.format(resultado));
                break;
            case 3:
                resultado = resultado * num2;
                print("O resultado de " + df.format(num1) + " * " + df.format(num2) + " é igual a: "
                        + df.format(resultado));
                break;
            case 4:
                if (num2 == 0) {
                    throw new IllegalArgumentException("Não é possível dividir por zero");
                } else {
                    resultado = resultado / num2;
                    print("O resultado de " + df.format(num1) + " / " + df.format(num2) + " é igual a: "
                            + df.format(resultado));
                }
                break;

            default:
                print("Inválido.");
                i = 4;
        }
        Calculadora.n1 = num1;
        Calculadora.n2 = num2;
        Calculadora.opcao = opcao;
        Calculadora.resultado = resultado;
        Calculadora.i = i;
    }

}
