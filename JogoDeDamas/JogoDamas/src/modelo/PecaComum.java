package modelo;


import jogo.Tabuleiro;


public class PecaComum extends Peca {


    public PecaComum(Jogador jogador) {
        super(jogador);
    }


    @Override
    public boolean movimentoValido(Posicao origem, Posicao destino, Tabuleiro tabuleiro) {
        // Validação básica de distância para peça comum (1 casa na diagonal, ou 2 para captura)
        int dCol = Math.abs(origem.getColunaIndex() - destino.getColunaIndex());
        int dLin = Math.abs(origem.getLinhaIndex() - destino.getLinhaIndex());


        // Movimento simples de 1 casa
        if (dCol == 1 && dLin == 1) {
            // Verificar direção correta (Brancas sobem, Pretas descem)
            int direcao = (getJogador().isBranca()) ? -1 : 1; // Ajustado para índices da matriz
            // No tabuleiro visual: Linha 1 é embaixo (índice 7), Linha 8 é em cima (índice 0)
            // Brancas (começam embaixo) avançam para índices menores na matriz.
            // Pretas (começam em cima) avançam para índices maiores na matriz.
            int diffLinhaIndex = destino.getLinhaIndex() - origem.getLinhaIndex();
            
            if (getJogador().isBranca() && diffLinhaIndex == -1) return true;
            if (!getJogador().isBranca() && diffLinhaIndex == 1) return true;
        }


        return false;
    }
}
