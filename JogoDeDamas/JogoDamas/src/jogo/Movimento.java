package jogo;

import modelo.Posicao;

/** Uma unica etapa; capturada e null em deslocamentos sem captura. */
public final class Movimento {
    private final Posicao origem;
    private final Posicao destino;
    private final Posicao capturada;

    public Movimento(Posicao origem, Posicao destino, Posicao capturada) {
        if (origem == null || destino == null) {
            throw new IllegalArgumentException("Origem e destino obrigatorios.");
        }
        this.origem = origem;
        this.destino = destino;
        this.capturada = capturada;
    }

    public Posicao getOrigem() { return origem; }
    public Posicao getDestino() { return destino; }
    public Posicao getCapturada() { return capturada; }
    public boolean isCaptura() { return capturada != null; }
}
