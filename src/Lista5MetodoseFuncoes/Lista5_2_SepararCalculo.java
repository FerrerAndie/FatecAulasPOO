/*
Crie funções:
somar()
mostrarResultado()
 */

package Lista5MetodoseFuncoes;

public class Lista5_2_SepararCalculo {
	
	public static int somar(int n1, int n2) {
		int soma = n1 + n2;
		return soma;
	}
	
	public static void mostrarResultado(int soma) {
		System.out.println("A soma é: " + soma);
	}

	public static void main(String[] args) {
		
		int resultado = somar(10, 20);
		mostrarResultado(resultado);

	}

}
