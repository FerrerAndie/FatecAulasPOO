package dmd_Lista_2_condicionais;
import java.util.Scanner;
public class Cond_21 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String usuarioCorreto = "admin";
        String senhaCorreta = "1234";

        System.out.print("Usuário: ");
        String usuario = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        if (!usuario.equals(usuarioCorreto)
                || !senha.equals(senhaCorreta)) {
            System.out.println("Login inválido");
            sc.close();
            return;
        }

        double saldo = 1000.0;
        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Ver saldo");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Saldo: R$ " + saldo);
                    break;

                case 2:
                    System.out.print("Valor do depósito: ");
                    double deposito = sc.nextDouble();

                    if (deposito > 0) {
                        saldo += deposito;
                        System.out.println("Depósito realizado");
                    } else {
                        System.out.println("Valor inválido");
                    }
                    break;

                case 3:
                    System.out.print("Valor do saque: ");
                    double saque = sc.nextDouble();

                    if (saque <= 0) {
                        System.out.println("Valor inválido");
                    } else if (saque > saldo) {
                        System.out.println("Saldo insuficiente");
                    } else {
                        saldo -= saque;
                        System.out.println("Saque realizado");
                    }
                    break;

                case 4:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 4);

        sc.close();
    }
}
