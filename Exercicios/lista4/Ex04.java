package lista4;

import java.util.Scanner;

public class Ex04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double soma = 0;

        for (int i = 0; i < 6; i++) {
            soma += sc.nextDouble();
        }

        System.out.println("Média: " + soma / 6);

        sc.close();
    }
}
