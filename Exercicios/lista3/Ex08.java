package lista3;


import java.util.Scanner;

public class Ex08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Quantos números deseja informar? ");
        int quantidade = sc.nextInt();

        double soma = 0;

        for (int i = 1; i <= quantidade; i++) {
            System.out.print("Digite o " + i + "º número: ");
            double numero = sc.nextDouble();
            soma += numero;
        }

        if (quantidade > 0) {
            System.out.println("Média: " + soma / quantidade);
        } else {
            System.out.println("Quantidade inválida");
        }

        sc.close();
    }
}

