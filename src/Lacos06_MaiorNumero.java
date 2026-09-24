/*
Exercício 6 – Maior número
Leia 5 números inteiros digitados pelo usuário e informe o maior número.
 */

import java.util.Scanner;

public class Lacos06_MaiorNumero {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int maior = Integer.MIN_VALUE;
		
		for(int i = 1; i <= 5; i++) {
			System.out.print("Digite um número inteiro: ");
			int num = sc.nextInt();
			
			if(num > maior) {
				maior = num;
			}
		}
		
		System.out.println();
		System.out.println("O maior número digitado foi: " + maior);
		sc.close();
		
		
		
	}
}
