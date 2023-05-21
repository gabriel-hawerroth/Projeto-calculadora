package calculadora;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Sequencial {

    static Scanner sca = new Scanner(System.in);
    static Scanner scb = new Scanner(System.in);
    static boolean continua = true;

    public static void print(String texto) {
        System.out.println(texto);
    }

    public static void sequencial(Double resultado, boolean umaLinha, boolean continuaCalculadora, int i,
            boolean passoAPasso, int opcao, double n1, double n2, double n3, double n4, String result) {

        Metodos.df.setMaximumFractionDigits(5);

        while (continua) {
            if (i == 3) {
                continua = false;
                break;
            } else if (i == 4) {
                continua = false;
                continuaCalculadora = false;
                break;
            }
            if (opcao == 5 || opcao == 6) {
                Metodos.inserirResultadosRaizes(n1, opcao, resultado);
            } else if (opcao == 7) {
                Metodos.inserirResultadosImparPar(n1, opcao, result);
            }

            print("");
            print("Digite o primeiro valor:");

            try {
                n1 = sca.nextDouble();
                Metodos.exibirMenuOperacao();
                opcao = sca.nextInt();
            } catch (InputMismatchException erro) {
                print("");
                print("O valor deve ser um número! Reiniciando.");
                sca.nextLine();
                continue;
            }

            if (opcao == 5) {
                Metodos.obterRaizQuadrada(n1, resultado, i);
                continue;
            } else if (opcao == 6) {
                Metodos.obterRaizCubica(n1, resultado, i);
                continue;
            } else if (opcao == 7) {
                Metodos.obterImparPar(n1, result, i);
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

            Metodos.obterResultado(n1, n2, opcao, resultado, i);
            Metodos.inserirResultados(n1, opcao, n2, resultado);

            Metodos.exibirMenuContinuar();
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
                    Metodos.exibirMenuOperacao();
                    opcao = sca.nextInt();
                } catch (InputMismatchException erro) {
                    print("");
                    print("O valor deve ser um número! Reiniciando cálculo.");
                    sca.nextLine();
                    continue;
                }
                if (opcao == 5) {
                    Metodos.obterRaizQuadrada(n3, resultado, i);
                    continue;
                } else if (opcao == 6) {
                    Metodos.obterRaizCubica(n3, resultado, i);
                    continue;
                } else if (opcao == 7) {
                    Metodos.obterImparPar(n3, result, i);
                    continue;
                } else if (opcao == 8) {
                    passoAPasso = false;
                    Calculadora.continuaMenuInicial = true;
                    break;
                } else if (opcao <= 0 || opcao >= 9) {
                    print("");
                    print("Opção inválida, reiniciando passo a passo.");
                    continue;
                }

                if (opcao == 5 || opcao == 6) {
                    Metodos.inserirResultadosRaizes(n1, opcao, resultado);
                } else if (opcao == 7) {
                    Metodos.inserirResultadosImparPar(n1, opcao, result);
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

                Metodos.obterResultado2(n3, n4, opcao, resultado, i);
                Metodos.inserirResultados(n3, opcao, n4, resultado);

                Metodos.exibirMenuContinuar();
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
                continua = false;
                break;
            } else if (i == 4) {
                continua = false;
                continuaCalculadora = false;
                break;
            }

            while (i != 1 && i != 2 && i != 3 && i != 4) {
                Metodos.exibirMenuInvalido();
                try {
                    i = sca.nextInt();
                } catch (InputMismatchException erro) {
                    print("");
                    print("O valor deve ser um número! Reiniciando calculadora.");
                    sca.nextLine();
                    break;
                }

                if (i == 3) {
                    continua = false;
                    continuaCalculadora = false;
                }
            }
        }

        Calculadora.resultado = resultado;
        Calculadora.umaLinha = umaLinha;
        Calculadora.continuaCalculadora = continuaCalculadora;
        Calculadora.i = i;
        Calculadora.passoAPasso = passoAPasso;
        Calculadora.opcao = opcao;
        Calculadora.n1 = n1;
        Calculadora.n2 = n2;
        Calculadora.n3 = n3;
        Calculadora.n4 = n4;
        Calculadora.result = result;

    }
}
