package modelo;


import jogo.Tabuleiro;


public class Dama extends Peca {


    public Dama(Jogador jogador) {
        super(jogador);
        setDama(true);
    }


    @Override
    public boolean movimentoValido(Posicao origem, Posicao destino, Tabuleiro tabuleiro) {
        int dCol = Math.abs(origem.getColunaIndex() - destino.getColunaIndex());
        int dLin = Math.abs(origem.getLinhaIndex() - destino.getLinhaIndex());


        // A dama se movimenta em diagonal a qualquer distância
        if (dCol == dLin && dCol > 0) {
            // Aqui você validará se o caminho está livre de barreiras
            return true;
        }
        return false;
    }
}

