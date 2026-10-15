package Lista5MetodoseFuncoes;

import java.util.Random;
import java.util.Scanner;

public class exercicio12 {

    public static void mostrarMenu() {
        System.out.println("1 - Pedra");
        System.out.println("2 - Papel");
        System.out.println("3 - Tesoura");
    }

    public static int jogadaComputador() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }

    public static int jogadaJogador(Scanner scanner) {
        System.out.print("Escolha sua jogada: ");
        return scanner.nextInt();
    }

    public static String converterJogada(int jogada) {
        switch (jogada) {
            case 1:
                return "Pedra";
            case 2:
                return "Papel";
            case 3:
                return "Tesoura";
            default:
                return "Inválida";
        }
    }

    public static String verificarVencedor(int jogador, int computador) {
        if (jogador == computador) {
            return "Empate!";
        }

        if ((jogador == 1 && computador == 3)
                || (jogador == 2 && computador == 1)
                || (jogador == 3 && computador == 2)) {
            return "Jogador venceu!";
        }

        return "Computador venceu!";
    }

    public static void mostrarResultado(int jogador, int computador, String resultado) {
        System.out.println("Você: " + converterJogada(jogador));
        System.out.println("Computador: " + converterJogada(computador));
        System.out.println(resultado);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        mostrarMenu();
        int jogador = jogadaJogador(scanner);

        if (jogador < 1 || jogador > 3) {
            System.out.println("Opção inválida.");
        } else {
            int computador = jogadaComputador();
            String resultado = verificarVencedor(jogador, computador);
            mostrarResultado(jogador, computador, resultado);
        }

        scanner.close();
    }
}
