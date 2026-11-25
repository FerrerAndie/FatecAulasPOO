package modelo;

/** Direcao e linha de promocao de cada lado do tabuleiro. */
public enum Cor {
    BRANCAS(1, 8), PRETAS(-1, 1);

    private final int direcao;
    private final int linhaPromocao;

    Cor(int direcao, int linhaPromocao) {
        this.direcao = direcao;
        this.linhaPromocao = linhaPromocao;
    }

    public int getDirecao() { return direcao; }
    public int getLinhaPromocao() { return linhaPromocao; }
    public Cor oposta() { return this == BRANCAS ? PRETAS : BRANCAS; }
}
