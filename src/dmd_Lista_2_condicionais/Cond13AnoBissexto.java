/*
 * 13. Ano bissexto
Leia um ano e informe se é bissexto.
📌 Regra:
Divisível por 4 e não por 100
OU divisível por 400
 */

package dmd_Lista_2_condicionais;

import java.util.Scanner;
public class Cond13AnoBissexto {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite um ano: ");
		int ano = sc.nextInt();
		
		if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
			System.out.println(ano+ " é um ano bissexto.");
		} else {
			System.out.println(ano+ " não é ano bissexto.");
		}
		
		
		sc.close();

	}

}
