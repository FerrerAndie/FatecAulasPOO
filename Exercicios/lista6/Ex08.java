
package lista6;

public class Ex08 {

    private String nome;
    private Ex07[] paises = new Ex07[48];
    private int qtd = 0;

    // A) Construtor
    public Ex08(String nome) {
        this.nome = nome;
    }

    // B) Adicionar países
    public boolean adicionarPais(Ex07 pais) {

        if (pais == null || qtd == 48) {
            return false;
        }

        paises[qtd] = pais;
        qtd++;

        return true;
    }

    // C) Retornar a dimensão total
    public double dimensaoTotal() {

        double total = 0;

        for (int i = 0; i < qtd; i++) {
            total += paises[i].getDimensao();
        }

        return total;
    }

    // D) Retornar a população total
    public long populacaoTotal() {

        long total = 0;

        for (int i = 0; i < qtd; i++) {
            total += paises[i].getPopulacao();
        }

        return total;
    }

    // E) Retornar a densidade populacional
    public double densidadePopulacional() {

        if (dimensaoTotal() == 0) {
            return 0;
        }

        return (double) populacaoTotal() / dimensaoTotal();
    }

    // F) País com maior população
    public Ex07 maiorPopulacao() {

        if (qtd == 0) {
            return null;
        }

        Ex07 maior = paises[0];

        for (int i = 1; i < qtd; i++) {

            if (paises[i].getPopulacao() > maior.getPopulacao()) {
                maior = paises[i];
            }
        }

        return maior;
    }

    // G) País com menor população
    public Ex07 menorPopulacao() {

        if (qtd == 0) {
            return null;
        }

        Ex07 menor = paises[0];

        for (int i = 1; i < qtd; i++) {

            if (paises[i].getPopulacao() < menor.getPopulacao()) {
                menor = paises[i];
            }
        }

        return menor;
    }

    // H) País com maior dimensão territorial
    public Ex07 maiorDimensao() {

        if (qtd == 0) {
            return null;
        }

        Ex07 maior = paises[0];

        for (int i = 1; i < qtd; i++) {

            if (paises[i].getDimensao() > maior.getDimensao()) {
                maior = paises[i];
            }
        }

        return maior;
    }

    // I) País com menor dimensão territorial
    public Ex07 menorDimensao() {

        if (qtd == 0) {
            return null;
        }

        Ex07 menor = paises[0];

        for (int i = 1; i < qtd; i++) {

            if (paises[i].getDimensao() < menor.getDimensao()) {
                menor = paises[i];
            }
        }

        return menor;
    }

    // J) Razão territorial entre o maior e o menor país
    public double razaoTerritorial() {

        Ex07 maior = maiorDimensao();
        Ex07 menor = menorDimensao();

        if (maior == null || menor == null || menor.getDimensao() == 0) {
            return 0;
        }

        return maior.getDimensao() / menor.getDimensao();
    }

    public String getNome() {
        return nome;
    }
}

