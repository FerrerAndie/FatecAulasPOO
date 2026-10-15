package Lista5MetodoseFuncoes;

import java.util.Random;
import java.util.Scanner;

public class exercicio15 {

    public static void iniciarJogo() {
        System.out.println("=== BATALHA RPG ===");
        System.out.println("Você enfrentará um monstro!");
    }

    public static void mostrarMenu() {
        System.out.println("\n1 - Atacar");
        System.out.println("2 - Curar");
        System.out.println("3 - Ver status");
    }

    public static int lerOpcao(Scanner scanner) {
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    public static int atacar() {
        Random random = new Random();
        return random.nextInt(16) + 5;
    }

    public static int receberDano(int vida) {
        Random random = new Random();
        int dano = random.nextInt(11) + 5;

        vida -= dano;

        if (vida < 0) {
            vida = 0;
        }

        System.out.println("Você recebeu " + dano + " de dano.");
        return vida;
    }

    public static int curar(int vida) {
        vida += 15;

        if (vida > 100) {
            vida = 100;
        }

        System.out.println("Você recuperou vida.");
        return vida;
    }

    public static void mostrarStatus(int vidaJogador, int vidaMonstro) {
        System.out.println("Sua vida: " + vidaJogador);
        System.out.println("Vida do monstro: " + vidaMonstro);
    }

    public static boolean verificarVitoria(int vidaMonstro) {
        return vidaMonstro <= 0;
    }

    public static boolean verificarDerrota(int vidaJogador) {
        return vidaJogador <= 0;
    }

    public static void encerrarJogo(boolean venceu) {
        if (venceu) {
            System.out.println("Parabéns! Você venceu a batalha!");
        } else {
            System.out.println("Você foi derrotado!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int vidaJogador = 100;
        int vidaMonstro = 80;
        boolean venceu = false;

        iniciarJogo();

        while (vidaJogador > 0 && vidaMonstro > 0) {
            mostrarStatus(vidaJogador, vidaMonstro);
            mostrarMenu();

            int opcao = lerOpcao(scanner);

            if (opcao == 1) {
                int dano = atacar();
                vidaMonstro -= dano;

                if (vidaMonstro < 0) {
                    vidaMonstro = 0;
                }

                System.out.println("Você causou " + dano + " de dano!");

                if (vidaMonstro > 0) {
                    vidaJogador = receberDano(vidaJogador);
                }

            } else if (opcao == 2) {
                vidaJogador = curar(vidaJogador);

                vidaJogador = receberDano(vidaJogador);

            } else if (opcao == 3) {
                mostrarStatus(vidaJogador, vidaMonstro);

            } else {
                System.out.println("Opção inválida.");
            }
        }

        venceu = verificarVitoria(vidaMonstro);
        encerrarJogo(venceu);

        scanner.close();
    }
}

