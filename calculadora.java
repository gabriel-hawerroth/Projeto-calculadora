package calculadora;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

public class calculadora {

	public static void main(String[] args) {

		double resultado = 0, n3, n1, n2, n4;
		int i, opcao;
		boolean continua = true;

		Scanner sc = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("0.#################################################");

		metodos met = new metodos();
		menu menu = new menu();

		while (continua) {

			System.out.println("");
			System.out.println("Digite o primeiro valor:");
			try {
				n1 = sc.nextDouble();
				menu.exibirMenu();
				opcao = sc.nextInt();
			} catch (InputMismatchException erro) {
				System.out.println("");
				System.out.println("O valor deve ser um número! reiniciando cálculo");
				sc.nextLine();
				continue;
			}

			if (opcao == 5) {

				boolean continuaImparPar = true;
				resultado = met.impar_par(n1);
				System.out.println("");

				if (resultado == 1) {
					System.out.println("O número " + df.format(n1) + " é ímpar");
				} else if (resultado == 0) {
					System.out.println("O número " + df.format(n1) + " é par");
				} else {
					System.out.println("A definição de ímpar ou par se aplica apenas a números inteiros");
				}

				while (continuaImparPar) {
					double x;
					System.out.println("");
					System.out.println("1 - Continuar");
					System.out.println("2 - Encerrar calculadora");
					try {
						x = sc.nextDouble();
					} catch (InputMismatchException erro) {
						System.out.println("");
						System.out.println("O valor deve ser um número!");
						sc.nextLine();
						continue;
					}
					if (x == 1) {
						continuaImparPar = false;
					} else if (x == 2) {
						continuaImparPar = false;
						continua = false;
					} else {
						System.out.println("");
						System.out.println("Opção inválida.");
						continue;
					}
				}
				continue;
			} else if (opcao == 6) {
				break;
			} else if (opcao <= 0 || opcao >= 7) {
				System.out.println("");
				System.out.println("Opção inválida, reiniciando cálculo");
				continue;
			}

			System.out.println("");
			System.out.println("Digite o segundo valor:");
			try {
				n2 = sc.nextDouble();
			} catch (InputMismatchException erro) {
				System.out.println("");
				System.out.println("O valor deve ser um número! reiniciando cálculo");
				sc.nextLine();
				continue;
			}

			switch (opcao) {
				case 1:
					resultado = met.somar(n1, n2);
					System.out.println("");
					System.out.println("O resultado de " + df.format(n1) + " + " + df.format(n2) + " é igual a: "
							+ df.format(resultado));
					break;
				case 2:
					resultado = met.subtrair(n1, n2);
					System.out.println("");
					System.out.println("O resultado de " + df.format(n1) + " - " + df.format(n2) + " é igual a: "
							+ df.format(resultado));
					break;
				case 3:
					resultado = met.multiplicar(n1, n2);
					System.out.println("");
					System.out.println("O resultado de " + df.format(n1) + " * " + df.format(n2) + " é igual a: "
							+ df.format(resultado));
					break;
				case 4:
					resultado = met.dividir(n1, n2);
					System.out.println("");
					System.out.println("O resultado de " + df.format(n1) + " / " + df.format(n2) + " é igual a: "
							+ df.format(resultado));
					break;
				default:
					System.out.println("");
					System.out.println("Inválido");
					break;
			}

			System.out.println("");
			System.out.println("1 - Continuar");
			System.out.println("2 - Continuar, mas com o resultado anterior");
			System.out.println("3 - Encerrar calculadora");
			try {
				i = sc.nextInt();
			} catch (InputMismatchException erro) {
				System.out.println("");
				System.out.println("O valor deve ser um número! reiniciando calculadora");
				sc.nextLine();
				continue;
			}

			while (i == 2) {

				try {
					n3 = resultado;
					System.out.println("");
					menu.exibirMenu();
					opcao = sc.nextInt();

					System.out.println("");
					System.out.println("Informe o valor:");
					n4 = sc.nextDouble();
				} catch (InputMismatchException erro) {
					System.out.println("");
					System.out.println("O valor deve ser um número! reiniciando cálculo");
					sc.nextLine();
					continue;
				}

				switch (opcao) {
					case 1:
						resultado = resultado + n4;
						System.out.println("");
						System.out.println("O resultado de " + df.format(n3) + " + " + df.format(n4) + " é igual a: "
								+ df.format(resultado));
						break;
					case 2:
						resultado = resultado - n4;
						System.out.println("");
						System.out.println("O resultado de " + df.format(n3) + " - " + df.format(n4) + " é igual a: "
								+ df.format(resultado));
						break;
					case 3:
						resultado = resultado * n4;
						System.out.println("");
						System.out.println("O resultado de " + df.format(n3) + " * " + df.format(n4) + " é igual a: "
								+ df.format(resultado));
						break;
					case 4:
						resultado = resultado / n4;
						System.out.println("");
						System.out.println("O resultado de " + df.format(n3) + " / " + df.format(n4) + " é igual a: "
								+ df.format(resultado));
						break;
					case 5:
						System.out.println("");
						System.out.println("Finalizando...");
						i = 3;
						break;

					default:
						System.out.println("");
						System.out.println("Inválido");
						i = 4;
				}

				System.out.println("");
				System.out.println("1 - Continuar");
				System.out.println("2 - Continuar, mas com o último número");
				System.out.println("3 - Encerrar calculadora");
				try {
					i = sc.nextInt();
				} catch (InputMismatchException erro) {
					System.out.println("");
					System.out.println("O valor deve ser um número! reiniciando calculadora");
					sc.nextLine();
					break;
				}
				continue;
			}

			if (i == 3) {
				continua = false;
				break;
			}

			while (i != 1 && i != 2 && i != 3) {
				System.out.println("");
				System.out.println("Opção inválida");
				System.out.println("1 - Continuar");
				System.out.println("2 - Continuar, mas com o último número");
				System.out.println("3 - Encerrar calculadora");
				try {
					i = sc.nextInt();
				} catch (InputMismatchException erro) {
					System.out.println("");
					System.out.println("O valor deve ser um número! reiniciando calculadora");
					sc.nextLine();
					break;
				}
				
				if (i == 3) {
					continua = false;
				}
			}

		}

		System.out.println("");
		System.out.println("Calculadora encerrada.");
		continua = false;
		sc.close();

	}

}