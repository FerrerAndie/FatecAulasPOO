package jogo;

import modelo.Jogador;
import modelo.Peca;
import modelo.Posicao;

public class Partida {
    private Tabuleiro tabuleiro;
    private Jogador jogadorBranco;
    private Jogador jogadorPreto;
    private Jogador turnoAtual;
    private String status; // "EM_ANDAMENTO", "VITORIA_BRANCAS", "VITORIA_PRETAS"

    public Partida(String nomeBranco, String nomePreto) {
        jogadorBranco = new Jogador(nomeBranco, true);
        jogadorPreto = new Jogador(nomePreto, false);
        tabuleiro = new Tabuleiro();
        tabuleiro.inicializar(jogadorBranco, jogadorPreto);
        turnoAtual = jogadorBranco;
        status = "EM_ANDAMENTO";
    }

    public void imprimirEstado() {
        System.out.println();
        for (int l = 0; l < 8; l++) {
            StringBuilder linhaStr = new StringBuilder();
            for (int c = 0; c < 8; c++) {
                Peca p = tabuleiro.getPeca(l, c);
                if (p == null) {
                    linhaStr.append(".");
                } else {
                    char simbolo = p.getJogador().isBranca() ? 'b' : 'p';
                    if (p.isDama()) {
                        simbolo = Character.toUpperCase(simbolo);
                    }
                    linhaStr.append(simbolo);
                }
            }
            System.out.println(linhaStr.toString());
        }
        System.out.println();
        System.out.println("TURNO " + (turnoAtual.isBranca() ? "BRANCAS" : "PRETAS"));
        System.out.println("STATUS=" + status);
    }

    public boolean realizarMovimento(Posicao origem, Posicao destino) {
        if (!status.equals("EM_ANDAMENTO")) {
            System.out.println("Movimento rejeitado: A partida já foi encerrada.");
            return false;
        }

        if (!origem.ehValida() || !destino.ehValida()) {
            System.out.println("Coordenada inválida.");
            return false;
        }

        Peca peca = tabuleiro.getPeca(origem);
        if (peca == null) {
            System.out.println("Movimento rejeitado: Origem sem peça.");
            return false;
        }

        if (peca.getJogador() != turnoAtual) {
            System.out.println("Movimento rejeitado: A peça pertence ao adversário.");
            return false;
        }

        if (tabuleiro.getPeca(destino) != null) {
            System.out.println("Movimento rejeitado: Posição de destino ocupada.");
            return false;
        }

        // Validação polimórfica de movimento
        if (peca.movimentoValido(origem, destino, tabuleiro)) {
            tabuleiro.removerPeca(origem);
            tabuleiro.setPeca(destino, peca);

            // Regra de Promoção a Dama (Requisito RF12 / TM14)
            if (peca.getJogador().isBranca() && destino.getLinha() == 8) {
                peca.setDama(true);
            } else if (!peca.getJogador().isBranca() && destino.getLinha() == 1) {
                peca.setDama(true);
            }

            // Alternar turno
            turnoAtual = (turnoAtual == jogadorBranco) ? jogadorPreto : jogadorBranco;
            System.out.println("Movimento realizado com sucesso.");
            return true;
        } else {
            System.out.println("Movimento inválido para esta peça.");
            return false;
        }
    }

    public Jogador getTurnoAtual() {
        return turnoAtual;
    }
    
    public String getStatus() {
        return status;
    }
}
