package jogodavelha;

public class Tabuleiro {

    private char[][] casas;

    public Tabuleiro() {
        casas = new char[3][3];
        inicializar();
    }

    public void inicializar() {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                casas[linha][coluna] = '-';
            }
        }
    }

    public void mostrar() {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                System.out.print(casas[linha][coluna] + " ");
            }
            System.out.println();
        }
    }

	public boolean marcar(int linha, int coluna, char simbolo) {
	    if (linha < 0 || linha >= 3 || coluna < 0 || coluna >= 3) {
	        System.out.println("Posição inválida!");
	        return false;
	    }
	
	    if (casas[linha][coluna] == '-') {
	        casas[linha][coluna] = simbolo;
	        return true;
	    } else {
	        System.out.println("Essa casa já está ocupada!");
	        return false;
	    }
	}
	

public boolean verificarVitoria(char simbolo) {

    // Verificar linhas
    for (int linha = 0; linha < 3; linha++) {
        if (casas[linha][0] == simbolo &&
            casas[linha][1] == simbolo &&
            casas[linha][2] == simbolo) {
            return true;
        }
    }

    // Verificar colunas
    for (int coluna = 0; coluna < 3; coluna++) {
        if (casas[0][coluna] == simbolo &&
            casas[1][coluna] == simbolo &&
            casas[2][coluna] == simbolo) {
            return true;
        }
    }

    // Verificar diagonal principal
    if (casas[0][0] == simbolo &&
        casas[1][1] == simbolo &&
        casas[2][2] == simbolo) {
        return true;
    }

    // Verificar diagonal secundária
    if (casas[0][2] == simbolo &&
        casas[1][1] == simbolo &&
        casas[2][0] == simbolo) {
        return true;
    }

    return false;
}


	public boolean verificarEmpate() {
	    for (int linha = 0; linha < 3; linha++) {
	        for (int coluna = 0; coluna < 3; coluna++) {
	            if (casas[linha][coluna] == '-') {
	                return false;
	            }
	        }
	    }
	
	    return true;
	}


}
