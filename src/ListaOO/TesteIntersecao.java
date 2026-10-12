package ListaOO;

public class TesteIntersecao {

    public static void main(String[] args) {

        Reta r1 = new Reta(1, 1);   // y = x + 1
        Reta r2 = new Reta(-2, 2);  // y = -2x + 2

        Ponto2D ponto = r1.intersecao(r2);

        if (ponto != null) {
            System.out.println("Interseção: " + ponto);
        } else {
            System.out.println("As retas são paralelas.");
        }
    }
}

