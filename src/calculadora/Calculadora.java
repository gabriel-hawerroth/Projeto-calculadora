package calculadora;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Calculadora {

    static boolean continuaCalculadora = true, registroNoHistorico = false;
    static int i, opcao, menuInicial;
    static double resultado = 0, n1, n2, n3, n4;
    static String result, sql;

    static DecimalFormat df = new DecimalFormat("0.#");
    static Scanner sca = new Scanner(System.in);
    static Scanner scb = new Scanner(System.in);

    static Connection conexao = null;
    static PreparedStatement pstmt = null;

    public static void main(String[] args) {

        conectarBanco();
        limparBanco();

        while (continuaCalculadora) { // começo calculadora

            df.setMaximumFractionDigits(5);
            boolean continuaMenuInicial = true, umaLinha = false, passoAPasso = false;

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
                    obterHistorico();
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

            while (umaLinha) { // inicia calculadora na função uma linha

                int i = 1;
                exibirMenuUnicaLinha();

                while (i == 1) {
                    String calculo = "";
                    int numIndex = 0, opIndex = 0;
                    boolean invalido = false;
                    print("");
                    print("Digite o cálculo:");
                    calculo = scb.nextLine();

                    String[] partes = calculo.split(" ");
                    Double[] numeros = new Double[partes.length];
                    String[] operadores = new String[partes.length];

                    for (String parte : partes) {
                        try {
                            Double num = Double.parseDouble(parte);
                            numeros[numIndex++] = num;
                        } catch (NumberFormatException e) {
                            operadores[opIndex++] = parte;
                        }
                    }

                    if (numIndex < 2) {
                        print("");
                        print("Cálculo inválido.");
                        continue;
                    }

                    boolean primeiroLoop = true;
                    for (int x = 0; x < opIndex; x++) {
                        if (!operadores[x].equals("+") && (!operadores[x].equals("-"))
                                && (!operadores[x].equals("*")) && (!operadores[x].equals("/"))) {
                            print("");
                            print("Você digitou algo inválido!");
                            invalido = true;
                            break;
                        } else if (invalido == false) {
                            if (primeiroLoop) {
                                resultado = numeros[0];
                                primeiroLoop = false;
                            }
                            switch (operadores[x]) {
                                case "+":
                                    resultado = resultado + numeros[x + 1];
                                    break;
                                case "-":
                                    resultado = resultado - numeros[x + 1];
                                    break;
                                case "*":
                                    resultado = resultado * numeros[x + 1];
                                    break;
                                case "/":
                                    resultado = resultado / numeros[x + 1];
                                    break;
                            }
                        }
                    }
                    if (invalido == true) {
                        continue;
                    } else if (invalido == false) {
                        print("");
                        print("O resultado é: " + df.format(resultado));
                        insereResultados2(calculo, resultado);
                    }

                    exibirMenuContinuar2();
                    try {
                        i = sca.nextInt();
                    } catch (InputMismatchException erro) {
                        print("");
                        print("O valor deve ser um número! Reiniciando.");
                        sca.nextLine();
                        break;
                    }

                    if (i == 2) {
                        umaLinha = false;
                        break;
                    } else if (i == 3) {
                        umaLinha = false;
                        continuaCalculadora = false;
                        break;
                    }

                    while (i != 1 && i != 2 && i != 3) {
                        exibirMenuInvalido();
                        try {
                            i = sca.nextInt();
                        } catch (InputMismatchException erro) {
                            print("");
                            print("O valor deve ser um número! Reiniciando calculadora.");
                            sca.nextLine();
                            break;
                        }

                        if (i == 3) {
                            umaLinha = false;
                            continuaCalculadora = false;
                        }
                    }
                }

            }

            while (passoAPasso) { // inicia calculadora na função passo a passo

                if (i == 3) {
                    passoAPasso = false;
                    break;
                } else if (i == 4) {
                    passoAPasso = false;
                    continuaCalculadora = false;
                    break;
                }
                if (opcao == 5 || opcao == 6) {
                    insereResultadosRaizes(n1, opcao, resultado);
                } else if (opcao == 7) {
                    insereResultadosImparPar(n1, opcao, result);
                }

                print("");
                print("Digite o primeiro valor:");

                try {
                    n1 = sca.nextDouble();
                    exibirMenuOperacao();
                    opcao = sca.nextInt();
                } catch (InputMismatchException erro) {
                    print("");
                    print("O valor deve ser um número! Reiniciando.");
                    sca.nextLine();
                    continue;
                }

                if (opcao == 5) {
                    obterRaizQuadrada(n1);
                    continue;
                } else if (opcao == 6) {
                    obterRaizCubica(n1);
                    continue;
                } else if (opcao == 7) {
                    obterImparPar(n1);
                    continue;
                } else if (opcao == 8) {
                    i = 3;
                    break;
                } else if (opcao <= 0 || opcao >= 9) {
                    print("");
                    print("Opção inválida, reiniciando passo a passo.");
                    print("");
                    print("Opção inválida, reiniciando.");
                    continue;
                }

                print("");
                print("Digite o segundo valor:");
                try {
                    n2 = sca.nextDouble();
                } catch (InputMismatchException erro) {
                    print("");
                    print("O valor deve ser um número! Reiniciando passo a passo.");
                    sca.nextLine();
                    continue;
                }

                obterResultado(n1, n2);
                insereResultados(n1, opcao, n2, resultado);

                exibirMenuContinuar();
                try {
                    i = sca.nextInt();
                } catch (InputMismatchException erro) {
                    print("");
                    print("O valor deve ser um número! Reiniciando calculadora.");
                    sca.nextLine();
                    break;
                }

                while (i == 2) { // enquanto quiser continuar com o último valor

                    n3 = resultado;

                    try {
                        exibirMenuOperacao();
                        opcao = sca.nextInt();
                    } catch (InputMismatchException erro) {
                        print("");
                        print("O valor deve ser um número! Reiniciando cálculo.");
                        sca.nextLine();
                        continue;
                    }
                    if (opcao == 5) {
                        obterRaizQuadrada(n3);
                        continue;
                    } else if (opcao == 6) {
                        obterRaizCubica(n3);
                        continue;
                    } else if (opcao == 7) {
                        obterImparPar(n3);
                        continue;
                    } else if (opcao == 8) {
                        passoAPasso = false;
                        break;
                    } else if (opcao <= 0 || opcao >= 9) {
                        print("");
                        print("Opção inválida, reiniciando passo a passo.");
                        continue;
                    }

                    if (opcao == 5 || opcao == 6) {
                        insereResultadosRaizes(n1, opcao, resultado);
                    } else if (opcao == 7) {
                        insereResultadosImparPar(n1, opcao, result);
                    }

                    print("");
                    print("Informe o valor:");
                    try {
                        n4 = sca.nextDouble();
                    } catch (InputMismatchException erro) {
                        print("");
                        print("O valor deve ser um número! Reiniciando cálculo.");
                        sca.nextLine();
                        continue;
                    }

                    obterResultado2(n3, n4);
                    insereResultados(n3, opcao, n4, resultado);

                    exibirMenuContinuar();
                    try {
                        i = sca.nextInt();
                    } catch (InputMismatchException erro) {
                        print("");
                        print("O valor deve ser um número! Reiniciando calculadora.");
                        sca.nextLine();
                        break;
                    }
                    continue;
                }

                if (i == 1) {
                    continue;
                } else if (i == 3) {
                    passoAPasso = false;
                    break;
                } else if (i == 4) {
                    passoAPasso = false;
                    continuaCalculadora = false;
                    break;
                }

                while (i != 1 && i != 2 && i != 3 && i != 4) {
                    exibirMenuInvalido();
                    try {
                        i = sca.nextInt();
                    } catch (InputMismatchException erro) {
                        print("");
                        print("O valor deve ser um número! Reiniciando calculadora.");
                        sca.nextLine();
                        break;
                    }

                    if (i == 3) {
                        passoAPasso = false;
                        continuaCalculadora = false;
                    }
                }

            }
        }

        print("");
        print("Calculadora encerrada.");
        continuaCalculadora = false;
        sca.close();
        scb.close();
        encerrarConexao();

    }

    //
    //
    //
    //
    // métodos

    private static void print(String texto) {
        System.out.println(texto);
    }

    private static void conectarBanco() {
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

    private static void limparBanco() {
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

    private static void obterHistorico() {
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
    }

    private static void insereResultados(double a, int b, double c, double d) {
        try { // insere os resultados da função passo a passo
            String sql = "INSERT INTO PASSO_A_PASSO (ID, NUM1, OPERACAO, NUM2, RESULTADO, DATA_HORA) VALUES (idcalculo.nextval, ?, ?, ?, ?, SYSDATE)";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setDouble(1, a);
            pstmt.setInt(2, b);
            pstmt.setDouble(3, c);
            pstmt.setDouble(4, d);
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

    private static void insereResultados2(String a, double b) {
        try { // insere os resultados da função uma linha
            String sql = "INSERT INTO UMA_LINHA (ID, CALCULO, RESULTADO, DATA_HORA) VALUES (idcalculo.nextval, ?, ?, SYSDATE)";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, a);
            pstmt.setDouble(2, b);
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

    private static void insereResultadosImparPar(double a, int b, String c) {
        try { // insere os resultados da função passo a passo
            String sql = "INSERT INTO PASSO_A_PASSO (ID, NUM1, OPERACAO, RESULTADO, DATA_HORA) VALUES (idcalculo.nextval, ?, ?, ?, SYSDATE)";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setDouble(1, a);
            pstmt.setInt(2, b);
            pstmt.setString(3, c);
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

    private static void insereResultadosRaizes(double a, int b, Double c) {
        try { // insere os resultados da função passo a passo
            String sql = "INSERT INTO PASSO_A_PASSO (ID, NUM1, OPERACAO, RESULTADO, DATA_HORA) VALUES (idcalculo.nextval, ?, ?, ?, SYSDATE)";
            pstmt = conexao.prepareStatement(sql);
            pstmt.setDouble(1, a);
            pstmt.setInt(2, b);
            pstmt.setDouble(3, c);
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

    private static void encerrarConexao() {
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

    private static void exibirMenuOperacao() {
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

    private static void exibirMenuInicial() {
        print("");
        print("Bem vindo!");
        print("1 - Fazer cálculo passo a passo");
        print("2 - Fazer cálculo por conta própria em uma linha");
        print("3 - Obter histórico de cálculos");
        print("4 - Encerrar calculadora");
    }

    private static void exibirMenuContinuar() {
        print("");
        print("1 - Continuar");
        print("2 - Continuar, mas com o resultado anterior");
        print("3 - Voltar para o menu inicial");
        print("4 - Encerrar calculadora");
    }

    private static void exibirMenuContinuar2() {
        print("");
        print("1 - Continuar");
        print("2 - Voltar para o menu inicial");
        print("3 - Encerrar calculadora");
    }

    private static void exibirMenuInvalido() {
        print("");
        print("Opção inválida.");
        print("1 - Continuar");
        print("2 - Continuar, mas com o último número");
        print("3 - Encerrar calculadora");
    }

    private static void exibirMenuUnicaLinha() {
        print("");
        print("Exemplo de como o cálculo deve ser digitado: '10 + 10'; '2 * 10 + 70 / 3'; '1,5 * 10 - 4'.");
        print("Sempre com espaços entre cada número/operador, ao escrever todo o cálculo pressione enter.");
        print("Operadores aceitos: +, -, *, /.");
        print("O cálculo é realizado em ordem sequencial, sem obedecer a regra matemática de ordem das operações.");
    }

    private static void obterRaizQuadrada(double num) {
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
    }

    private static void obterRaizCubica(double num) {
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
    }

    private static void obterImparPar(double num) {

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

    }

    private static void obterResultado(double num1, double num2) {
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
    }

    private static void obterResultado2(double num1, double num2) {
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
    }

}
