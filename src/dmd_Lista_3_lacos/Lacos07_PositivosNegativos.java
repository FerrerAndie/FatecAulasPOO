/*
Leia 10 números inteiros e informe quantos são positivos e negativos.
Considere o número 0 como neutro.
 */
package dmd_Lista_3_lacos;

import java.util.Scanner;
public class Lacos07_PositivosNegativos {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int positivos = 0;
		int negativos = 0;
		int neutros = 0;
		
		System.out.println("Digite 10 números inteiros: ");
		for(int i = 1; i <= 10; i++) {
			System.out.print("Digite o " +i+ " número: ");
			int num = sc.nextInt();
			
			if(num > 0) {
				positivos ++;
			} else if(num == 0){
				neutros++;
			} else {
				negativos ++;
			}
		}
		
		System.out.println("\n--- Resultado ---");
		System.out.println();
	        System.out.println("Positivos: " + positivos);
	        System.out.println("Negativos: " + negativos);
	        System.out.println("Neutros (zeros): " + neutros);
			
		sc.close();
	}

}
