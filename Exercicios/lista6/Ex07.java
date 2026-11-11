package lista6;


import java.util.Arrays;

public class Ex07 {
    private String codigoISO;
    private String nome;
    private long populacao;
    private double dimensao;

    private Ex07[] fronteiras = new Ex07[40];
    private int qtd = 0;

    public Ex07(String codigoISO, String nome, double dimensao) {
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
        if (!(objeto instanceof Ex07)) {
            return false;
        }

        Ex07 outro = (Ex07) objeto;

        return codigoISO != null
            && codigoISO.equalsIgnoreCase(outro.codigoISO);
    }

    @Override
    public int hashCode() {
        return codigoISO == null ? 0 : codigoISO.toUpperCase().hashCode();
    }

    public boolean adicionarFronteira(Ex07 pais) {
        if (pais == null || equals(pais)
                || fazFronteira(pais) || qtd == 40) {
            return false;
        }

        fronteiras[qtd] = pais;
        qtd++;
        return true;
    }

    public boolean fazFronteira(Ex07 pais) {
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

    public Ex07[] vizinhosComuns(Ex07 pais) {
        Ex07[] temporario = new Ex07[Math.min(qtd, pais.qtd)];
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
