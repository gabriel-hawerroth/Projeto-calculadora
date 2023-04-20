package calculadora;

public class metodos {

	public double somar(double a, double b) {
		return a + b;
	}

	public double subtrair(double a, double b) {
		return a - b;
	}

	public double multiplicar(double a, double b) {
		return a * b;
	}

	public double dividir(double a, double b) {
		if (b == 0) {
			throw new IllegalArgumentException("Não é possível dividir por zero");
		}
		return a / b;
	}

	public double impar_par(double a) {
		return a % 2;
	}

	public double raizQuadrada(double a) {
		return Math.sqrt(a);
	}

	public double raizCubica(double a) {
		return Math.cbrt(a);
	}

	public void exibirMenu(){
		System.out.println("");
		System.out.println("1 - Somar");
		System.out.println("2 - Subtrair");
		System.out.println("3 - Multiplicar");
		System.out.println("4 - Dividir");
		System.out.println("5 - Raiz quadrada");
		System.out.println("6 - Raiz cúbica");
		System.out.println("7 - Ímpar/par");
		System.out.println("8 - Encerrar calculadora");
		System.out.print("Digite o número da operação desejada: ");
		}

}