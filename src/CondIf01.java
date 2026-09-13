/*
Leia um número inteiro e classifique:
"Positivo" → maior que 0
"Negativo" → menor que 0
"Zero" → igual a 0
 */

import java.util.Scanner;
public class CondIf01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite um número inteiro: ");
		int numero = sc.nextInt();
		
		if (numero > 0) System.out.println("O número " + numero + " é positivo");
		else if (numero == 0) System.out.println("O número é igual a " + numero);
		else {
			System.out.println("O número " + numero + " é negativo");
		}
		
		sc.close();
	}

}
