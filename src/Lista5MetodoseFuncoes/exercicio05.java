package Lista5MetodoseFuncoes;


import java.util.Scanner;

public class exercicio05 {

    public static void mostrarMenu() {
        System.out.println("1 - Somar");
        System.out.println("2 - Subtrair");
        System.out.println("3 - Multiplicar");
        System.out.println("4 - Dividir");
    }

    public static double lerNumero(Scanner scanner) {
        System.out.print("Digite um número: ");
        return scanner.nextDouble();
    }

    public static double somar(double a, double b) {
        return a + b;
    }

    public static double subtrair(double a, double b) {
        return a - b;
    }

    public static double multiplicar(double a, double b) {
        return a * b;
    }

    public static double dividir(double a, double b) {
        return a / b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        mostrarMenu();
        System.out.print("Escolha uma operação: ");
        int opcao = scanner.nextInt();

        double n1 = lerNumero(scanner);
        double n2 = lerNumero(scanner);
        double resultado;

        switch (opcao) {
            case 1:
                resultado = somar(n1, n2);
                System.out.println("Resultado: " + resultado);
                break;
            case 2:
                resultado = subtrair(n1, n2);
                System.out.println("Resultado: " + resultado);
                break;
            case 3:
                resultado = multiplicar(n1, n2);
                System.out.println("Resultado: " + resultado);
                break;
            case 4:
                if (n2 == 0) {
                    System.out.println("Não é possível dividir por zero.");
                } else {
                    resultado = dividir(n1, n2);
                    System.out.println("Resultado: " + resultado);
                }
                break;
            default:
                System.out.println("Opção inválida.");
        }

        scanner.close();
    }
}

