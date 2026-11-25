package modelo;

/** Identidade imutavel de um dos dois jogadores humanos. */
public final class Jogador {
    private final String nome;
    private final Cor cor;

    public Jogador(String nome, Cor cor) {
        if (nome == null || nome.trim().isEmpty() || nome.length() > 100) {
            throw new IllegalArgumentException("Informe um nome de 1 a 100 caracteres.");
        }
        for (int i = 0; i < nome.length(); i++) {
            if (Character.isISOControl(nome.charAt(i))) {
                throw new IllegalArgumentException("O nome nao pode conter caracteres de controle.");
            }
        }
        if (cor == null) { throw new IllegalArgumentException("Cor obrigatoria."); }
        this.nome = nome.trim();
        this.cor = cor;
    }

    public String getNome() { return nome; }
    public Cor getCor() { return cor; }
}
