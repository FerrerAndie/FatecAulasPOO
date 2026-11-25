package aplicacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.Locale;

import jogo.Partida;
import jogo.StatusPartida;
import modelo.Cor;
import modelo.Posicao;
import persistencia.PersistenciaPartida;

/** Interpreta comandos e apresenta resultados; as regras ficam em Partida. */
public final class ConsoleJogo {
    private final BufferedReader entrada;
    private final PrintWriter saida;
    private final PersistenciaPartida persistencia;
    private Partida partida;

    public ConsoleJogo(Reader entrada, Writer saida, PersistenciaPartida persistencia) {
        if (entrada == null || saida == null || persistencia == null) {
            throw new IllegalArgumentException("Informe entrada, saída e persistência.");
        }
        this.entrada = new BufferedReader(entrada);
        this.saida = new PrintWriter(saida, true);
        this.persistencia = persistencia;
    }

    public void executar() {
        saida.println("=== JOGO DE DAMAS ===");
        saida.println("NOVO <brancas> <pretas> | MOVER <origem> <destino> | ESTADO");
        saida.println("SALVAR <arquivo> | CARREGAR <arquivo> | SAIR");
        saida.println("Exemplo: NOVO Ana Pedro. Coordenadas: A1 a H8.");
        boolean continuar = true;
        while (continuar) {
            // Linha separada mantém ESTADO idêntico também com entrada redirecionada.
            saida.println(">");
            String linha;
            try {
                linha = entrada.readLine();
            } catch (IOException e) {
                saida.println("Erro ao ler a entrada: " + e.getMessage());
                break;
            }
            if (linha == null) {
                break;
            }
            if (linha.trim().isEmpty()) {
                continue;
            }
            try {
                continuar = executarComando(linha.trim());
            } catch (IllegalArgumentException e) {
                saida.println("Erro: " + e.getMessage());
            } catch (IllegalStateException e) {
                saida.println("Erro: " + e.getMessage());
            } catch (IOException e) {
                saida.println("Erro: falha de arquivo: " + e.getMessage());
            }
        }
        saida.println("Programa encerrado.");
    }

    private boolean executarComando(String linha) throws IOException {
        String[] partes = linha.split("\\s+", 2);
        String comando = partes[0].toUpperCase(Locale.ROOT);
        String argumentos = partes.length == 2 ? partes[1].trim() : "";
        switch (comando) {
        case "NOVO":
            String[] nomes = doisArgumentos(argumentos, "NOVO <brancas> <pretas>");
            partida = Partida.nova(nomes[0], nomes[1]);
            saida.println("Partida iniciada.");
            mostrarTabuleiro();
            informarSituacao();
            break;
        case "MOVER":
            exigirPartida();
            String[] casas = doisArgumentos(argumentos, "MOVER <origem> <destino>");
            partida.mover(Posicao.de(casas[0]), Posicao.de(casas[1]));
            saida.println("Movimento realizado.");
            mostrarTabuleiro();
            informarSituacao();
            break;
        case "ESTADO":
            semArgumentos(argumentos, "ESTADO");
            exigirPartida();
            mostrarEstado();
            break;
        case "SALVAR":
            exigirPartida();
            exigirArquivo(argumentos, "SALVAR <arquivo>");
            persistencia.salvar(partida, Paths.get(argumentos));
            saida.println("Partida salva com sucesso.");
            break;
        case "CARREGAR":
            exigirArquivo(argumentos, "CARREGAR <arquivo>");
            // A atribuição ocorre somente após leitura e validação completas.
            Partida carregada = persistencia.carregar(Paths.get(argumentos));
            partida = carregada;
            saida.println("Partida carregada com sucesso.");
            mostrarTabuleiro();
            informarSituacao();
            break;
        case "SAIR":
            semArgumentos(argumentos, "SAIR");
            return false;
        default:
            throw new IllegalArgumentException("Comando desconhecido: " + partes[0] + ".");
        }
        return true;
    }

    private String[] doisArgumentos(String argumentos, String uso) {
        String[] itens = argumentos.split("\\s+");
        if (argumentos.isEmpty() || itens.length != 2) {
            throw new IllegalArgumentException("Use: " + uso + ".");
        }
        return itens;
    }

    private void semArgumentos(String argumentos, String uso) {
        if (!argumentos.isEmpty()) {
            throw new IllegalArgumentException("Use apenas: " + uso + ".");
        }
    }

    private void exigirArquivo(String argumentos, String uso) {
        if (argumentos.isEmpty()) {
            throw new IllegalArgumentException("Use: " + uso + ".");
        }
    }

    private void exigirPartida() {
        if (partida == null) {
            throw new IllegalStateException("Inicie com NOVO ou CARREGAR antes deste comando.");
        }
    }

    private void mostrarEstado() {
        saida.println("TABULEIRO_INICIO");
        for (String linha : partida.getTabuleiro().linhas()) {
            saida.println(linha);
        }
        saida.println("TABULEIRO_FIM");
        saida.println("TURNO=" + partida.getTurno().name());
        saida.println("STATUS=" + partida.getStatus().name());
    }

    private void mostrarTabuleiro() {
        saida.println("    A B C D E F G H");
        int numero = 8;
        for (String linha : partida.getTabuleiro().linhas()) {
            saida.print(numero + " | ");
            for (int coluna = 0; coluna < 8; coluna++) {
                saida.print(linha.charAt(coluna) + " ");
            }
            saida.println("| " + numero);
            numero--;
        }
        saida.println("    A B C D E F G H");
        saida.println("b/p: peças comuns; B/P: damas; .: casa vazia.");
    }

    private void informarSituacao() {
        if (partida.getStatus() != StatusPartida.EM_ANDAMENTO) {
            Cor vencedora = partida.getStatus() == StatusPartida.VITORIA_BRANCAS
                    ? Cor.BRANCAS : Cor.PRETAS;
            saida.println("Vencedor: " + partida.getJogador(vencedora).getNome()
                    + " (" + nomeCor(vencedora) + ").");
        } else if (partida.isCapturaEmAndamento()) {
            saida.println("Continue a captura com a mesma peça em "
                    + partida.getPosicaoEmSequencia() + ".");
            saida.println("As peças capturadas ainda bloqueiam suas casas até o fim da sequência.");
        } else {
            Cor turno = partida.getTurno();
            saida.println("Jogador atual: " + partida.getJogador(turno).getNome()
                    + " (" + nomeCor(turno) + ").");
        }
    }

    private String nomeCor(Cor cor) {
        return cor == Cor.BRANCAS ? "Brancas" : "Pretas";
    }
}
