package modelo;


public class Jogador {
    private String nome;
    private boolean branca; // true para brancas, false para pretas


    public Jogador(String nome, boolean branca) {
        this.nome = nome;
        this.branca = branca;
    }


    public String getNome() {
        return nome;
    }


    public boolean isBranca() {
        return branca;
    }
}

