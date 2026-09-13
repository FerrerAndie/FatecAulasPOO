/*
Média do aluno
Leia duas notas e calcule a média.
Classifique:
"Aprovado" → média ≥ 6
"Recuperação" → 4 ≤ média < 6
"Reprovado" → média < 4
 */

import java.util.Scanner;
public class CondIf07_MediaSimples {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		float media;
		
		System.out.print("Digite a 1° nota: ");
		float nota1 = sc.nextFloat();
		
		System.out.print("Digite a 2° nota: ");
		float nota2 = sc.nextFloat();
		System.out.println();
		
		media = (nota1 + nota2) / 2;
		
		if(media >= 6) {
			System.out.print("A média é: " + media + ". Aprovado!");
		} else if (media >= 4 && media < 6){
			System.out.print("A média é: " + media + ". Recuperação.");
		} else {
			System.out.print("A média é: " + media + ". Reprovado.");
		}
		
		sc.close();

	}

}

