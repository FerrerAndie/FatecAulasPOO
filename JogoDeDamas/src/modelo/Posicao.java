package modelo;


public class Posicao {
    private char coluna; // 'A' até 'H'
    private int linha;   // 1 até 8


    public Posicao(char coluna, int linha) {
        this.coluna = Character.toUpperCase(coluna);
        this.linha = linha;
    }


    public char getColuna() {
        return coluna;
    }


    public int getLinha() {
        return linha;
    }


    // Converte coluna 'A'-'H' para índice de matriz 0-7
    public int getColunaIndex() {
        return coluna - 'A';
    }


    // Converte linha 1-8 para índice de matriz 0-7 (invertendo para o display correto)
    public int getLinhaIndex() {
        return 8 - linha;
    }


    public static Posicao fromIndices(int linhaIndex, int colunaIndex) {
        char col = (char) ('A' + colunaIndex);
        int lin = 8 - linhaIndex;
        return new Posicao(col, lin);
    }


    public boolean ehValida() {
        return coluna >= 'A' && coluna <= 'H' && linha >= 1 && linha <= 8;
    }


    @Override
    public String toString() {
        return "" + coluna + linha;
    }
}
