package persistencia;

import java.io.IOException;
import java.nio.file.Path;

import jogo.Partida;

/** Contrato de armazenamento, independente da interface de console. */
public interface PersistenciaPartida {
    void salvar(Partida partida, Path arquivo) throws IOException;

    Partida carregar(Path arquivo) throws IOException;
}
