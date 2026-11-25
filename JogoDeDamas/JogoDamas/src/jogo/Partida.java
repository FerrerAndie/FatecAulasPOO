package jogo;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import modelo.Cor;
import modelo.Dama;
import modelo.Jogador;
import modelo.Peca;
import modelo.PecaComum;
import modelo.Posicao;

/** Coordena uma partida; nenhuma alteracao publica ignora a validacao das regras. */
public final class Partida {
    private final Jogador brancas;
    private final Jogador pretas;
    private final Tabuleiro tabuleiro;
    private final Regras regras;
    private final Set<Posicao> capturadas;
    private Cor turno;
    private StatusPartida status;
    private Posicao posicaoEmSequencia;

    private Partida(String nomeBrancas, String nomePretas, Tabuleiro tabuleiro, Cor turno) {
        this.brancas = new Jogador(nomeBrancas, Cor.BRANCAS);
        this.pretas = new Jogador(nomePretas, Cor.PRETAS);
        if (turno == null) { throw new IllegalArgumentException("Turno obrigatorio."); }
        this.tabuleiro = tabuleiro;
        this.turno = turno;
        this.status = StatusPartida.EM_ANDAMENTO;
        this.regras = new Regras();
        this.capturadas = new LinkedHashSet<Posicao>();
    }

    public static Partida nova(String nomeBrancas, String nomePretas) {
        return new Partida(nomeBrancas, nomePretas, Tabuleiro.inicial(), Cor.BRANCAS);
    }

    /** Constroi e valida isoladamente; a interface so troca a partida apos o retorno. */
    public static Partida restaurar(String nomeBrancas, String nomePretas, Cor turno,
                                     StatusPartida statusSalvo, List<String> linhas) {
        if (statusSalvo == null) { throw new IllegalArgumentException("Status obrigatorio."); }
        Tabuleiro tabuleiro = Tabuleiro.deLinhas(linhas);
        int brancas = tabuleiro.contar(Cor.BRANCAS);
        int pretas = tabuleiro.contar(Cor.PRETAS);
        if (brancas > 12 || pretas > 12 || brancas + pretas == 0) {
            throw new IllegalArgumentException("Quantidade de pecas inconsistente.");
        }
        Partida partida = new Partida(nomeBrancas, nomePretas, tabuleiro, turno);
        partida.atualizarStatus();
        // Um cenario EM_ANDAMENTO ja sem movimentos deve detectar a vitoria ao carregar.
        if (statusSalvo != StatusPartida.EM_ANDAMENTO && statusSalvo != partida.status) {
            throw new IllegalArgumentException("Status salvo contradiz a situacao do tabuleiro.");
        }
        return partida;
    }

    public Cor getTurno() { return turno; }
    public StatusPartida getStatus() { return status; }
    public Jogador getJogador(Cor cor) {
        if (cor == null) { throw new IllegalArgumentException("Cor obrigatoria."); }
        return cor == Cor.BRANCAS ? brancas : pretas;
    }
    public Tabuleiro getTabuleiro() { return tabuleiro; }
    public boolean isCapturaEmAndamento() { return posicaoEmSequencia != null; }
    public Posicao getPosicaoEmSequencia() { return posicaoEmSequencia; }

    public void mover(Posicao origem, Posicao destino) {
        if (status != StatusPartida.EM_ANDAMENTO) {
            throw new IllegalStateException("A partida terminou. Use NOVO para iniciar outra.");
        }
        if (origem == null || destino == null) {
            throw new IllegalArgumentException("Informe as coordenadas de origem e destino.");
        }
        if (!origem.isEscura() || !destino.isEscura()) {
            throw new IllegalArgumentException("Somente casas escuras podem ser usadas.");
        }
        Peca peca = tabuleiro.obter(origem);
        if (peca == null) { throw new IllegalArgumentException("A origem nao possui peca."); }
        if (peca.getCor() != turno) { throw new IllegalArgumentException("Esta peca pertence ao adversario."); }
        if (posicaoEmSequencia != null && !posicaoEmSequencia.equals(origem)) {
            throw new IllegalArgumentException("Continue a captura com a mesma peca em " + posicaoEmSequencia + ".");
        }
        if (tabuleiro.obter(destino) != null) { throw new IllegalArgumentException("O destino esta ocupado."); }
        List<Movimento> legais = regras.legais(tabuleiro, turno, posicaoEmSequencia, capturadas);
        Movimento escolhido = null;
        for (Movimento movimento : legais) {
            if (movimento.getOrigem().equals(origem) && movimento.getDestino().equals(destino)) {
                escolhido = movimento;
                break;
            }
        }
        if (escolhido == null) {
            if (!legais.isEmpty() && legais.get(0).isCaptura()) {
                throw new IllegalArgumentException("Captura obrigatoria: escolha uma sequencia com o maior numero de capturas.");
            }
            throw new IllegalArgumentException("Movimento invalido para esta peca.");
        }
        tabuleiro.deslocar(escolhido);
        if (escolhido.isCaptura()) {
            capturadas.add(escolhido.getCapturada());
            posicaoEmSequencia = destino;
            if (!regras.legais(tabuleiro, turno, posicaoEmSequencia, capturadas).isEmpty()) { return; }
        }
        concluirJogada(destino);
    }

    private void concluirJogada(Posicao destino) {
        for (Posicao posicao : capturadas) { tabuleiro.colocar(posicao, null); }
        capturadas.clear();
        posicaoEmSequencia = null;
        Peca peca = tabuleiro.obter(destino);
        if (peca instanceof PecaComum && destino.getLinha() == turno.getLinhaPromocao()) {
            tabuleiro.colocar(destino, new Dama(turno));
        }
        turno = turno.oposta();
        atualizarStatus();
    }

    private void atualizarStatus() {
        if (tabuleiro.contar(Cor.BRANCAS) == 0) {
            status = StatusPartida.VITORIA_PRETAS;
        } else if (tabuleiro.contar(Cor.PRETAS) == 0) {
            status = StatusPartida.VITORIA_BRANCAS;
        } else if (regras.legais(tabuleiro, turno, null, Collections.<Posicao>emptySet()).isEmpty()) {
            status = turno == Cor.BRANCAS ? StatusPartida.VITORIA_PRETAS : StatusPartida.VITORIA_BRANCAS;
        } else {
            status = StatusPartida.EM_ANDAMENTO;
        }
    }
}
