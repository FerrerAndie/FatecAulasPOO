package persistencia;

import java.util.Scanner;
import jogo.Partida;
import modelo.Posicao;

public class PersistenciaArquivo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Partida partida = null;

        System.out.println("=================================");
        System.out.println("          JOGO DE DAMAS          ");
        System.out.println("=================================");
        System.out.println("Comandos disponíveis:");
        System.out.println("NOVO <jogador1> <jogador2>");
        System.out.println("MOVER <origem> <destino>");
        System.out.println("ESTADO");
        System.out.println("SAIR");
        System.out.println("---------------------------------");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("SAIR")) {
                System.out.println("Programa encerrado.");
                break;
            }

            String[] partes = input.split(" ");
            String comando = partes[0].toUpperCase();

            switch (comando) {
                case "NOVO":
                    if (partes.length >= 3) {
                        partida = new Partida(partes[1], partes[2]);
                        System.out.println("Partida iniciada.");
                        System.out.println("Jogador atual: " + partes[1] + " (Brancas)");
                    } else {
                        System.out.println("Uso incorreto. Exemplo: NOVO Ana Pedro");
                    }
                    break;

                case "ESTADO":
                    if (partida != null) {
                        partida.imprimirEstado();
                    } else {
                        System.out.println("Nenhuma partida em andamento. Inicie uma com NOVO.");
                    }
                    break;

                case "MOVER":
                    if (partida != null) {
                        if (partes.length >= 3) {
                            try {
                                char colOrigem = partes[1].charAt(0);
                                int linOrigem = Character.getNumericValue(partes[1].charAt(1));
                                char colDestino = partes[2].charAt(0);
                                int linDestino = Character.getNumericValue(partes[2].charAt(1));

                                Posicao origem = new Posicao(colOrigem, linOrigem);
                                Posicao destino = new Posicao(colDestino, linDestino);

                                partida.realizarMovimento(origem, destino);
                            } catch (Exception e) {
                                System.out.println("Formato de coordenadas inválido. Exemplo: MOVER A3 B4");
                            }
                        } else {
                            System.out.println("Comando MOVER incompleto. Exemplo: MOVER A3 B4");
                        }
                    } else {
                        System.out.println("Inicie uma partida primeiro.");
                    }
                    break;

                default:
                    System.out.println("Comando desconhecido. Tente novamente.");
                    break;
            }
        }
        scanner.close();
    }
}


