package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import jogo.Movimento;
import jogo.Tabuleiro;

public final class Dama extends Peca {
    public Dama(Cor cor) { super(cor); }

    @Override public char getSimbolo() { return getCor() == Cor.BRANCAS ? 'B' : 'P'; }

    @Override public List<Movimento> movimentosSemCaptura(Tabuleiro tabuleiro, Posicao origem) {
        List<Movimento> movimentos = new ArrayList<Movimento>();
        for (int[] d : diagonais()) {
            Posicao destino = origem.deslocar(d[0], d[1]);
            while (destino != null && tabuleiro.obter(destino) == null) {
                movimentos.add(new Movimento(origem, destino, null));
                destino = destino.deslocar(d[0], d[1]);
            }
        }
        return movimentos;
    }

    @Override public List<Movimento> capturas(Tabuleiro tabuleiro, Posicao origem,
                                              Set<Posicao> jaCapturadas) {
        List<Movimento> movimentos = new ArrayList<Movimento>();
        for (int[] d : diagonais()) {
            Posicao cursor = origem.deslocar(d[0], d[1]);
            // Procura a primeira peca desta diagonal. Nao atravessa bloqueios.
            while (cursor != null && tabuleiro.obter(cursor) == null) {
                cursor = cursor.deslocar(d[0], d[1]);
            }
            if (cursor == null || jaCapturadas.contains(cursor)
                    || tabuleiro.obter(cursor).getCor() == getCor()) { continue; }
            Posicao capturada = cursor;
            cursor = cursor.deslocar(d[0], d[1]);
            // Cada casa vazia alem da adversaria e uma aterrissagem candidata.
            while (cursor != null && tabuleiro.obter(cursor) == null) {
                movimentos.add(new Movimento(origem, cursor, capturada));
                cursor = cursor.deslocar(d[0], d[1]);
            }
        }
        return movimentos;
    }
}
