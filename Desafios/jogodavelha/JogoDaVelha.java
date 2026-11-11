
package jogodavelha;

import java.util.Scanner;

public class JogoDaVelha {

    private Tabuleiro tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private Scanner entrada;

    public JogoDaVelha() {
        tabuleiro = new Tabuleiro();
        jogador1 = new Jogador("Ana", 'X');
        jogador2 = new Jogador("Bia", 'O');
        entrada = new Scanner(System.in);
    }


	public void iniciar() {
	    String resposta;
	
	    do {
	        tabuleiro.inicializar();
	        boolean vezJogador1 = true;
	
	        while (true) {
	            tabuleiro.mostrar();
	
	            Jogador jogadorAtual;
	
	            if (vezJogador1) {
	                jogadorAtual = jogador1;
	            } else {
	                jogadorAtual = jogador2;
	            }
	
	            System.out.println("Vez de: " + jogadorAtual.getNome());
	
	            int linha = lerNumero("Digite a linha (0 a 2): ");
	            int coluna = lerNumero("Digite a coluna (0 a 2): ");
	
	            boolean jogadaValida = tabuleiro.marcar(
	                linha, coluna, jogadorAtual.getSimbolo()
	            );
	
	            if (jogadaValida) {
	                if (tabuleiro.verificarVitoria(jogadorAtual.getSimbolo())) {
	                    tabuleiro.mostrar();
	                    System.out.println(jogadorAtual.getNome() + " venceu!");
	                    break;
	                }
	
	                if (tabuleiro.verificarEmpate()) {
	                    tabuleiro.mostrar();
	                    System.out.println("Empate!");
	                    break;
	                }
	
	                vezJogador1 = !vezJogador1;
	            }
	        }
	
	        System.out.print("Desejam jogar novamente? (s/n): ");
	        resposta = entrada.next();
	
	    } while (resposta.equalsIgnoreCase("s"));
	
	    System.out.println("Obrigado por jogar!");
	}


    private int lerNumero(String mensagem) {
        while (true) {
            System.out.print(mensagem);

            if (entrada.hasNextInt()) {
                return entrada.nextInt();
            }

            System.out.println("Entrada inválida! Digite um número.");
            entrada.next();
        }
    }
}

