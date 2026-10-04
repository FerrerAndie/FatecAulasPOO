/*
 * 12. Dia da semana
Leia um número de 1 a 7 e exiba:
1 → Domingo 2 → Segunda 3 → Terça 4 → Quarta 5 → Quinta 6 → Sexta 7 → Sábado
💡 Caso inválido: mostrar erro.
 */

package dmd_Lista_2_condicionais;

import java.util.Scanner;
public class CondSwitch12DiaDaSemana {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num;
		
		System.out.println("Digite um número de 1 a 7: ");
		num = sc.nextInt();
		
		switch (num) {
		case 1:
			System.out.println("Domingo :)");
			break;
		case 2:
			System.out.println("Segunda :(");
			break;
		case 3:
			System.out.println("Terça :/");
			break;	
		case 4:
			System.out.println("Quarta :|");
			break;	
		case 5:
			System.out.println("Quinta :)");
			break;
		case 6:
			System.out.println("Sexta :D");
			break;
		case 7:
			System.out.println("Sábado :D");
			break;
		default:
			System.out.println("Dia inválido!");
			break;	
		}
			
		sc.close();

	}

}
