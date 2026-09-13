/*
Maior de três números
Leia três números e informe qual é o maior.
�� Dica: compare progressivamente.
 */

import java.util.Scanner;
public class CondIf08_MaiorQue {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite o primeiro número: ");
		float a = sc.nextFloat();
		
		System.out.print("Digite o segundo número: ");
		float b = sc.nextFloat();
		
		System.out.print("Digite o terceiro número: ");
		float c = sc.nextFloat();
		
		System.out.println();
		
		if (a > b &&  a > b) {
			System.out.print(a + " é o maior número. ");
		} else if (b > a &&  b > c) {
			System.out.print(b + " é o maior número. ");
		} else if (c > a &&  c > b) {
			System.out.print(c + " é o maior número. ");
		} else if (a == b &&  a == c) {
			System.out.print("Os números são iguais");
		} 
		
		sc.close();
	}

}
