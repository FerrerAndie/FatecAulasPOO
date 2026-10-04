/*15. IMC (Índice de Massa Corporal)
Leia:
peso
altura
Calcule:
IMC = peso / (altura * altura)
Classifique:
< 18.5 → Abaixo do peso
18.5 a 24.9 → Normal
25 a 29.9 → Sobrepeso
≥ 30 → Obesidade
 */


package dmd_Lista_2_condicionais;
import java.util.Scanner;

public class Cond15IMC {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite seu peso (kg): ");
		double peso = sc.nextDouble();
		
		System.out.println("Digite sua altura (m): ");
		double altura = sc.nextDouble();
		
		double imc = peso / (altura * altura);
		
		if(imc < 18.5) {
			System.out.println("IMC: " + imc + ". Você está abaixo do peso ideal.");
		} else if (imc > 18.5 && imc < 24.9) {
			System.out.println("IMC: " + imc + ". Você está com o peso ideal.");
		} else if (imc > 24.9 && imc < 29.9 ) {
			System.out.println("IMC: " + imc + ". Você está com sobrepeso.");
		} else {
			System.out.println("IMC: " + imc + ". Você está com obesidade.");
		}
		
		sc.close();

	}

}
