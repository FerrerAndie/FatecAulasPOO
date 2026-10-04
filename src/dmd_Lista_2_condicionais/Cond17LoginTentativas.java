/*
17. Login com tentativas
Usuário tem 3 tentativas
Se errar 3 vezes → "Conta bloqueada"
💡 Dica: use for ou while
 */

package dmd_Lista_2_condicionais;

import java.util.Scanner;

public class Cond17LoginTentativas {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Digite o usuário: ");
		String user = sc.nextLine().toLowerCase();

		if ("admin".equals(user)) {

			for (int tentativa = 1; tentativa <= 3; tentativa++) {

				System.out.print("Digite a senha: ");
				String senha = sc.nextLine();

				if ("1234".equals(senha)) {

					System.out.println("Acesso permitido.");
					break;

				} else if (tentativa < 3) {

					System.out.println("Senha incorreta. Você tem mais "
							+ (3 - tentativa) + " tentativas.");

				} else {

					System.out.println("Muitas tentativas, usuário bloqueado.");
				}
			}

		} else {

			System.out.println("Acesso negado, usuário incorreto.");
		}

		sc.close();
	}
}