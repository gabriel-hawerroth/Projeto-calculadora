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

}