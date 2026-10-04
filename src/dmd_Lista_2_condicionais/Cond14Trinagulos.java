/*
 * 14. Tipo de triângulo
Leia três lados e classifique:
Equilátero → todos iguais
Isósceles → dois iguais
Escaleno → todos diferentes
💡 Extra: valide se os lados formam um triângulo.
Para saber se três lados formam um triângulo, verifique se a soma dos dois lados menores 
é maior do que o maior lado.
 */

package dmd_Lista_2_condicionais;

import java.util.Scanner;
public class Cond14Trinagulos {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite o primeiro lado: ");
		int a = sc.nextInt();
		
		System.out.print("Digite o segundo lado: ");
		int b = sc.nextInt();
		
		System.out.print("Digite o terceiro lado: ");
		int c = sc.nextInt();
		
		if((a + b > c) && (a + c > b) && (b + c > a)) {
			if(a == b & b == c & c == a) {
				System.out.println("Triângulo Equilátero.");
			} else if (a == b || b == c || c == a) {
				System.out.println("Triângulo Isóceles.");
			} else {
				System.out.println("Triângulo Escaleno.");
			}
		} else {
			System.out.println("Não forma um triângulo.");
		}
		
		sc.close();

	}

}
