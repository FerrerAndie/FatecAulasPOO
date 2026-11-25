package aplicacao;

import java.util.Scanner;
import jogo.Partida;
import modelo.Posicao;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Partida partida = null;

        System.out.println("==================================================");
        System.out.println("          BEM-VINDO AO JOGO DE DAMAS       ");
        System.out.println("==================================================");
        System.out.println("COMANDOS DISPONÍVEIS:");
        System.out.println("  1. NOVO <NomeBrancas> <NomePretas>  -> Inicia uma nova partida");
        System.out.println("     Exemplo: INICIAR Ana Pedro");
        System.out.println("  2. MOVER <Origem> <Destino>         -> Move uma peça");
        System.out.println("     Exemplo: MOVER A3 B4");
        System.out.println("  3. ESTADO                           -> Mostra o tabuleiro atual");
        System.out.println("  4. SAIR                             -> Fecha o programa");
        System.out.println("--------------------------------------------------\n");

        while (true) {
            System.out.print("Digite um comando (ou veja as opções acima)\n> ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("SAIR")) {
                System.out.println("Programa encerrado. Até mais!");
                break;
            }

            String[] partes = input.split("\\s+");
            String comando = partes[0].toUpperCase();

            switch (comando) {
                case "NOVO":
                    if (partes.length >= 3) {
                        partida = new Partida(partes[1], partes[2]);
                        System.out.println("\n[SUCESSO] Partida iniciada!");
                        System.out.println("-> Turno inicial: " + partes[1] + " (Peças Brancas - 'b')");
                        partida.imprimirEstado();
                    } else {
                        System.out.println("[ERRO] Comando incompleto. Use: NOVO <Jogador1> <Jogador2>");
                    }
                    break;

                case "ESTADO":
                    if (partida != null) {
                        partida.imprimirEstado();
                    } else {
                        System.out.println("[AVISO] Nenhuma partida iniciada. Digite NOVO <Nome1> <Nome2> primeiro.");
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

                                boolean movimentoRealizado = partida.realizarMovimento(origem, destino);
                                
                                // Se o movimento foi aceito pelas regras, exibe o tabuleiro atualizado de cara!
                                if (movimentoRealizado) {
                                    partida.imprimirEstado();
                                }
                            } catch (Exception e) {
                                System.out.println("[ERRO] Formato de coordenadas inválido. Use letras de A-H e números de 1-8.");
                                System.out.println("Exemplo correto: MOVER A3 B4");
                            }
                        } else {
                            System.out.println("[ERRO] Comando MOVER incompleto. Exemplo: MOVER A3 B4");
                        }
                    } else {
                        System.out.println("[AVISO] Inicie uma partida antes de tentar mover alguma peça!");
                    }
                    break;

                default:
                    System.out.println("[ERRO] Comando desconhecido. Verifique os comandos disponíveis no início da tela.");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }
}
