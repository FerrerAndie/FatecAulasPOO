/*
 Ler matriz 3x3
 Mostrar matriz na tela
 Calcular soma diagonal principal
 Calcular soma diagonal sencundária
 Mostrar as duas somas 
 */

import java.util.Scanner;

public class ExtraSomaDiagonais {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] matriz = new int[3][3];
        int soma = 0;
        int somaSecundaria = 0;

        // Ler matriz
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("Digite o valor da linha " + (i + 1) + " coluna " + (j +1) + ": ");
                matriz[i][j] = sc.nextInt();
            }
        }
        
     // Mostrar matriz
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println(); 
        }
        
        //calcular digonal principal:
        for (int i = 0; i < matriz.length; i++) {
        	soma += matriz [i][i];
        }
        
        //Calcular diagonal secundária:
        for (int i = 0; i < matriz.length; i++) {
        	somaSecundaria += matriz[i][matriz.length - 1 - i]; //i = 0,1,2; j = 3 - 1 - i
        }
        
        System.out.println("Soma diagonal principal: " + soma);
        System.out.println("Soma diagonal secundária: " + somaSecundaria);
        
        sc.close();
    }
}


