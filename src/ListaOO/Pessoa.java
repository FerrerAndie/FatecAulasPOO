package ListaOO;


public class Pessoa {
    private String nome;
    private Pessoa pai;
    private Pessoa mae;

    public Pessoa(String nome, Pessoa pai, Pessoa mae) {
        this.nome = nome;
        this.pai = pai;
        this.mae = mae;
    }

    public Pessoa(String nome) {
        this(nome, null, null);
    }

    public String getNome() {
        return nome;
    }

    public Pessoa getPai() {
        return pai;
    }

    public Pessoa getMae() {
        return mae;
    }

    public void setPai(Pessoa pai) {
        this.pai = pai;
    }

    public void setMae(Pessoa mae) {
        this.mae = mae;
    }

    public boolean equals(Pessoa pessoa) {
        return pessoa != null
            && nome.equals(pessoa.nome)
            && mesmaPessoa(mae, pessoa.mae);
    }

    private boolean mesmaPessoa(Pessoa a, Pessoa b) {
        return a == b
            || (a != null && b != null && a.nome.equals(b.nome));
    }

    public boolean saoIrmas(Pessoa pessoa) {
        return pessoa != null
            && pessoa != this
            && mesmaPessoa(pai, pessoa.pai)
            && mesmaPessoa(mae, pessoa.mae);
    }

    public boolean temComoAntecessora(Pessoa alvo) {
        return alvo != null
            && (ancestral(pai, alvo) || ancestral(mae, alvo));
    }

    private boolean ancestral(Pessoa ramo, Pessoa alvo) {
        return ramo != null
            && (ramo == alvo
                || ramo.nome.equals(alvo.nome)
                || ancestral(ramo.pai, alvo)
                || ancestral(ramo.mae, alvo));
    }
}

