package modelo;

import java.util.List;
import jogo.Tabuleiro;

public abstract class Peca {
    private Jogador jogador;
    private boolean dama;

    public Peca(Jogador jogador) {
        this.jogador = jogador;
        this.dama = false;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public boolean isDama() {
        return dama;
    }

    public void setDama(boolean dama) {
        this.dama = dama;
    }

    // Método abstrato exigido para aplicar polimorfismo nos movimentos
    public abstract boolean movimentoValido(Posicao origem, Posicao destino, Tabuleiro tabuleiro);
}

