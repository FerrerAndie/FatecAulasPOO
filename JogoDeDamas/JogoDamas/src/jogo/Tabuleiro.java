package jogo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.Cor;
import modelo.Dama;
import modelo.Peca;
import modelo.PecaComum;
import modelo.Posicao;

/** Consulta publica; alteracoes ficam restritas ao pacote que aplica as regras. */
public final class Tabuleiro {
    private final Peca[][] casas;

    private Tabuleiro() { casas = new Peca[8][8]; }

    static Tabuleiro inicial() {
        Tabuleiro tabuleiro = new Tabuleiro();
        for (int linha = 1; linha <= 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Posicao p = new Posicao(coluna, linha);
                if (p.isEscura() && (linha <= 3 || linha >= 6)) {
                    tabuleiro.colocar(p, new PecaComum(linha <= 3 ? Cor.BRANCAS : Cor.PRETAS));
                }
            }
        }
        return tabuleiro;
    }

    static Tabuleiro deLinhas(List<String> linhas) {
        if (linhas == null || linhas.size() != 8) {
            throw new IllegalArgumentException("O tabuleiro deve conter exatamente oito linhas.");
        }
        Tabuleiro tabuleiro = new Tabuleiro();
        for (int i = 0; i < 8; i++) {
            String linha = linhas.get(i);
            if (linha == null || linha.length() != 8) {
                throw new IllegalArgumentException("Cada linha do tabuleiro deve ter oito caracteres.");
            }
            for (int coluna = 0; coluna < 8; coluna++) {
                char simbolo = linha.charAt(coluna);
                if (simbolo == '.') { continue; }
                Posicao p = new Posicao(coluna, 8 - i);
                if (!p.isEscura()) { throw new IllegalArgumentException("Peca em casa clara: " + p); }
                Peca peca;
                switch (simbolo) {
                    case 'b': peca = new PecaComum(Cor.BRANCAS); break;
                    case 'p': peca = new PecaComum(Cor.PRETAS); break;
                    case 'B': peca = new Dama(Cor.BRANCAS); break;
                    case 'P': peca = new Dama(Cor.PRETAS); break;
                    default: throw new IllegalArgumentException("Simbolo de peca invalido: " + simbolo);
                }
                if (peca instanceof PecaComum && p.getLinha() == peca.getCor().getLinhaPromocao()) {
                    throw new IllegalArgumentException("Peca comum sem promocao na ultima linha: " + p);
                }
                tabuleiro.colocar(p, peca);
            }
        }
        return tabuleiro;
    }

    public Peca obter(Posicao posicao) {
        if (posicao == null) { throw new IllegalArgumentException("Posicao obrigatoria."); }
        return casas[posicao.getLinha() - 1][posicao.getColuna()];
    }

    public int contar(Cor cor) {
        int total = 0;
        for (Peca[] linha : casas) {
            for (Peca peca : linha) {
                if (peca != null && peca.getCor() == cor) { total++; }
            }
        }
        return total;
    }

    public List<String> linhas() {
        List<String> resultado = new ArrayList<String>();
        for (int linha = 8; linha >= 1; linha--) {
            StringBuilder texto = new StringBuilder();
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = casas[linha - 1][coluna];
                texto.append(peca == null ? '.' : peca.getSimbolo());
            }
            resultado.add(texto.toString());
        }
        return Collections.unmodifiableList(resultado);
    }

    Tabuleiro copiar() {
        Tabuleiro copia = new Tabuleiro();
        for (int i = 0; i < 8; i++) { System.arraycopy(casas[i], 0, copia.casas[i], 0, 8); }
        return copia;
    }

    void colocar(Posicao posicao, Peca peca) {
        if (peca != null && !posicao.isEscura()) {
            throw new IllegalArgumentException("Uma peca deve ocupar uma casa escura.");
        }
        casas[posicao.getLinha() - 1][posicao.getColuna()] = peca;
    }

    void deslocar(Movimento movimento) {
        Peca peca = obter(movimento.getOrigem());
        colocar(movimento.getOrigem(), null);
        colocar(movimento.getDestino(), peca);
    }
}
