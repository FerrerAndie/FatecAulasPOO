package ListaOO;


public class Matriz {
    private int linhas;
    private int colunas;
    private double[][] valores;

    public Matriz(int linhas, int colunas) {
        if (linhas <= 0 || colunas <= 0) {
            throw new IllegalArgumentException();
        }

        this.linhas = linhas;
        this.colunas = colunas;
        valores = new double[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public double get(int i, int j) {
        return valores[i][j];
    }

    public void set(int i, int j, double valor) {
        valores[i][j] = valor;
    }

    public boolean equals(Matriz matriz) {
        if (matriz == null
                || linhas != matriz.linhas
                || colunas != matriz.colunas) {
            return false;
        }

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (Math.abs(valores[i][j] - matriz.valores[i][j]) > 1e-9) {
                    return false;
                }
            }
        }

        return true;
    }

    public Matriz transposta() {
        Matriz resultado = new Matriz(colunas, linhas);

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                resultado.valores[j][i] = valores[i][j];
            }
        }

        return resultado;
    }

    public Matriz oposta() {
        Matriz resultado = new Matriz(linhas, colunas);

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                resultado.valores[i][j] = -valores[i][j];
            }
        }

        return resultado;
    }

    public Matriz nula() {
        return new Matriz(linhas, colunas);
    }

    public boolean ehNula() {
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (valores[i][j] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean ehIdentidade() {
        if (linhas != colunas) {
            return false;
        }

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                double esperado = (i == j) ? 1 : 0;

                if (valores[i][j] != esperado) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean ehDiagonal() {
        if (linhas != colunas) {
            return false;
        }

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (i != j && valores[i][j] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean ehSingular() {
        if (!ehDiagonal()) {
            return false;
        }

        for (int i = 1; i < linhas; i++) {
            if (valores[i][i] != valores[0][0]) {
                return false;
            }
        }

        return true;
    }

    public boolean ehSimetrica() {
        return linhas == colunas && equals(transposta());
    }

    public boolean ehAntissimetrica() {
        if (linhas != colunas) {
            return false;
        }

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (valores[i][j] != -valores[j][i]) {
                    return false;
                }
            }
        }

        return true;
    }

    public void somar(Matriz matriz) {
        mesmasDimensoes(matriz);

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                valores[i][j] += matriz.valores[i][j];
            }
        }
    }

    public void subtrair(Matriz matriz) {
        mesmasDimensoes(matriz);

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                valores[i][j] -= matriz.valores[i][j];
            }
        }
    }

    private void mesmasDimensoes(Matriz matriz) {
        if (matriz == null
                || linhas != matriz.linhas
                || colunas != matriz.colunas) {
            throw new IllegalArgumentException("Dimensões diferentes");
        }
    }

    public void multiplicar(Matriz matriz) {
        if (matriz == null || colunas != matriz.linhas) {
            throw new IllegalArgumentException("Dimensões incompatíveis");
        }

        double[][] resultado = new double[linhas][matriz.colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < matriz.colunas; j++) {
                for (int k = 0; k < colunas; k++) {
                    resultado[i][j] += valores[i][k] * matriz.valores[k][j];
                }
            }
        }

        colunas = matriz.colunas;
        valores = resultado;
    }

    public Matriz clone() {
        Matriz resultado = new Matriz(linhas, colunas);

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                resultado.valores[i][j] = valores[i][j];
            }
        }

        return resultado;
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                texto.append(valores[i][j]);

                if (j < colunas - 1) {
                    texto.append("\t");
                }
            }

            if (i < linhas - 1) {
                texto.append("\n");
            }
        }

        return texto.toString();
    }
}
