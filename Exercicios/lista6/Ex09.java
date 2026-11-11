package lista6;


public class Ex09 {
    private String nome;
    private Ex09 pai;
    private Ex09 mae;

    public Ex09(String nome, Ex09 pai, Ex09 mae) {
        this.nome = nome;
        this.pai = pai;
        this.mae = mae;
    }

    public Ex09(String nome) {
        this(nome, null, null);
    }

    public String getNome() {
        return nome;
    }

    public Ex09 getPai() {
        return pai;
    }

    public Ex09 getMae() {
        return mae;
    }

    public void setPai(Ex09 pai) {
        this.pai = pai;
    }

    public void setMae(Ex09 mae) {
        this.mae = mae;
    }

    public boolean equals(Ex09 pessoa) {
        return pessoa != null
            && nome.equals(pessoa.nome)
            && mesmaPessoa(mae, pessoa.mae);
    }

    private boolean mesmaPessoa(Ex09 a, Ex09 b) {
        return a == b
            || (a != null && b != null && a.nome.equals(b.nome));
    }

    public boolean saoIrmas(Ex09 pessoa) {
        return pessoa != null
            && pessoa != this
            && mesmaPessoa(pai, pessoa.pai)
            && mesmaPessoa(mae, pessoa.mae);
    }

    public boolean temComoAntecessora(Ex09 alvo) {
        return alvo != null
            && (ancestral(pai, alvo) || ancestral(mae, alvo));
    }

    private boolean ancestral(Ex09 ramo, Ex09 alvo) {
        return ramo != null
            && (ramo == alvo
                || ramo.nome.equals(alvo.nome)
                || ancestral(ramo.pai, alvo)
                || ancestral(ramo.mae, alvo));
    }
}

