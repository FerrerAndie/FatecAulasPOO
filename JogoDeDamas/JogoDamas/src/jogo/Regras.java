package jogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import modelo.Cor;
import modelo.Peca;
import modelo.Posicao;

/** Gera somente etapas que ainda podem completar a maior captura possivel. */
final class Regras {
    List<Movimento> legais(Tabuleiro tabuleiro, Cor cor, Posicao continuacao,
                            Set<Posicao> capturadas) {
        List<Movimento> candidatas = new ArrayList<Movimento>();
        List<Movimento> melhores = new ArrayList<Movimento>();
        List<Posicao> origens = origens(tabuleiro, cor, continuacao);
        for (Posicao origem : origens) {
            Peca peca = tabuleiro.obter(origem);
            // Despacho polimorfico: comum e dama produzem capturas diferentes.
            candidatas.addAll(peca.capturas(tabuleiro, origem, capturadas));
        }
        int maior = 0;
        Map<String, Integer> memoria = new HashMap<String, Integer>();
        for (Movimento movimento : candidatas) {
            int quantidade = avaliarEtapa(tabuleiro, movimento, capturadas, memoria);
            if (quantidade > maior) {
                maior = quantidade;
                melhores.clear();
            }
            if (quantidade == maior) { melhores.add(movimento); }
        }
        if (maior > 0 || continuacao != null) { return melhores; }
        for (Posicao origem : origens) {
            melhores.addAll(tabuleiro.obter(origem).movimentosSemCaptura(tabuleiro, origem));
        }
        return melhores;
    }

    private List<Posicao> origens(Tabuleiro tabuleiro, Cor cor, Posicao continuacao) {
        List<Posicao> resultado = new ArrayList<Posicao>();
        if (continuacao != null) {
            resultado.add(continuacao);
            return resultado;
        }
        for (int linha = 1; linha <= 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Posicao origem = new Posicao(coluna, linha);
                Peca peca = tabuleiro.obter(origem);
                if (peca != null && peca.getCor() == cor) { resultado.add(origem); }
            }
        }
        return resultado;
    }

    private int avaliarEtapa(Tabuleiro tabuleiro, Movimento movimento,
                              Set<Posicao> capturadas, Map<String, Integer> memoria) {
        Tabuleiro simulacao = tabuleiro.copiar();
        simulacao.deslocar(movimento);
        Set<Posicao> novasCapturadas = new HashSet<Posicao>(capturadas);
        novasCapturadas.add(movimento.getCapturada());
        // As capturadas NAO sao removidas: continuam bloqueando as diagonais.
        // A peca tambem NAO e promovida durante esta simulacao de sequencia.
        return 1 + maximoRestante(simulacao, movimento.getDestino(), novasCapturadas, memoria);
    }

    private int maximoRestante(Tabuleiro tabuleiro, Posicao origem,
                                Set<Posicao> capturadas, Map<String, Integer> memoria) {
        long mascara = 0;
        for (Posicao p : capturadas) { mascara |= 1L << p.hashCode(); }
        String chave = tabuleiro.linhas().toString() + origem + ":" + mascara;
        Integer calculado = memoria.get(chave);
        if (calculado != null) { return calculado; }
        int maior = 0;
        for (Movimento movimento : tabuleiro.obter(origem).capturas(tabuleiro, origem, capturadas)) {
            maior = Math.max(maior, avaliarEtapa(tabuleiro, movimento, capturadas, memoria));
        }
        memoria.put(chave, maior);
        return maior;
    }
}
