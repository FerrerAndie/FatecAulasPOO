package dmd_Lista_2_condicionais;

/*
 * 11. Calculadora com switch
Leia:
dois números
uma operação (+, -, *, /)
Execute a operação usando switch.
💡 Dica: cuidado com divisão por zero.
 */

import java.util.Scanner;
public class CondSwitch11 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double a, b;
		
		System.out.print("Digite o primeiro número: ");
		a = sc.nextDouble();
		
		System.out.print("Digite o segundo número: ");
		b = sc.nextDouble();
		
		System.out.print("Digite a operação (+, -, *, /): ");
		char op = sc.next().charAt(0);
		
		System.out.println();
		
		switch(op) {
		case '+':
			System.out.println("Resultado: " + (a + b));
			break;
		case '-':
			System.out.println("Resultado: " + (a - b));
			break;
		case '*':
			System.out.println("Resultado: " + (a * b));
			break;
		case '/':
			if(b != 0) {
				System.out.println("Resultado: " + (a / b) );
			} else {
				System.out.println("Operação inválida, número não pode ser dividido por 0!");
			}
			break;
		default:
		System.out.println("Operação inválida");
		
		}
		
		sc.close();

	}

}
