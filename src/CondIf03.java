/*
3. Maior de idade
Leia a idade de uma pessoa:
"Maior de idade" → idade ≥ 18
"Menor de idade" → idade < 18
 */

import java.util.Scanner;
public class CondIf03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite a idade: ");
		int idade = sc.nextInt();
		
		if(idade >= 18) System.out.print("Maior de idade.");
		else {
			System.out.print("Menor de idade.");
		}
		
		sc.close();
	}

}
