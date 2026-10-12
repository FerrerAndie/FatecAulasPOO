package ListaOO;


import java.util.Arrays;

public class Pais {
    private String codigoISO;
    private String nome;
    private long populacao;
    private double dimensao;

    private Pais[] fronteiras = new Pais[40];
    private int qtd = 0;

    public Pais(String codigoISO, String nome, double dimensao) {
        this.codigoISO = codigoISO;
        this.nome = nome;
        setDimensao(dimensao);
    }

    public String getCodigoISO() {
        return codigoISO;
    }

    public void setCodigoISO(String codigoISO) {
        this.codigoISO = codigoISO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getPopulacao() {
        return populacao;
    }

    public void setPopulacao(long populacao) {
        if (populacao < 0) {
            throw new IllegalArgumentException();
        }
        this.populacao = populacao;
    }

    public double getDimensao() {
        return dimensao;
    }

    public void setDimensao(double dimensao) {
        if (dimensao < 0) {
            throw new IllegalArgumentException();
        }
        this.dimensao = dimensao;
    }

    @Override
    public boolean equals(Object objeto) {
        if (!(objeto instanceof Pais)) {
            return false;
        }

        Pais outro = (Pais) objeto;

        return codigoISO != null
            && codigoISO.equalsIgnoreCase(outro.codigoISO);
    }

    @Override
    public int hashCode() {
        return codigoISO == null ? 0 : codigoISO.toUpperCase().hashCode();
    }

    public boolean adicionarFronteira(Pais pais) {
        if (pais == null || equals(pais)
                || fazFronteira(pais) || qtd == 40) {
            return false;
        }

        fronteiras[qtd] = pais;
        qtd++;
        return true;
    }

    public boolean fazFronteira(Pais pais) {
        for (int i = 0; i < qtd; i++) {
            if (fronteiras[i].equals(pais)) {
                return true;
            }
        }
        return false;
    }

    public double densidadePopulacional() {
        if (dimensao == 0) {
            return 0;
        }
        return populacao / dimensao;
    }

    public Pais[] vizinhosComuns(Pais pais) {
        Pais[] temporario = new Pais[Math.min(qtd, pais.qtd)];
        int quantidade = 0;

        for (int i = 0; i < qtd; i++) {
            if (pais.fazFronteira(fronteiras[i])) {
                temporario[quantidade] = fronteiras[i];
                quantidade++;
            }
        }

        return Arrays.copyOf(temporario, quantidade);
    }

    @Override
    public String toString() {
        return nome + " (" + codigoISO + ")";
    }
}
