/*
Exercício 2 – Soma dos elementos
Leia 10 números e calcule a soma de todos os elementos do array.
 */

package lista4;
import java.util.Scanner;

public class Ex02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] vetor =  new int [10];
		int soma = 0;
		
		for(int i = 0; i < 10; i++) {
			System.out.print("Digite o " +(i + 1) + "° número: ");
			vetor[i] = sc.nextInt();
			soma += vetor[i];
		}
		
		System.out.println("\nA soma do vetor é: "+soma);

	}

}
