package Lista5MetodoseFuncoes;

import java.util.Scanner;

public class exercicio08 {

    public static String lerNome(Scanner scanner) {
        System.out.print("Digite seu nome: ");
        return scanner.nextLine();
    }

    public static int lerIdade(Scanner scanner) {
        System.out.print("Digite sua idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        return idade;
    }

    public static String lerCidade(Scanner scanner) {
        System.out.print("Digite sua cidade: ");
        return scanner.nextLine();
    }

    public static void mostrarResumo(String nome, int idade, String cidade) {
        System.out.println("\n--- Resumo ---");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Cidade: " + cidade);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nome = lerNome(scanner);
        int idade = lerIdade(scanner);
        String cidade = lerCidade(scanner);

        mostrarResumo(nome, idade, cidade);

        scanner.close();
    }
}

