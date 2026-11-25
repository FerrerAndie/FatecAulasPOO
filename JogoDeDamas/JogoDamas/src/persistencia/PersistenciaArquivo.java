package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import jogo.Partida;
import jogo.StatusPartida;
import modelo.Cor;

/** Arquivo de texto versionado, validado por inteiro antes de criar a partida. */
public final class PersistenciaArquivo implements PersistenciaPartida {
    private static final String VERSAO = "JOGO_DAMAS_V1";
    private static final int LIMITE_BYTES = 16 * 1024;

    @Override
    public void salvar(Partida partida, Path arquivo) throws IOException {
        if (partida == null || arquivo == null) {
            throw new IllegalArgumentException("Informe a partida e o arquivo.");
        }
        if (partida.isCapturaEmAndamento()) {
            throw new IllegalStateException("Conclua a sequência de captura antes de salvar.");
        }

        Path destino = arquivo.toAbsolutePath().normalize();
        Path pasta = destino.getParent();
        if (pasta == null || !Files.isDirectory(pasta)) {
            throw new IOException("A pasta de destino não existe.");
        }
        // O arquivo atual só é substituído depois que a escrita termina sem erros.
        Path temporario = Files.createTempFile(pasta, ".jogodamas-", ".tmp");
        try {
            try (BufferedWriter saida = Files.newBufferedWriter(temporario, StandardCharsets.UTF_8)) {
                escreverLinha(saida, VERSAO);
                escreverLinha(saida, "BRANCAS=" + partida.getJogador(Cor.BRANCAS).getNome());
                escreverLinha(saida, "PRETAS=" + partida.getJogador(Cor.PRETAS).getNome());
                escreverLinha(saida, "TURNO=" + partida.getTurno().name());
                escreverLinha(saida, "STATUS=" + partida.getStatus().name());
                escreverLinha(saida, "TABULEIRO_INICIO");
                for (String linha : partida.getTabuleiro().linhas()) {
                    escreverLinha(saida, linha);
                }
                escreverLinha(saida, "TABULEIRO_FIM");
            }
            try {
                Files.move(temporario, destino, StandardCopyOption.ATOMIC_MOVE,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (AtomicMoveNotSupportedException e) {
                // Sistemas sem movimento atômico ainda recebem um arquivo já completo.
                Files.move(temporario, destino, StandardCopyOption.REPLACE_EXISTING);
            }
        } finally {
            Files.deleteIfExists(temporario);
        }
    }

    @Override
    public Partida carregar(Path arquivo) throws IOException {
        if (arquivo == null) {
            throw new IllegalArgumentException("Informe o arquivo.");
        }
        if (!Files.isRegularFile(arquivo)) {
            throw new IOException("Arquivo inexistente ou não é um arquivo regular.");
        }
        String texto = lerTextoLimitado(arquivo);
        List<String> linhas = new ArrayList<String>();
        try (BufferedReader entrada = new BufferedReader(new StringReader(texto))) {
            String linha;
            while ((linha = entrada.readLine()) != null) {
                linhas.add(linha);
            }
        }
        if (linhas.size() != 15 || !VERSAO.equals(linhas.get(0))
                || !"TABULEIRO_INICIO".equals(linhas.get(5))
                || !"TABULEIRO_FIM".equals(linhas.get(14))) {
            throw new IOException("Formato inválido: esperado um arquivo JOGO_DAMAS_V1 completo.");
        }
        try {
            String brancas = valor(linhas.get(1), "BRANCAS=");
            String pretas = valor(linhas.get(2), "PRETAS=");
            Cor turno = Cor.valueOf(valor(linhas.get(3), "TURNO="));
            StatusPartida status = StatusPartida.valueOf(valor(linhas.get(4), "STATUS="));
            List<String> tabuleiro = new ArrayList<String>(linhas.subList(6, 14));
            // Partida verifica peças, casas, quantidades e coerência da situação.
            return Partida.restaurar(brancas, pretas, turno, status, tabuleiro);
        } catch (IllegalArgumentException e) {
            throw new IOException("Partida inválida: " + e.getMessage(), e);
        }
    }

    private String lerTextoLimitado(Path arquivo) throws IOException {
        try (InputStream entrada = Files.newInputStream(arquivo);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int lidos;
            while ((lidos = entrada.read(buffer)) != -1) {
                if (bytes.size() + lidos > LIMITE_BYTES) {
                    throw new IOException("Arquivo grande demais para uma partida (limite de 16 KiB).");
                }
                bytes.write(buffer, 0, lidos);
            }
            try {
                return StandardCharsets.UTF_8.newDecoder()
                        .onMalformedInput(CodingErrorAction.REPORT)
                        .onUnmappableCharacter(CodingErrorAction.REPORT)
                        .decode(ByteBuffer.wrap(bytes.toByteArray())).toString();
            } catch (CharacterCodingException e) {
                throw new IOException("O arquivo deve usar texto UTF-8 válido.", e);
            }
        }
    }

    private String valor(String linha, String prefixo) {
        if (!linha.startsWith(prefixo) || linha.length() == prefixo.length()) {
            throw new IllegalArgumentException("Campo obrigatório ausente: " + prefixo);
        }
        return linha.substring(prefixo.length());
    }

    private void escreverLinha(BufferedWriter saida, String linha) throws IOException {
        saida.write(linha);
        saida.newLine();
    }
}
