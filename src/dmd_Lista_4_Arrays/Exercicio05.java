package dmd_Lista_4_Arrays;

import java.util.Scanner;

public class Exercicio05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pares = 0;

        for (int i = 0; i < 10; i++) {
            int numero = sc.nextInt();

            if (numero % 2 == 0) {
                pares++;
            }
        }

        System.out.println("Quantidade de pares: " + pares);

        sc.close();
    }
}

