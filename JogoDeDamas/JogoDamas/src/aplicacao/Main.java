package aplicacao;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import persistencia.PersistenciaArquivo;

/** Ponto de entrada: monta os objetos e entrega o controle ao console. */
public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        ConsoleJogo console = new ConsoleJogo(
                new InputStreamReader(System.in, StandardCharsets.UTF_8),
                new OutputStreamWriter(System.out, StandardCharsets.UTF_8),
                new PersistenciaArquivo());
        console.executar();
    }
}
