/*
4. Maior número
Leia dois números e informe qual é o maior.
�� Extra: trate também quando os dois forem iguais.
 */

import java.util.Scanner;
public class CondIf04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite o primeiro número: ");
		float num1 = sc.nextFloat();
		
		System.out.print("Digite o segundo número: ");
		float num2 = sc.nextFloat();
		
		if (num1 > num2) System.out.print(num1 + " é maior que " + num2);
		else if (num1 < num2) System.out.print(num2 + " é maior que " + num1);
		else {
			System.out.print("Os números são iguais");
		}
		
		sc.close();
	}

}
