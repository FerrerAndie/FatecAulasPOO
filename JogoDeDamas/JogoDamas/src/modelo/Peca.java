package modelo;

import java.util.List;
import java.util.Set;
import jogo.Movimento;
import jogo.Tabuleiro;

/** Abstracao compartilhada: toda peca tem cor, mas cada tipo se move de um jeito. */
public abstract class Peca {
    private final Cor cor;

    protected Peca(Cor cor) {
        if (cor == null) { throw new IllegalArgumentException("Cor obrigatoria."); }
        this.cor = cor;
    }

    public final Cor getCor() { return cor; }

    /** Cada consulta recebe seus valores, sem expor um array compartilhado mutavel. */
    protected final int[][] diagonais() {
        return new int[][] {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    }

    public abstract char getSimbolo();
    public abstract List<Movimento> movimentosSemCaptura(Tabuleiro tabuleiro, Posicao origem);
    public abstract List<Movimento> capturas(Tabuleiro tabuleiro, Posicao origem,
                                            Set<Posicao> jaCapturadas);
}
