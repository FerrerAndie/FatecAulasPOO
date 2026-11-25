package testes;

import aplicacao.ConsoleJogo;
import jogo.Partida;
import jogo.StatusPartida;
import modelo.Cor;
import modelo.Peca;
import modelo.Posicao;
import persistencia.PersistenciaArquivo;
import persistencia.PersistenciaPartida;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Testes sem JUnit: cada verificação lança AssertionError em caso de divergência. */
public final class TestesJogo {
    private interface Caso { void executar() throws Exception; }
    private static int aprovados;
    private static int reprovados;
    private static Path temporarios;
    private static final PersistenciaPartida ARQUIVOS = new PersistenciaArquivo();
    private static final String INICIAL = ".p.p.p.p\np.p.p.p.\n.p.p.p.p\n........\n........\nb.b.b.b.\n.b.b.b.b\nb.b.b.b.";

    public static void main(String[] args) throws Exception {
        temporarios = Files.createTempDirectory("damas-testes-");
        testar("TM01", () -> {
            Partida p = Partida.nova("Ana", "Pedro");
            igual(INICIAL, tabuleiro(p)); igual(Cor.BRANCAS, p.getTurno());
            igual(StatusPartida.EM_ANDAMENTO, p.getStatus());
            igual(12, contar(p, 'b')); igual(12, contar(p, 'p'));
            String out = console("NOVO Ana Pedro\nESTADO\nSAIR\n");
            igual(bloco(p), ultimoBloco(out));
        });
        testar("TM02", () -> {
            Partida p = Partida.nova("Ana", "Pedro"); mover(p, "A3", "B4");
            peca(p, "A3", '.'); peca(p, "B4", 'b'); igual(Cor.PRETAS, p.getTurno());
        });
        testar("TM03", () -> rejeitaSemAlterar(Partida.nova("Ana", "Pedro"), "A3", "C3"));
        testar("TM04", () -> rejeitaSemAlterar(Partida.nova("Ana", "Pedro"), "A3", "C1"));
        testar("TM05", () -> rejeitaSemAlterar(Partida.nova("Ana", "Pedro"), "B6", "A5"));
        testar("TM06", () -> {
            Partida p = Partida.nova("Ana", "Pedro");
            String out = console("NOVO Ana Pedro\nMOVER A3 Z9\nESTADO\nSAIR\n");
            igual(bloco(p), ultimoBloco(out)); contem(out, "Erro:");
        });
        testar("TM07", () -> {
            Partida p = Partida.nova("Ana", "Pedro"); mover(p, "A3", "B4"); mover(p, "B6", "A5");
            peca(p, "B4", 'b'); peca(p, "A5", 'p'); igual(Cor.BRANCAS, p.getTurno());
        });
        testar("TM08", () -> {
            Partida p = Partida.nova("Ana", "Pedro"); mover(p, "A3", "B4");
            rejeitaSemAlterar(p, "C3", "D4"); igual(Cor.PRETAS, p.getTurno());
        });
        testar("TM09", () -> {
            Partida p = carregar("tm09-captura-simples.txt"); mover(p, "C3", "E5");
            peca(p, "D4", '.'); peca(p, "E5", 'b'); igual(Cor.PRETAS, p.getTurno());
            igual(StatusPartida.EM_ANDAMENTO, p.getStatus());
        });
        testar("TM10", () -> rejeitaSemAlterar(carregar("tm10-captura-obrigatoria.txt"), "C3", "B4"));
        testar("TM11", () -> {
            Partida p = carregar("tm11-captura-multipla.txt"); mover(p, "C3", "E5");
            igual(Cor.BRANCAS, p.getTurno()); verdade(p.isCapturaEmAndamento(), "Sequência deve continuar");
            peca(p, "D4", 'p'); igual(Posicao.de("E5"), p.getPosicaoEmSequencia());
            mover(p, "E5", "G7"); peca(p, "D4", '.'); peca(p, "F6", '.'); peca(p, "G7", 'b');
            igual(Cor.PRETAS, p.getTurno()); verdade(!p.isCapturaEmAndamento(), "Sequência deve terminar");
        });
        testar("TM12", () -> {
            Partida p = carregar("tm12-captura-tras.txt"); mover(p, "E5", "C3");
            peca(p, "C3", 'b'); peca(p, "D4", '.'); igual(Cor.PRETAS, p.getTurno());
        });
        testar("TM13", () -> {
            Partida p = carregar("tm13-maior-captura.txt"); rejeitaSemAlterar(p, "C3", "A5");
            mover(p, "C3", "E5"); igual(Cor.BRANCAS, p.getTurno()); mover(p, "E5", "G7");
            peca(p, "G7", 'b'); peca(p, "B4", 'p'); igual(Cor.PRETAS, p.getTurno());
        });
        testar("TM14", () -> {
            Partida p = carregar("tm14-promocao.txt"); mover(p, "A7", "B8"); peca(p, "B8", 'B');
            igual(Cor.PRETAS, p.getTurno());
        });
        testar("TM15", () -> {
            Partida p = carregar("tm15-dama-longa.txt"); mover(p, "C3", "F6"); peca(p, "C3", '.'); peca(p, "F6", 'B');
        });
        testar("TM16", () -> {
            Partida p = carregar("tm16-dama-tras.txt"); mover(p, "F6", "C3"); peca(p, "F6", '.'); peca(p, "C3", 'B');
        });
        testar("TM17", () -> rejeitaSemAlterar(carregar("tm17-dama-bloqueada.txt"), "C3", "F6"));
        testar("TM18", () -> {
            Partida p = carregar("tm18-eliminacao.txt"); mover(p, "C3", "E5");
            igual(StatusPartida.VITORIA_BRANCAS, p.getStatus()); igual(0, contar(p, 'p'));
            rejeitaSemAlterar(p, "E5", "F6");
        });
        testar("TM19", () -> {
            Partida p = carregar("tm19-sem-movimentos.txt"); igual(StatusPartida.VITORIA_PRETAS, p.getStatus());
            peca(p, "A7", 'b'); rejeitaSemAlterar(p, "A7", "B8");
        });
        testar("TM20", () -> {
            Partida p = Partida.nova("Ana", "Pedro"); mover(p, "A3", "B4");
            Path arquivo = temporarios.resolve("tm20.txt"); ARQUIVOS.salvar(p, arquivo);
            verdade(Files.isRegularFile(arquivo), "Arquivo deve existir");
            String texto = new String(Files.readAllBytes(arquivo), StandardCharsets.UTF_8);
            contem(texto, "BRANCAS=Ana"); contem(texto, "PRETAS=Pedro"); contem(texto, "TURNO=PRETAS");
            igual(bloco(p), bloco(ARQUIVOS.carregar(arquivo)));
        });
        testar("TM21", () -> {
            Path arquivo = temporarios.resolve("tm21.txt");
            console("NOVO Ana Pedro\nMOVER A3 B4\nSALVAR " + arquivo + "\nSAIR\n");
            String out = console("CARREGAR " + arquivo + "\nMOVER B6 A5\nESTADO\nSAIR\n");
            Partida esperado = Partida.nova("Ana", "Pedro"); mover(esperado, "A3", "B4"); mover(esperado, "B6", "A5");
            igual(bloco(esperado), ultimoBloco(out));
            Partida p = ARQUIVOS.carregar(arquivo); igual("Ana", p.getJogador(Cor.BRANCAS).getNome());
            igual("Pedro", p.getJogador(Cor.PRETAS).getNome());
        });
        testar("TM22", () -> consoleFalhaPreserva("CARREGAR " + temporarios.resolve("inexistente.txt")));
        testar("TM23", () -> consoleFalhaPreserva("CARREGAR testes/cenarios/tm23-invalido.txt"));
        testar("TA01", () -> consoleFalhaPreserva("ABRACADABRA"));
        testar("TA02", () -> consoleFalhaPreserva("MOVER"));
        testar("TA03", () -> rejeitaSemAlterar(Partida.nova("Ana", "Pedro"), "A3", "A4"));
        testar("TA04", () -> rejeitaSemAlterar(Partida.nova("Ana", "Pedro"), "C5", "D6"));
        testar("TA05", () -> rejeitaSemAlterar(carregar("ta05-comum-tras.txt"), "C3", "B2"));
        testar("TA06", () -> {
            String out = console("CARREGAR testes/cenarios/tm18-eliminacao.txt\nMOVER C3 E5\nMOVER E5 F6\nESTADO\nSAIR\n");
            Partida p = carregar("tm18-eliminacao.txt"); mover(p, "C3", "E5");
            igual(bloco(p), ultimoBloco(out)); contem(out, "Erro:");
        });
        testar("TA07", () -> consoleFalhaPreserva("CARREGAR testes/cenarios/ta07-vazio.txt"));
        testar("TA08", () -> {
            String out = console("novo Ana Pedro\nmover a3 b4\nestado\nsair\n");
            Partida p = Partida.nova("Ana", "Pedro"); mover(p, "A3", "B4"); igual(bloco(p), ultimoBloco(out));
        });
        testar("TA09", () -> {
            Partida p = carregar("tm11-captura-multipla.txt"); mover(p, "C3", "E5");
            Path arquivo = temporarios.resolve("preservar.txt"); Files.write(arquivo, Arrays.asList("Conteudo anterior"), StandardCharsets.UTF_8);
            String antes = new String(Files.readAllBytes(arquivo), StandardCharsets.UTF_8);
            falha(() -> ARQUIVOS.salvar(p, arquivo));
            igual(antes, new String(Files.readAllBytes(arquivo), StandardCharsets.UTF_8));
            igual(Cor.BRANCAS, p.getTurno()); peca(p, "D4", 'p');
            mover(p, "E5", "G7"); peca(p, "G7", 'b');
            String out = console("CARREGAR testes/cenarios/tm11-captura-multipla.txt\nMOVER C3 E5\nSALVAR " + arquivo + "\nMOVER E5 G7\nESTADO\nSAIR\n");
            igual(bloco(p), ultimoBloco(out)); contem(out, "Erro:");
            igual(antes, new String(Files.readAllBytes(arquivo), StandardCharsets.UTF_8));
        });
        testar("TA10", () -> {
            String out = console("CARREGAR testes/cenarios/tm18-eliminacao.txt\nMOVER C3 E5\nNOVO Clara Beto\nESTADO\nSAIR\n");
            igual(bloco(Partida.nova("Clara", "Beto")), ultimoBloco(out));
        });
        extras();
        System.out.println("RESULTADO: " + aprovados + " aprovados; " + reprovados + " reprovados.");
        apagarTemporarios();
        if (reprovados != 0) { throw new AssertionError("Existem testes reprovados."); }
    }

    private static void extras() throws Exception {
        testar("EX01 Maioria entre peças diferentes", () -> {
            Partida p = cenario(Cor.BRANCAS, "bC3", "bA5", "pD4", "pF6", "pB6", "pH8");
            rejeitaSemAlterar(p, "A5", "C7"); mover(p, "C3", "E5");
            rejeitaSemAlterar(p, "A5", "C7"); mover(p, "E5", "G7"); peca(p, "B6", 'p');
        });
        testar("EX02 Empate de sequências permite ambas", () -> {
            Partida esquerda = cenario(Cor.BRANCAS, "bC3", "pB4", "pD4", "pH8");
            Partida direita = cenario(Cor.BRANCAS, "bC3", "pB4", "pD4", "pH8");
            mover(esquerda, "C3", "A5"); mover(direita, "C3", "E5");
            peca(esquerda, "A5", 'b'); peca(direita, "E5", 'b');
        });
        testar("EX03 Capturados bloqueiam e não são recapturados", () -> {
            Partida p = cenario(Cor.BRANCAS, "BC3", "pD4", "pF6", "pB2");
            mover(p, "C3", "E5"); peca(p, "D4", 'p');
            rejeitaSemAlterar(p, "E5", "A1"); mover(p, "E5", "G7");
            verdade(!p.isCapturaEmAndamento(), "Não atravessar capturados para retornar a B2");
            peca(p, "B2", 'p'); peca(p, "D4", '.'); peca(p, "F6", '.');
        });
        testar("EX04 Dama aceita todas as aterrissagens empatadas", () -> {
            for (String destino : Arrays.asList("E5", "F6", "G7", "H8")) {
                Partida p = cenario(Cor.BRANCAS, "BC3", "pD4", "pH6");
                mover(p, "C3", destino); peca(p, destino, 'B'); peca(p, "D4", '.');
            }
        });
        testar("EX05 Dama escolhe aterrissagem pela maioria futura", () -> {
            Partida p = cenario(Cor.BRANCAS, "BC3", "pD4", "pF4", "pH6");
            rejeitaSemAlterar(p, "C3", "F6"); mover(p, "C3", "E5");
            mover(p, "E5", "G3"); peca(p, "G3", 'B');
        });
        testar("EX06 Promoção somente no fim da captura", () -> {
            Partida p = cenario(Cor.BRANCAS, "bB6", "pC7", "pE7", "pA7");
            mover(p, "B6", "D8"); peca(p, "D8", 'b');
            rejeitaSemAlterar(p, "D8", "G5"); mover(p, "D8", "F6"); peca(p, "F6", 'b');
        });
        testar("EX07 Promoção ao terminar captura na última linha", () -> {
            Partida p = cenario(Cor.BRANCAS, "bD6", "pE7", "pA7");
            mover(p, "D6", "F8"); peca(p, "F8", 'B');
        });
        testar("EX08 Regras espelhadas para pretas", () -> {
            Partida p = cenario(Cor.PRETAS, "pG3", "bF2", "bD2", "bA7");
            mover(p, "G3", "E1"); peca(p, "E1", 'p'); mover(p, "E1", "C3"); peca(p, "C3", 'p');
            Partida promocao = cenario(Cor.PRETAS, "pH2", "bA3"); mover(promocao, "H2", "G1"); peca(promocao, "G1", 'P');
            Partida movimento = cenario(Cor.PRETAS, "pF6", "bA3"); mover(movimento, "F6", "E5"); peca(movimento, "E5", 'p');
        });
        testar("EX09 Vitória preta por eliminação", () -> {
            Partida p = cenario(Cor.PRETAS, "pE5", "bD4"); mover(p, "E5", "C3");
            igual(StatusPartida.VITORIA_PRETAS, p.getStatus());
        });
        testar("EX10 Dama não atravessa duas peças nem aliada", () -> {
            Partida p = cenario(Cor.BRANCAS, "BC3", "pD4", "pF6", "pH6");
            rejeitaSemAlterar(p, "C3", "G7");
            Partida q = cenario(Cor.BRANCAS, "BC3", "pD4", "bF6", "pH6");
            rejeitaSemAlterar(q, "C3", "G7");
        });
        testar("EX11 Captura é obrigatória em outra peça", () -> {
            Partida p = cenario(Cor.BRANCAS, "bC3", "bA1", "pD4", "pH8"); rejeitaSemAlterar(p, "A1", "B2");
        });
        testar("EX12 Preservação de damas, nomes e vitória", () -> {
            Partida p = cenario(Cor.PRETAS, "BC3", "PH6");
            Path path = temporarios.resolve("damas.txt"); ARQUIVOS.salvar(p, path);
            Partida q = ARQUIVOS.carregar(path); igual(bloco(p), bloco(q));
            igual("Ana Júlia", q.getJogador(Cor.BRANCAS).getNome()); igual("Pedro Silva", q.getJogador(Cor.PRETAS).getNome());
            Partida fim = carregar("tm18-eliminacao.txt"); mover(fim, "C3", "E5");
            ARQUIVOS.salvar(fim, path); igual(bloco(fim), bloco(ARQUIVOS.carregar(path)));
        });
        testar("EX13 Arquivos estruturalmente corrompidos", () -> {
            String valido = arquivoTexto(Partida.nova("Ana", "Pedro"));
            List<String> invalidos = Arrays.asList(
                valido.replace("JOGO_DAMAS_V1", "JOGO_DAMAS_V2"),
                valido.replace("TURNO=BRANCAS", "TURNO=VERDES"),
                valido.replace("STATUS=EM_ANDAMENTO", "STATUS=EMPATE"),
                valido.replace("BRANCAS=Ana", "BRANCAS="),
                valido.replace(".p.p.p.p", "Xp.p.p.p"),
                valido.replace(".p.p.p.p", "pp.p.p.p"),
                valido.replace(".p.p.p.p\n", ""), valido + "EXTRA\n",
                valido.replace("TABULEIRO_FIM", "FIM"),
                valido.replace("STATUS=EM_ANDAMENTO", "STATUS=VITORIA_BRANCAS")
            );
            for (int i = 0; i < invalidos.size(); i++) {
                Path path = temporarios.resolve("corrupto-" + i + ".txt");
                Files.write(path, invalidos.get(i).getBytes(StandardCharsets.UTF_8));
                falha(() -> ARQUIVOS.carregar(path)); consoleFalhaPreserva("CARREGAR " + path);
            }
        });
        testar("EX14 Falhas de leitura e escrita", () -> {
            falha(() -> ARQUIVOS.carregar(temporarios));
            Path destino = temporarios.resolve("nao-existe").resolve("partida.txt");
            falha(() -> ARQUIVOS.salvar(Partida.nova("Ana", "Pedro"), destino));
            String out = console("NOVO Ana Pedro\nSALVAR " + destino + "\nESTADO\nSAIR\n");
            igual(bloco(Partida.nova("Ana", "Pedro")), ultimoBloco(out)); contem(out, "Erro:");
        });
        testar("EX15 Entrada incompleta, EOF e sem partida", () -> {
            String out = console("ESTADO\nMOVER A3 B4\nSALVAR " + temporarios.resolve("sem.txt") + "\nNOVO Ana\nNOVO Ana Pedro\nMOVER A3 B4 C5\nESTADO\n");
            igual(bloco(Partida.nova("Ana", "Pedro")), ultimoBloco(out)); contem(out, "Erro:");
        });
        testar("EX16 Caminho com espaços e caixa preservada", () -> {
            Path path = temporarios.resolve("Partida Com Espacos.txt");
            String out = console("NOVO Ana Pedro\nMOVER A3 B4\nsalvar " + path + "\nNOVO X Y\ncarregar " + path + "\nESTADO\nSAIR\n");
            Partida p = Partida.nova("Ana", "Pedro"); mover(p, "A3", "B4"); igual(bloco(p), ultimoBloco(out));
        });
        testar("EX17 Maioria continua sendo exigida após etapa comum", () -> {
            Partida p = cenario(Cor.BRANCAS, "bC3", "pD4", "pD6", "pF6", "pF4", "pF2", "pH8");
            mover(p, "C3", "E5");
            // O primeiro salto é comum; só G3-E1 completa a maior sequência.
            rejeitaSemAlterar(p, "E5", "C7"); rejeitaSemAlterar(p, "E5", "G7");
            mover(p, "E5", "G3"); mover(p, "G3", "E1"); peca(p, "E1", 'b');
        });
        testar("EX18 Captura para trás de peça preta", () -> {
            Partida p = cenario(Cor.PRETAS, "pC3", "bD4", "bH2"); mover(p, "C3", "E5"); peca(p, "E5", 'p');
        });
        testar("EX19 Rejeita posição impossível no carregamento", () -> {
            falha(() -> cenario(Cor.BRANCAS, "bB8", "pA7"));
            falha(() -> cenario(Cor.BRANCAS, "bC3", "pA1"));
            falha(() -> cenario(Cor.BRANCAS));
        });
        testar("EX20 Retorno a casa antes vazia após circuito", () -> {
            Partida p = cenario(Cor.BRANCAS, "bC3", "pD4", "pF4", "pF2", "pD2", "pA7");
            mover(p, "C3", "E5"); mover(p, "E5", "G3"); mover(p, "G3", "E1"); mover(p, "E1", "C3");
            peca(p, "C3", 'b'); peca(p, "D4", '.'); peca(p, "F4", '.'); peca(p, "F2", '.'); peca(p, "D2", '.');
        });
        testar("EX21 UTF-8 inválido e tamanho excessivo", () -> {
            Path utf8 = temporarios.resolve("utf8-invalido.txt");
            Files.write(utf8, new byte[] {(byte) 0xc3, (byte) 0x28});
            falha(() -> ARQUIVOS.carregar(utf8)); consoleFalhaPreserva("CARREGAR " + utf8);
            Path enorme = temporarios.resolve("enorme.txt"); byte[] excesso = new byte[16385];
            Arrays.fill(excesso, (byte) 'x'); Files.write(enorme, excesso);
            falha(() -> ARQUIVOS.carregar(enorme)); consoleFalhaPreserva("CARREGAR " + enorme);
        });
        testar("EX22 Arquivo com quebras de linha CRLF", () -> {
            Partida p = Partida.nova("Ana", "Pedro"); mover(p, "A3", "B4");
            Path path = temporarios.resolve("windows.txt");
            Files.write(path, arquivoTexto(p).replace("\n", "\r\n").getBytes(StandardCharsets.UTF_8));
            igual(bloco(p), bloco(ARQUIVOS.carregar(path)));
        });
        testar("EX23 Escrita sobre diretório preserva dados e limpa temporários", () -> {
            Path diretorio = Files.createDirectory(temporarios.resolve("destino-diretorio"));
            Path sentinela = diretorio.resolve("sentinela.txt");
            Files.write(sentinela, "PRESERVAR".getBytes(StandardCharsets.UTF_8));
            List<String> antes = listar(temporarios);
            falha(() -> ARQUIVOS.salvar(Partida.nova("Ana", "Pedro"), diretorio));
            igual("PRESERVAR", new String(Files.readAllBytes(sentinela), StandardCharsets.UTF_8));
            igual(antes, listar(temporarios)); igual(Arrays.asList("sentinela.txt"), listar(diretorio));
        });
        testar("EX24 Delimitadores ESTADO em linhas exatas", () -> {
            String out = console("NOVO Ana Pedro\nESTADO\nSAIR\n").replace("\r\n", "\n");
            List<String> linhas = Arrays.asList(out.split("\n"));
            int inicio = linhas.indexOf("TABULEIRO_INICIO");
            verdade(inicio >= 0, "Delimitador inicial precisa ocupar uma linha exata");
            for (int i = 1; i <= 8; i++) verdade(linhas.get(inicio + i).matches("[bpBP.]{8}"), "Linha do tabuleiro deve ter oito símbolos");
            igual("TABULEIRO_FIM", linhas.get(inicio + 9));
            igual("TURNO=BRANCAS", linhas.get(inicio + 10));
            igual("STATUS=EM_ANDAMENTO", linhas.get(inicio + 11));
        });
    }

    private static void testar(String nome, Caso caso) {
        try { caso.executar(); aprovados++; System.out.println("APROVADO " + nome); }
        catch (Exception | AssertionError e) { reprovados++; System.out.println("REPROVADO " + nome + ": " + e); e.printStackTrace(System.out); }
    }
    private static void verdade(boolean condicao, String mensagem) { if (!condicao) throw new AssertionError(mensagem); }
    private static void igual(Object esperado, Object obtido) {
        if (esperado == null ? obtido != null : !esperado.equals(obtido)) throw new AssertionError("Esperado: " + esperado + "; obtido: " + obtido);
    }
    private static void contem(String texto, String trecho) { verdade(texto.contains(trecho), "Saída não contém: " + trecho); }
    private static void falha(Caso acao) throws Exception {
        try { acao.executar(); } catch (IllegalArgumentException | IllegalStateException | IOException esperada) { return; }
        throw new AssertionError("A operação deveria ter sido rejeitada");
    }
    private static void mover(Partida p, String origem, String destino) { p.mover(Posicao.de(origem), Posicao.de(destino)); }
    private static void rejeitaSemAlterar(Partida p, String origem, String destino) throws Exception {
        String antes = bloco(p); boolean sequencia = p.isCapturaEmAndamento(); Posicao ativa = p.getPosicaoEmSequencia();
        falha(() -> mover(p, origem, destino)); igual(antes, bloco(p)); igual(sequencia, p.isCapturaEmAndamento()); igual(ativa, p.getPosicaoEmSequencia());
    }
    private static void peca(Partida p, String onde, char simbolo) {
        Peca encontrada = p.getTabuleiro().obter(Posicao.de(onde)); igual(simbolo, encontrada == null ? '.' : encontrada.getSimbolo());
    }
    private static int contar(Partida p, char simbolo) {
        int n = 0; for (String linha : p.getTabuleiro().linhas()) for (char c : linha.toCharArray()) if (c == simbolo) n++; return n;
    }
    private static String tabuleiro(Partida p) { return String.join("\n", p.getTabuleiro().linhas()); }
    private static String bloco(Partida p) { return "TABULEIRO_INICIO\n" + tabuleiro(p) + "\nTABULEIRO_FIM\nTURNO=" + p.getTurno() + "\nSTATUS=" + p.getStatus(); }
    private static String ultimoBloco(String out) {
        String s = out.replace("\r\n", "\n"); int inicio = s.lastIndexOf("TABULEIRO_INICIO\n");
        verdade(inicio >= 0, "Bloco ESTADO ausente"); int status = s.indexOf("STATUS=", inicio); verdade(status >= 0, "Status ausente");
        int fim = s.indexOf('\n', status); return s.substring(inicio, fim < 0 ? s.length() : fim).trim();
    }
    private static String console(String entrada) throws Exception {
        StringWriter saida = new StringWriter(); new ConsoleJogo(new StringReader(entrada), saida, ARQUIVOS).executar(); return saida.toString();
    }
    private static void consoleFalhaPreserva(String comando) throws Exception {
        String out = console("NOVO Ana Pedro\nMOVER A3 B4\n" + comando + "\nESTADO\nSAIR\n");
        Partida p = Partida.nova("Ana", "Pedro"); mover(p, "A3", "B4"); igual(bloco(p), ultimoBloco(out)); contem(out, "Erro:");
    }
    private static Partida carregar(String nome) throws IOException { return ARQUIVOS.carregar(Paths.get("testes", "cenarios", nome)); }
    private static Partida cenario(Cor turno, String... pecas) {
        char[][] casas = new char[8][8]; for (char[] linha : casas) Arrays.fill(linha, '.');
        for (String p : pecas) casas[8 - (p.charAt(2) - '0')][p.charAt(1) - 'A'] = p.charAt(0);
        List<String> linhas = new ArrayList<String>(); for (char[] linha : casas) linhas.add(new String(linha));
        return Partida.restaurar("Ana Júlia", "Pedro Silva", turno, StatusPartida.EM_ANDAMENTO, linhas);
    }
    private static String arquivoTexto(Partida p) { return "JOGO_DAMAS_V1\nBRANCAS=" + p.getJogador(Cor.BRANCAS).getNome() + "\nPRETAS=" + p.getJogador(Cor.PRETAS).getNome() + "\nTURNO=" + p.getTurno() + "\nSTATUS=" + p.getStatus() + "\nTABULEIRO_INICIO\n" + tabuleiro(p) + "\nTABULEIRO_FIM\n"; }
    private static List<String> listar(Path diretorio) throws IOException {
        List<String> nomes = new ArrayList<String>();
        try (java.util.stream.Stream<Path> arquivos = Files.list(diretorio)) {
            arquivos.forEach(p -> nomes.add(p.getFileName().toString()));
        }
        java.util.Collections.sort(nomes); return nomes;
    }
    private static void apagarTemporarios() throws IOException {
        try (java.util.stream.Stream<Path> caminhos = Files.walk(temporarios)) {
            Path[] todos = caminhos.sorted(java.util.Comparator.reverseOrder()).toArray(Path[]::new);
            for (Path p : todos) Files.deleteIfExists(p);
        }
    }
}
