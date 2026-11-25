package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import jogo.Movimento;
import jogo.Tabuleiro;

public final class PecaComum extends Peca {
    public PecaComum(Cor cor) { super(cor); }

    @Override public char getSimbolo() { return getCor() == Cor.BRANCAS ? 'b' : 'p'; }

    @Override public List<Movimento> movimentosSemCaptura(Tabuleiro tabuleiro, Posicao origem) {
        List<Movimento> movimentos = new ArrayList<Movimento>();
        for (int dc : new int[] {-1, 1}) {
            Posicao destino = origem.deslocar(dc, getCor().getDirecao());
            if (destino != null && tabuleiro.obter(destino) == null) {
                movimentos.add(new Movimento(origem, destino, null));
            }
        }
        return movimentos;
    }

    @Override public List<Movimento> capturas(Tabuleiro tabuleiro, Posicao origem,
                                              Set<Posicao> jaCapturadas) {
        List<Movimento> movimentos = new ArrayList<Movimento>();
        for (int[] d : diagonais()) {
            Posicao meio = origem.deslocar(d[0], d[1]);
            Posicao destino = origem.deslocar(2 * d[0], 2 * d[1]);
            if (meio == null || destino == null || jaCapturadas.contains(meio)) { continue; }
            Peca adversaria = tabuleiro.obter(meio);
            if (adversaria != null && adversaria.getCor() != getCor()
                    && tabuleiro.obter(destino) == null) {
                movimentos.add(new Movimento(origem, destino, meio));
            }
        }
        return movimentos;
    }
}
