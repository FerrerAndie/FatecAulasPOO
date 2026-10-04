/*
 * 💡 Aqui você começa a pensar como sistema
16. Caixa eletrônico
Leia um valor inteiro e calcule a quantidade mínima de notas:
100
50
20
10
521
💡 Dica: use divisões e resto (/ e %)
 */



package dmd_Lista_2_condicionais;
import java.util.Scanner;

public class Cond16CaixaEletronico {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite um número inteiro: ");
		int valor = sc.nextInt();
		
		System.out.println();
		
		int n100 = valor / 100;
		valor = valor % 100;
		
		int n50 = valor / 50;
		valor = valor % 50;
		
		int n20 = valor / 20;
		valor = valor % 20;
		
		int n10 = valor / 10;
		valor = valor % 10;
		
		int n5 = valor / 5;
		valor = valor % 5;
		
		int n2 = valor / 2;
		valor = valor % 2;
		
		int n1 = valor / 1;
		
		System.out.println("Notas de 100: " + n100);
		System.out.println("Notas de 50: " + n50);
		System.out.println("Notas de 20: " + n20);
		System.out.println("Notas de 10: " + n10);
		System.out.println("Notas de 5: " + n5);
		System.out.println("Notas de 2: " + n2);
		System.out.println("Notas de 1: " + n1);
		
		sc.close();

	}

}
