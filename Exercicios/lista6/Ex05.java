package lista6;

public class Ex05 {

    public static void main(String[] args) {

        Ex04 r1 = new Ex04(1, 1);   // y = x + 1
        Ex04 r2 = new Ex04(-2, 2);  // y = -2x + 2

        Ex02 ponto = r1.intersecao(r2);

        if (ponto != null) {
            System.out.println("Interseção: " + ponto);
        } else {
            System.out.println("As retas são paralelas.");
        }
    }
}

