/*
Classificação por idade
Classifique uma pessoa de acordo com a idade:
Criança: 0 a 12
Adolescente: 13 a 17
Adulto: 18 a 59
Idoso: 60 ou mais
 */

import java.util.Scanner;
public class CondIf06_ClassificarIdade {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite a idade: ");
		int idade = sc.nextInt();

		System.out.println();
		
		if(idade >= 0 && idade <= 12) {
			System.out.print("Classificação = criança.");
		} else if (idade > 12 && idade <= 17){
			System.out.print("Classificação = adolescente.");
		} else if (idade > 17 && idade <= 59) {
			System.out.print("Classificação = adulto.");
		} else if (idade > 59) {
			
		} else {
			System.out.print("Digite uma idade válida.");
		}
		
		sc.close();

	}

}
