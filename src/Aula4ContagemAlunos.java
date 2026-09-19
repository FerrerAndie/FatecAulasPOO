/*
 * Exercício matriz. Contar os alunos presentes na sala de aula.
 * 18/nov/2026
 */

import java.util.Scanner;

public class Aula4ContagemAlunos { 
    public static void main(String[] args) { 
        // Matriz de 7 linhas e 6 colunas
    	int[][] sala = {
    		    {1, 0, 0, 1, 1, 1}, // Linha 0
    		    {1, 1, 0, 0, 0, 0}, // Linha 1
    		    {1, 0, 0, 0, 0, 1}, // Linha 2
    		    {0, 0, 0, 1, 1, 0}, // Linha 3
    		    {0, 1, 1, 1, 1, 1}, // Linha 4
    		    {0, 1, 1, 1, 1, 0}, // Linha 5
    		    {0, 0, 1, 1, 1, 1}  // Linha 6
    		};
        
        int pessoasPresentes = 0; 
        Scanner sc = new Scanner(System.in);
        
        for(int i = 0; i < sala.length; i++) {         
            for (int j = 0; j < sala[i].length; j++) { 
                pessoasPresentes += sala[i][j]; 
            } 
        }
        if (pessoasPresentes == 1) {
        	System.out.println("Há " + pessoasPresentes + " pessoa presente na sala.");
        } else if (pessoasPresentes == 0) {
        	System.out.println("Falta coletiva.");
        } else {
        	System.out.println("Há " + pessoasPresentes + " pessoas presentes na sala.");
        }
        
        //Add valor em local vazio.
        System.out.print("Alguém chegou depois? ");
        String resposta = sc.next(); 

        if (resposta.equalsIgnoreCase("sim") | resposta.equalsIgnoreCase("s")) {
        	System.out.print("Digite o número da fileira (linha): ");
        	int fileira = sc.nextInt() - 1;
        	System.out.print("Digite o número da coluna: ");
        	int coluna = sc.nextInt() - 1;
        		if (sala[fileira][coluna] == 1) {
        			System.out.println(); 
        			System.out.println("Essa posição já está ocupada! Total de pessoas: " + pessoasPresentes);
        		} else {
        			sala[fileira][coluna] = 1;
                    pessoasPresentes++; 
                    System.out.println(); 
                    System.out.println("Aluno adicionado! Novo total: " + pessoasPresentes + " pessoas.");
        		}
        } else {
        	System.out.println();
        	System.out.println("Sem alunos adicionados. Total: " + pessoasPresentes +" pessoas.");
        }
        
        sc.close();
    } 
}