/*
Login simples
Leia:
usuário
senha
Validação:
usuário = "admin"
senha = "1234"
Resultado:
"Acesso permitido"
"Acesso negado"
�� Dica: use .equals() para comparar Strings.
 */

import java.util.Scanner;
public class CondIf09_Login {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite o usuário: ");
		String user = sc.nextLine().toLowerCase(); 
		
		if ("admin".equals(user)) {
			System.out.print("Digite a senha: ");
			String senha = sc.nextLine();
			
			if ("1234".equals(senha)){
				System.out.print("Acesso permitido.");
			} else {
				System.out.print("Acesso negado, senha incorreta.");
			}
		} else {
			System.out.print("Acesso negado, usuário incorreto.");
		}
		
		
		sc.close();

	}

}
