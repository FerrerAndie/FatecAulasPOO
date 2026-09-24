/*
Exercício 5 – Tabuada
Peça ao usuário um número inteiro e exiba a tabuada desse número de 1 até 10.
 */

import java.util.Scanner;

public class Lacos05_Tabuada {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int produto;
		
		System.out.print("Digite um número inteiro: ");
		int num = sc.nextInt();
		
		System.out.println();
		System.out.println("Tabuada do " +num+ ":");
		for(int i = 1; i <= 10; i++) {
			produto = i * num;
			System.out.println(num + " x " + i + " = " + produto);
		}
		
		sc.close();
	}
}
