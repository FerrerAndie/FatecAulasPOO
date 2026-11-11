
/*
 
Jogo da velha
 
Requisitos obrigatórios
 
O programa deverá:
 
1. Representar o tabuleiro utilizando uma matriz 3 × 3.
 
2. Exibir o tabuleiro atualizado antes de cada jogada.
 
3. Solicitar ao jogador da vez a linha e a coluna em que deseja jogar.
 
4. Permitir apenas posições entre 1 e 3.
 
5. Verificar se a posição escolhida está disponível.
 
6. Não permitir que uma posição ocupada seja sobrescrita.
 
7. Alternar corretamente entre os jogadores X e O.
 
8. Verificar, depois de cada jogada, se houve vitória.
 
9. Considerar como vitória o preenchimento de:
 
uma linha completa;
 
uma coluna completa;
 
a diagonal principal;
 
a diagonal secundária.
 
10. Encerrar a partida quando:
 
um jogador vencer; ou
 
todas as posições estiverem ocupadas, resultando em empate.
 
11. Exibir o tabuleiro final e informar o resultado da partida.
 
*/

import java.util.Scanner;

public class JogoDaVelha {

	static char[][] tabuleiro = {
			{' ', ' ', ' '},
			{' ', ' ', ' '},
			{' ', ' ', ' '}
	};

	// inicio do programa
	public static void main(String[] args) {

		char jogadorAtual = 'X';
		boolean jogoAtivo = true;

		Scanner ler = new Scanner(System.in);

		// inicio do jogo
		while (jogoAtivo) {

			exibirTabuleiro();

			System.out.println("Jogador " + jogadorAtual + ", escolha uma linha (1-3)");
			int linha = ler.nextInt();

			System.out.println("Jogador " + jogadorAtual + ", escolha uma coluna (1-3)");
			int coluna = ler.nextInt();

			if (linha < 1 || linha > 3 || coluna < 1 || coluna > 3) {
			    System.out.println("Linha e coluna devem estar entre 1 e 3. Tente novamente.");
			    continue;
			}

			// se i,j estiverem vazios preencher com valor lido do jogador atual
			if (tabuleiro[linha - 1][coluna - 1] == ' ') {

				tabuleiro[linha - 1][coluna - 1] = jogadorAtual;

				// Se checar vitória = verdadeiro, exibe mensagem de vitoria e para o looping.
				if (checarVitoria(jogadorAtual)) {

					exibirTabuleiro();
					System.out.println("Parabéns, Jogador " + jogadorAtual + "! Você venceu!");
					jogoAtivo = false; // variavel controla loop

					// Se não, exibe mensagem de empate e para o looping.
				} else if (checarEmpate()) {

					exibirTabuleiro();
					System.out.println("Empate!");
					jogoAtivo = false; // variavel controla loop

					// Se não empatou nem ganhou, alterna para outro jogador.
				} else {

					jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X'; // alterna jogadores
				}

				// Caso a opção i,j já tenha valor, repete loop com o mesmo jogador.
			} else {

				System.out.println("Posição já ocupada, tente novamente");
				continue;
			}
		}

		ler.close();
	}

	// void = executa ação, e encerra tarefa

	// Método exibir tabuleiro percorre as linhas e colunas e exibe: |
	static void exibirTabuleiro() {

		for (char[] linha : tabuleiro) { // para cada array do tipo char chamado de linha dentro do tabuleiro

			for (char coluna : linha) { // para cada valor chamado de coluna dentro da linha

				System.out.print("|" + coluna);
			}

			System.out.println("|");
		}
	}

	// Método checar vitoria percorre as linhas e colunas e verifica se os valores são iguais (X ou O).
	// Se caso forem, exibe mensagem que o jogador venceu
	static boolean checarVitoria(char jogador) {

		// verificar linhas
		for (int i = 0; i < 3; i++) {

			if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {

				System.out.println("Jogador " + jogador + " venceu!");
				return true;
			}
		}

		// verificar colunas
		for (int i = 0; i < 3; i++) {

			if (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador) {

				System.out.println("Jogador " + jogador + " venceu!");
				return true;
			}
		}

		// verificar diagonais
		if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {

			System.out.println("Jogador " + jogador + " venceu!");
			return true;
		}

		if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) {

			System.out.println("Jogador " + jogador + " venceu!");
			return true;
		}

		return false;
	}

	// Método checar empate percorre as linhas e colunas e verifica se os valores estão vazios,
	// se falso não houve empate. Se positivo, exibe a mensagem que houve empate.

	// Verificar se houve empate
	static boolean checarEmpate() {

		for (char[] linha : tabuleiro) {

			for (char coluna : linha) {

				if (coluna == ' ') {

					return false;
				}
			}
		}

		return true;
	}
}
