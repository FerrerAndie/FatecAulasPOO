/*
 * Faça um programa que calcule e exiba a soma dos números de 1 até 100.
 */
public class Lacos04_Soma1_100 {

	public static void main(String[] args) {
		int soma = 0;
		
		for(int i = 1; i <= 100; i ++) {
			soma += i;
		} 
		System.out.println("A soma de 1 a 100 é: " + soma);
	}

}
