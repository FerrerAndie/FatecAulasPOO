/*
Exercício 1 – Leitura e exibição
Leia 5 números inteiros e armazene em um array.
Depois, exiba todos os valores.
 */


package lista4;

import java.util.Scanner;

public class Ex01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] vetor = new int[5]; 
		
		for(int i = 0; i < 5; i++) {
			System.out.print("Digite o " + (i + 1) + "° número: ");
			vetor[i] = sc.nextInt();
			}
		
		System.out.println("\nOs números digitados foram: ");
		for (int i = 0; i < 5; i++) {
            System.out.print(vetor[i] + " ");
        }
		
		sc.close();

	}

}
