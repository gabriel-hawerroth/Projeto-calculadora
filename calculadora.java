package calculadora;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.InputMismatchException;

public class calculadora {

	public static void main(String[] args) {

		boolean continuaCalculadora = true;

		Scanner sc = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("0.#");
		df.setMaximumFractionDigits(5);

		metodos met = new metodos();

		while (continuaCalculadora) {  								/* começo calculadora */
			double resultado = 0, n3, n1, n2, n4;
			int i, opcao, menuInicial;

			boolean continuaMenuInicial = true, umaLinha = false, passoAPasso = false;
			while (continuaMenuInicial) {
				System.out.println("");
				System.out.println("Bem vindo!");
				System.out.println("1 - Fazer cálculo passo a passo");
				System.out.println("2 - Fazer cálculo por conta própria em uma linha");
				System.out.println("3 - Encerrar calculadora");
				try {
					menuInicial = sc.nextInt();
				} catch (InputMismatchException erro) {
					System.out.println("");
					System.out.println("O valor deve ser um número!");
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
					System.out.println("");
					System.out.println("Opção inválida.");
					continue;
				}
			}

			while (umaLinha) { 										/* inicia calculadora na função única linha */
				System.out.println("");
				System.out.println("Função em desenvolvimento, aguarde.");
				break;
			}

			while (passoAPasso) {  									/* inicia calculadora na função passo a passo */

				System.out.println("");
				System.out.println("Digite o primeiro valor:");
				try {
					n1 = sc.nextDouble();
					met.exibirMenu();
					opcao = sc.nextInt();
				} catch (InputMismatchException erro) {
					System.out.println("");
					System.out.println("O valor deve ser um número! Reiniciando.");
					sc.nextLine();
					continue;
				}

				if (opcao == 5) {
					if (n1 - (int) n1 == 0) {
						resultado = met.raizQuadrada(n1);
						System.out.println("");
						System.out.println("A raiz quadrada de " + df.format(n1) + " é " + df.format(resultado) + ".");
					} else {
						System.out.println("");
						System.out.println("Só é possível calcular a raiz quadrada de um número inteiro.");
					}
					boolean continuaRaizQuadrada = true;
					while (continuaRaizQuadrada) {
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
							continuaRaizQuadrada = false;
							passoAPasso = false;
						} else if (x == 2) {
							continuaRaizQuadrada = false;
							passoAPasso = false;
							continuaCalculadora = false;
						} else {
							System.out.println("");
							System.out.println("Opção inválida.");
							continue;
						}
					}
					continue;
				} else if (opcao == 6) {
					if (n1 - (int) n1 == 0) {
						resultado = met.raizCubica(n1);
						System.out.println("");
						System.out.println("A raiz cúbica de " + df.format(n1) + " é " + df.format(resultado) + ".");
					} else {
						System.out.println("");
						System.out.println("Só é possível calcular a raiz cúbica de um número inteiro.");
					}
					boolean continuaRaizCubica = true;
					while (continuaRaizCubica) {
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
							continuaRaizCubica = false;
							passoAPasso = false;
						} else if (x == 2) {
							continuaRaizCubica = false;
							passoAPasso = false;
							continuaCalculadora = false;
						} else {
							System.out.println("");
							System.out.println("Opção inválida.");
							continue;
						}
					}
					continue;
				} else if (opcao == 7) {

					boolean continuaImparPar = true;
					resultado = met.impar_par(n1);
					System.out.println("");

					if (resultado == 1) {
						System.out.println("O número " + df.format(n1) + " é ímpar.");
					} else if (resultado == 0) {
						System.out.println("O número " + df.format(n1) + " é par.");
					} else {
						System.out.println("A definição de ímpar ou par se aplica apenas a números inteiros.");
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
							passoAPasso = false;
						} else if (x == 2) {
							continuaImparPar = false;
							passoAPasso = false;
							continuaCalculadora = false;
						} else {
							System.out.println("");
							System.out.println("Opção inválida.");
							continue;
						}
					}
					continue;
				} else if (opcao == 8) {
					passoAPasso = false;
					continuaCalculadora = false;
					break;
				} else if (opcao <= 0 || opcao >= 9) {
					System.out.println("");
					System.out.println("Opção inválida, reiniciando.");
					continue;
				}

				System.out.println("");
				System.out.println("Digite o segundo valor:");
				try {
					n2 = sc.nextDouble();
				} catch (InputMismatchException erro) {
					System.out.println("");
					System.out.println("O valor deve ser um número! Reiniciando.");
					sc.nextLine();
					continue;
				}

				switch (opcao) {
					case 1:
						resultado = met.somar(n1, n2);
						System.out.println("");
						System.out.println("O resultado de " + df.format(n1) + " + " + df.format(n2) + " é igual a: "
								+ df.format(resultado) + ".");
						break;
					case 2:
						resultado = met.subtrair(n1, n2);
						System.out.println("");
						System.out.println("O resultado de " + df.format(n1) + " - " + df.format(n2) + " é igual a: "
								+ df.format(resultado) + ".");
						break;
					case 3:
						resultado = met.multiplicar(n1, n2);
						System.out.println("");
						System.out.println("O resultado de " + df.format(n1) + " * " + df.format(n2) + " é igual a: "
								+ df.format(resultado) + ".");
						break;
					case 4:
						resultado = met.dividir(n1, n2);
						System.out.println("");
						System.out.println("O resultado de " + df.format(n1) + " / " + df.format(n2) + " é igual a: "
								+ df.format(resultado) + ".");
						break;
					default:
						System.out.println("");
						System.out.println("Opção inválida.");
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
					System.out.println("O valor deve ser um número! Reiniciando.");
					sc.nextLine();
					break;
				}
				if (i == 1){
					passoAPasso = false;
					break;
				}

				while (i == 2) {    							/* enquanto quiser continuar com o último valor */
					n3 = resultado;
					System.out.println("");
					try {
						met.exibirMenu();
						opcao = sc.nextInt();
					} catch (InputMismatchException erro) {
						System.out.println("");
						System.out.println("O valor deve ser um número! Reiniciando cálculo.");
						sc.nextLine();
						continue;
					}
					if (opcao == 5) {
						if (n3 - (int) n3 == 0) {
							resultado = met.raizQuadrada(n3);
							System.out.println("");
							System.out.println("A raiz quadrada de " + df.format(n3) + " é " + df.format(resultado) + ".");
						} else {
							System.out.println("");
							System.out.println("Só é possível calcular a raiz quadrada de um número inteiro.");
						}
						boolean continuaRaizQuadrada = true;
						while (continuaRaizQuadrada) {
							int x;
							System.out.println("");
							System.out.println("1 - Continuar");
							System.out.println("2 - Encerrar calculadora");
							try {
								x = sc.nextInt();
							} catch (InputMismatchException erro) {
								System.out.println("");
								System.out.println("O valor deve ser um número!");
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
								System.out.println("");
								System.out.println("Opção inválida.");
								continue;
							}
						}
						continue;
					} else if (opcao == 6) {
						if (n3 - (int) n3 == 0) {
							resultado = met.raizCubica(n3);
							System.out.println("");
							System.out.println("A raiz cúbica de " + df.format(n3) + " é " + df.format(resultado) + ".");
						} else {
							System.out.println("");
							System.out.println("Só é possível calcular a raiz cúbica de um número inteiro.");
						}
						boolean continuaRaizCubica = true;
						while (continuaRaizCubica) {
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
								i = 1;
								continuaRaizCubica = false;
							} else if (x == 2) {
								i = 3;
								continuaRaizCubica = false;
							} else {
								System.out.println("");
								System.out.println("Opção inválida.");
								continue;
							}
						}
						continue;
					} else if (opcao == 7) {
	
						boolean continuaImparPar = true;
						resultado = met.impar_par(n3);
						System.out.println("");
	
						if (resultado == 1) {
							System.out.println("O número " + df.format(n3) + " é ímpar.");
						} else if (resultado == 0) {
							System.out.println("O número " + df.format(n3) + " é par.");
						} else {
							System.out.println("A definição de ímpar ou par se aplica apenas a números inteiros.");
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
								i = 1;
								continuaImparPar = false;
							} else if (x == 2) {
								i = 3;
								continuaImparPar = false;
							} else {
								System.out.println("");
								System.out.println("Opção inválida.");
								continue;
							}
						}
						continue;
					} else if (opcao == 8) {
						passoAPasso = false;
						continuaCalculadora = false;
						break;
					} else if (opcao <= 0 || opcao >= 9) {
						System.out.println("");
						System.out.println("Opção inválida, reiniciando passo a passo.");
						continue;
					}

					System.out.println("");
					System.out.println("Informe o valor:");
					try {
						n4 = sc.nextDouble();
					} catch (InputMismatchException erro) {
						System.out.println("");
						System.out.println("O valor deve ser um número! Reiniciando cálculo.");
						sc.nextLine();
						continue;
					}
					switch (opcao) {
						case 1:
							resultado = resultado + n4;
							System.out.println("");
							System.out
									.println("O resultado de " + df.format(n3) + " + " + df.format(n4) + " é igual a: "
											+ df.format(resultado));
							break;
						case 2:
							resultado = resultado - n4;
							System.out.println("");
							System.out
									.println("O resultado de " + df.format(n3) + " - " + df.format(n4) + " é igual a: "
											+ df.format(resultado));
							break;
						case 3:
							resultado = resultado * n4;
							System.out.println("");
							System.out
									.println("O resultado de " + df.format(n3) + " * " + df.format(n4) + " é igual a: "
											+ df.format(resultado));
							break;
						case 4:
							resultado = resultado / n4;
							System.out.println("");
							System.out
									.println("O resultado de " + df.format(n3) + " / " + df.format(n4) + " é igual a: "
											+ df.format(resultado));
							break;
						case 5:
							System.out.println("");
							System.out.println("Finalizando...");
							i = 3;
							break;

						default:
							System.out.println("");
							System.out.println("Inválido.");
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
						System.out.println("O valor deve ser um número! Reiniciando calculadora.");
						sc.nextLine();
						break;
					}
					continue;
				}

				if (i == 3) {
					passoAPasso = false;
					continuaCalculadora = false;
					break;
				}

				while (i != 1 && i != 2 && i != 3) {
					System.out.println("");
					System.out.println("Opção inválida.");
					System.out.println("1 - Continuar");
					System.out.println("2 - Continuar, mas com o último número");
					System.out.println("3 - Encerrar calculadora");
					try {
						i = sc.nextInt();
					} catch (InputMismatchException erro) {
						System.out.println("");
						System.out.println("O valor deve ser um número! Reiniciando calculadora.");
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
		System.out.println("");
		System.out.println("Calculadora encerrada.");
		continuaCalculadora = false;
		sc.close();

	}

}