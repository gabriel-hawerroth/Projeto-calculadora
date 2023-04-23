package calculadora;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

public class Calculadora {

	static boolean continuaCalculadora = true;
	static int i, opcao, menuInicial;
	static double resultado = 0, n1, n2, n3, n4;

	static DecimalFormat df;
	static Scanner sc;

	public static void main(String[] args) {

		sc = new Scanner(System.in);
		df = new DecimalFormat("0.#");
		df.setMaximumFractionDigits(5);

		while (continuaCalculadora) { /* começo calculadora */

			boolean continuaMenuInicial = true, umaLinha = false, passoAPasso = false;
			while (continuaMenuInicial) {
				exibirMenuInicial();
				try {
					menuInicial = sc.nextInt();
				} catch (InputMismatchException erro) {
					print("");
					print("O valor deve ser um número!");
					sc.nextLine();
					continue;
				}
				if (menuInicial == 1) {
					passoAPasso = true;
					continuaMenuInicial = false;
				} else if (menuInicial == 2) {
					umaLinha = true;
					continuaMenuInicial = false;
				} else if (menuInicial == 3) {
					continuaCalculadora = false;
					continuaMenuInicial = false;
				} else {
					print("");
					print("Opção inválida.");
					continue;
				}
			}

			while (umaLinha) { /* inicia calculadora na função única linha */
				print("");
				print("Função em desenvolvimento, aguarde.");
				break;
			}

			while (passoAPasso) { /* inicia calculadora na função passo a passo */

				print("");
				print("Digite o primeiro valor:");
				try {
					n1 = sc.nextDouble();
					exibirMenuOperacao();
					opcao = sc.nextInt();
				} catch (InputMismatchException erro) {
					print("");
					print("O valor deve ser um número! Reiniciando.");
					sc.nextLine();
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
					System.out.println("");
					System.out.println("Opção inválida, reiniciando.");
					continue;
				}

				print("");
				print("Digite o segundo valor:");
				try {
					n2 = sc.nextDouble();
				} catch (InputMismatchException erro) {
					print("");
					print("O valor deve ser um número! Reiniciando passo a passo.");
					sc.nextLine();
					continue;
				}

				obterResultado(n1, n2, resultado);

				exibirMenuContinuar();
				try {
					i = sc.nextInt();
				} catch (InputMismatchException erro) {
					print("");
					print("O valor deve ser um número! Reiniciando calculadora.");
					sc.nextLine();
					break;
				}
				if (i == 1){
					continue;
				}

				while (i == 2) { /* enquanto quiser continuar com o último valor */
					n3 = resultado;
					try {
						exibirMenuOperacao();
						opcao = sc.nextInt();
					} catch (InputMismatchException erro) {
						print("");
						print("O valor deve ser um número! Reiniciando cálculo.");
						sc.nextLine();
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

					print("");
					print("Informe o valor:");
					try {
						n4 = sc.nextDouble();
					} catch (InputMismatchException erro) {
						print("");
						print("O valor deve ser um número! Reiniciando cálculo.");
						sc.nextLine();
						continue;
					}

					obterResultado2(n3, n4, resultado);

					exibirMenuContinuar();
					try {
						i = sc.nextInt();
					} catch (InputMismatchException erro) {
						print("");
						print("O valor deve ser um número! Reiniciando calculadora.");
						sc.nextLine();
						break;
					}
					continue;
				}

				if (i == 3) {
					passoAPasso = false;
					break;
				} else if (i == 4) {
					passoAPasso = false;
					continuaCalculadora = false;
					break;
				}

				while (i != 1 && i != 2 && i != 3) {
					exibirMenuInvalido();
					try {
						i = sc.nextInt();
					} catch (InputMismatchException erro) {
						print("");
						print("O valor deve ser um número! Reiniciando calculadora.");
						sc.nextLine();
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
		sc.close();

	}








	private static void print(String texto) {
		System.out.println(texto);
	}

	private static double somar(double a, double b) {
		return a + b;
	}

	private static double subtrair(double a, double b) {
		return a - b;
	}

	private static double multiplicar(double a, double b) {
		return a * b;
	}

	private static double dividir(double a, double b) {
		if (b == 0) {
			throw new IllegalArgumentException("Não é possível dividir por zero");
		}
		return a / b;
	}

	private static double impar_par(double a) {
		return a % 2;
	}

	private static double raizQuadrada(double a) {
		return Math.sqrt(a);
	}

	private static double raizCubica(double a) {
		return Math.cbrt(a);
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
		print("Digite o número da operação desejada: ");
	}

	private static void exibirMenuInicial() {
		print("");
		print("Bem vindo!");
		print("1 - Fazer cálculo passo a passo");
		print("2 - Fazer cálculo por conta própria em uma linha");
		print("3 - Encerrar calculadora");
	}

	private static void exibirMenuContinuar() {
		print("");
		print("1 - Continuar");
		print("2 - Continuar, mas com o resultado anterior");
		print("3 - Voltar para o menu inicial");
		print("4 - Encerrar calculadora");
	}

	private static void exibirMenuInvalido() {
		print("");
		print("Opção inválida.");
		print("1 - Continuar");
		print("2 - Continuar, mas com o último número");
		print("3 - Encerrar calculadora");
	}

	private static void obterRaizQuadrada(double num) {
		if (num - (int) num == 0) {
			resultado = raizQuadrada(num);
			print("");
			print(
					"A raiz quadrada de " + df.format(num) + " é " + df.format(resultado) + ".");
		} else {
			print("");
			print("Só é possível calcular a raiz quadrada de um número inteiro.");
		}
		boolean continuaRaizQuadrada = true;
		while (continuaRaizQuadrada) {
			int x;
			print("");
			print("1 - Continuar");
			print("2 - Encerrar calculadora");
			try {
				x = sc.nextInt();
			} catch (InputMismatchException erro) {
				print("");
				print("O valor deve ser um número!");
				sc.nextLine();
				continue;
			}
			if (x == 1) {
				i = 1;
				continuaRaizQuadrada = false;
			} else if (x == 2) {
				i = 3;
				continuaRaizQuadrada = false;
			} else {
				print("");
				print("Opção inválida.");
				continue;
			}
		}
	}

	private static void obterRaizCubica(double num) {
		if (num - (int) num == 0) {
			resultado = raizCubica(num);
			print("");
			System.out
					.println("A raiz cúbica de " + df.format(num) + " é " + df.format(resultado) + ".");
		} else {
			print("");
			print("Só é possível calcular a raiz cúbica de um número inteiro.");
		}
		boolean continuaRaizCubica = true;
		while (continuaRaizCubica) {
			double x;
			print("");
			print("1 - Continuar");
			print("2 - Encerrar calculadora");
			try {
				x = sc.nextDouble();
			} catch (InputMismatchException erro) {
				print("");
				print("O valor deve ser um número!");
				sc.nextLine();
				continue;
			}
			if (x == 1) {
				i = 1;
				continuaRaizCubica = false;
			} else if (x == 2) {
				i = 3;
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
		resultado = impar_par(num);
		print("");

		if (resultado == 1) {
			print("O número " + df.format(num) + " é ímpar.");
		} else if (resultado == 0) {
			print("O número " + df.format(num) + " é par.");
		} else {
			print("A definição de ímpar ou par se aplica apenas a números inteiros.");
		}

		while (continuaImparPar) {
			double x;
			print("");
			print("1 - Continuar");
			print("2 - Encerrar calculadora");
			try {
				x = sc.nextDouble();
			} catch (InputMismatchException erro) {
				print("");
				print("O valor deve ser um número!");
				sc.nextLine();
				continue;
			}
			if (x == 1) {
				i = 1;
				continuaImparPar = false;
			} else if (x == 2) {
				i = 3;
				continuaImparPar = false;
			} else {
				print("");
				print("Opção inválida.");
				continue;
			}
		}

	}

	private static void obterResultado(double num1, double num2, double result) {
		switch (opcao) {
			case 1:
				result = somar(num1, num2);
				print("");
				System.out
						.println("O resultado de " + df.format(num1) + " + " + df.format(num2) + " é igual a: "
								+ df.format(result));
				break;
			case 2:
				result = subtrair(num1, num2);
				print("");
				System.out
						.println("O resultado de " + df.format(num1) + " - " + df.format(num2) + " é igual a: "
								+ df.format(result));
				break;
			case 3:
				result = multiplicar(num1, num2);
				print("");
				System.out
						.println("O resultado de " + df.format(num1) + " * " + df.format(num2) + " é igual a: "
								+ df.format(result));
				break;
			case 4:
				result = dividir(num1, num2);
				print("");
				System.out
						.println("O resultado de " + df.format(num1) + " / " + df.format(num2) + " é igual a: "
								+ df.format(result));
				break;

			default:
				print("");
				print("Inválido.");
				i = 4;
		}
		resultado = result;
	}

	private static void obterResultado2(double num1, double num2, double result) {
		switch (opcao) {
			case 1:
				result = somar(result, num2);
				print("");
				System.out
						.println("O resultado de " + df.format(num1) + " + " + df.format(num2) + " é igual a: "
								+ df.format(result));
				break;
			case 2:
				result = subtrair(result, num2);
				print("");
				System.out
						.println("O resultado de " + df.format(num1) + " - " + df.format(num2) + " é igual a: "
								+ df.format(result));
				break;
			case 3:
				result = multiplicar(result, num2);
				print("");
				System.out
						.println("O resultado de " + df.format(num1) + " * " + df.format(num2) + " é igual a: "
								+ df.format(result));
				break;
			case 4:
				result = dividir(result, num2);
				print("");
				System.out
						.println("O resultado de " + df.format(num1) + " / " + df.format(num2) + " é igual a: "
								+ df.format(result));
				break;

			default:
				print("");
				print("Inválido.");
				i = 4;
		}
		resultado = result;
	}

}