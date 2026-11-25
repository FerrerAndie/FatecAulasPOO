package jogo;

import modelo.Peca;
import modelo.PecaComum;
import modelo.Posicao;
import modelo.Jogador;

public class Tabuleiro {
    private Peca[][] pecas;

    public Tabuleiro() {
        pecas = new Peca[8][8];
    }

    public Peca getPeca(Posicao pos) {
        return pecas[pos.getLinhaIndex()][pos.getColunaIndex()];
    }

    public Peca getPeca(int linhaIndex, int colunaIndex) {
        return pecas[linhaIndex][colunaIndex];
    }

    public void setPeca(Posicao pos, Peca peca) {
        pecas[pos.getLinhaIndex()][pos.getColunaIndex()] = peca;
    }

    public void removerPeca(Posicao pos) {
        pecas[pos.getLinhaIndex()][pos.getColunaIndex()] = null;
    }

    public void inicializar(Jogador jogadorBranco, Jogador jogadorPreto) {
        // Peças pretas nas linhas 6, 7 e 8 (índices 0, 1, 2)
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 8; c++) {
                if ((l + c) % 2 != 0) { // Casas escuras
                    pecas[l][c] = new PecaComum(jogadorPreto);
                }
            }
        }

        // Peças brancas nas linhas 1, 2 e 3 (índices 5, 6, 7)
        for (int l = 5; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                if ((l + c) % 2 != 0) { // Casas escuras
                    pecas[l][c] = new PecaComum(jogadorBranco);
                }
            }
        }
    }
}
