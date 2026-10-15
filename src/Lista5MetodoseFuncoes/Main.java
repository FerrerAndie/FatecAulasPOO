package Lista5MetodoseFuncoes;

public class Main {

    public static int somar(int n1, int n2) {
        return n1 + n2;
    }

    public static void mostrarResultado(int resultado) {
        System.out.println("Resultado: " + resultado);
    }

    public static void main(String[] args) {
        int resultado = somar(10, 20);
        mostrarResultado(resultado);
    }
}