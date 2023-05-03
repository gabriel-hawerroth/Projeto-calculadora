package calculadora;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

public class Calculadora {

	static boolean continuaCalculadora = true;
	static int i, opcao, menuInicial;
	static double resultado = 0, n1, n2, n3, n4;

	static DecimalFormat df = new DecimalFormat("0.#");
	static Scanner sca = new Scanner(System.in);
	static Scanner scb = new Scanner(System.in);

	public static void main(String[] args) {

		while (continuaCalculadora) { /* começo calculadora */

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
					continuaCalculadora = false;
					continuaMenuInicial = false;
				} else {
					print("");
					print("Opção inválida.");
					continue;
				}
			}

			while (umaLinha) { /* inicia calculadora na função única linha */

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
					if (invalido == true){
						continue;
					}else if (invalido == false) {
						print("");
						print("O resultado é: " + df.format(resultado));
					}

					exibirMenuContinuar2();
					try {
						i = sca.nextInt();
					} catch (InputMismatchException erro) {
						print("");
						print("O valor deve ser um número! Reiniciando calculadora.");
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

			while (passoAPasso) { /* inicia calculadora na função passo a passo */

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
					System.out.println("");
					System.out.println("Opção inválida, reiniciando.");
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

				obterResultado(n1, n2, resultado);

				exibirMenuContinuar();
				try {
					i = sca.nextInt();
				} catch (InputMismatchException erro) {
					print("");
					print("O valor deve ser um número! Reiniciando calculadora.");
					sca.nextLine();
					break;
				}

				while (i == 2) { /* enquanto quiser continuar com o último valor */
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

					obterResultado2(n3, n4, resultado);

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

	}

	// métodos

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