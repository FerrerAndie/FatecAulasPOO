package modelo;

import java.util.Locale;

/** Valor imutavel: coluna de 0 a 7, linha de 1 a 8. A1 e escura. */
public final class Posicao {
    private final int coluna;
    private final int linha;

    public Posicao(int coluna, int linha) {
        if (coluna < 0 || coluna > 7 || linha < 1 || linha > 8) {
            throw new IllegalArgumentException("Coordenada invalida: use A1 ate H8.");
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    public static Posicao de(String texto) {
        if (texto == null || !texto.matches("[a-hA-H][1-8]")) {
            throw new IllegalArgumentException("Coordenada invalida: use A1 ate H8.");
        }
        String normalizado = texto.toUpperCase(Locale.ROOT);
        return new Posicao(normalizado.charAt(0) - 'A', normalizado.charAt(1) - '0');
    }

    public int getColuna() { return coluna; }
    public int getLinha() { return linha; }
    public boolean isEscura() { return (coluna + linha) % 2 == 1; }

    /** Retorna null quando o deslocamento sair do tabuleiro. */
    public Posicao deslocar(int colunas, int linhas) {
        int c = coluna + colunas;
        int l = linha + linhas;
        return c < 0 || c > 7 || l < 1 || l > 8 ? null : new Posicao(c, l);
    }

    @Override public boolean equals(Object outro) {
        if (!(outro instanceof Posicao)) { return false; }
        Posicao p = (Posicao) outro;
        return coluna == p.coluna && linha == p.linha;
    }

    @Override public int hashCode() { return (linha - 1) * 8 + coluna; }
    @Override public String toString() { return "" + (char) ('A' + coluna) + linha; }
}
