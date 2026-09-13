/*
2. Par ou ímpar
Leia um número inteiro e informe:
"Par" → quando o resto da divisão por 2 for 0
"Ímpar" → caso contrário
 */

import java.util.Scanner;
public class CondIf02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite um número inteiro: ");
		int num = sc.nextInt();
		
		if (num % 2 == 0) System.out.println("O número " + num + " é par");
		else {
			System.out.println("O número " + num + " é ímpar");
		}
		sc.close();
	}
}
