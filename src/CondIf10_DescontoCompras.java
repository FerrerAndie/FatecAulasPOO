/*
10. Desconto em compra
Leia o valor de uma compra:
≥ 100 → aplicar 10% de desconto
< 100 → sem desconto
Exiba o valor final.
 */

import java.util.Scanner;
public class CondIf10_DescontoCompras {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double valorDesconto;
		
		System.out.print("Digite o valor da compra: ");
		double valorCompra = sc.nextDouble();
		
		if (valorCompra >= 100) {
			valorDesconto = valorCompra - (valorCompra * 0.10);
			System.out.print("O valor da compra com 10% de desconto é: R$ " + valorDesconto);
		} else {
			System.out.print("O valor da compra é: R$ " + valorCompra);
		}
		
		sc.close();
	}

}
