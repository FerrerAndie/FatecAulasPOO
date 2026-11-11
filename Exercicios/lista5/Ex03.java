/*
Problema
O programa verifica se um número é par.
Sua tarefa
Divida em funções:
lerNumero()
ehPar()
mostrarResultado()
 */

package lista5;

import java.util.Scanner;

public class Ex03 {
	
	public static int lerNumero() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número inteiro: ");
        int n = sc.nextInt();

        return n;
    }

    public static boolean ehPar(int n) {
        if (n % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void mostrarResultado(boolean par) {
        if (par) {
            System.out.println("\nO número é par.");
        } else {
            System.out.println("\nO número não é ímpar.");
        }
    }

    public static void main(String[] args) {

        int numero = lerNumero();

        boolean resultado = ehPar(numero);

        mostrarResultado(resultado);
    }
}