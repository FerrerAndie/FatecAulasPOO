package dmd_Lista_4_Arrays;

import java.util.Scanner;

public class Exercicio08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] v = new int[10];
        int contador = 0;

        for (int i = 0; i < v.length; i++) {
            v[i] = sc.nextInt();
        }

        int x = sc.nextInt();

        for (int i = 0; i < v.length; i++) {
            if (v[i] == x) {
                contador++;
            }
        }

        System.out.println(contador);

        sc.close();
    }
}
