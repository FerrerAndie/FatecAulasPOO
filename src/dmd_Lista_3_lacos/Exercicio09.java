package dmd_Lista_3_lacos;


import java.util.Scanner;

public class Exercicio09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número inteiro de 0 a 12: ");
        int numero = sc.nextInt();

        if (numero < 0 || numero > 12) {
            System.out.println("Número inválido");
        } else {
            int fatorial = 1;

            for (int i = numero; i >= 1; i--) {
                fatorial *= i;
            }

            System.out.println(numero + "! = " + fatorial);
        }

        sc.close();
    }
}

