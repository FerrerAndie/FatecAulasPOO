/*
5. Número dentro de intervalo
Leia um número e verifique:
Está entre 10 e 50 (inclusive)
Ou está fora desse intervalo
�� Dica: use && (E lógico)
 */

import java.util.Scanner;
public class CondIf05_NumeroIntervalo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite um número inteiro: ");
		int num = sc.nextInt();
		
		System.out.println();
		
		if(num >= 10 && num <= 50) {
			System.out.print("O número " +num+ " está no intervalo (10-50).");
		} else {
			System.out.print("O número " +num+ " está fora do intervalo (10-50).");
		}
		
		sc.close();
	}

}
