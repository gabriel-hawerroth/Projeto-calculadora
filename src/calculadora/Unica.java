package calculadora;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Unica {

    static Scanner sca = new Scanner(System.in);
    static Scanner scb = new Scanner(System.in);
    static boolean continua = true;

    public static void print(String texto) {
        System.out.println(texto);
    }

    public static void unica(Double resultado, boolean umaLinha, boolean continuaCalculadora) {
        Metodos.df.setMaximumFractionDigits(5);
        int i = 1;
        Metodos.exibirMenuUnicaLinha();

        while (continua) {
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
                    print("O resultado é: " + Metodos.df.format(resultado));
                    Metodos.inserirResultados2(calculo, resultado);
                }

                Metodos.exibirMenuContinuar2();
                try {
                    i = sca.nextInt();
                } catch (InputMismatchException erro) {
                    print("");
                    print("O valor deve ser um número! Reiniciando.");
                    sca.nextLine();
                    break;
                }

                if (i == 2) {
                    continua = false;
                    break;
                } else if (i == 3) {
                    continua = false;
                    continuaCalculadora = false;
                    break;
                }

                while (i != 1 && i != 2 && i != 3) {
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
                        umaLinha = false;
                        continuaCalculadora = false;
                    }
                }
            }
        }
    }
}
