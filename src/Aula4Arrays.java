/*
 * Exercícios aula de arrays usando if, for
 */
public class Aula4Arrays {
	//Funcão imprimir array
	private static void imprimir(int[]p) {
		System.out.print("[");
        for (int i1 = 0; i1 < p.length; i1++) { //para inteiro de valor 0, enquanto ele for menor que o tamanho do array, incrementa com 1.
            System.out.print(p[i1]); // printa o valor enquanto o laço durar.
            
            if (i1 < p.length - 1) {   //se valor for menor que o tamanho do array imprimir uma vírgula. -1 pois o último numero não recebe.
                System.out.print(", ");
            }
        } 
        System.out.print("]");
        System.out.println();
        
	}
	
	private static int soma(int[]p) {
		int soma = 0;

		for (int i1 = 0; i1 < p.length; i1++) {
		    soma = soma + p[i1]; 
		}
		return soma;
		
		
	}
	
	
	public static void main(String[] args) {
		
		int [] v; //define uma variável do tipo array com inteiro.
		
		v = new int[6]; //a variavel v recebe um tamanho com 6 posições. 
		int i;
		
		for (i = 0; i < v.length; i ++) { // adiciona valor de 5 a 9 no array.
			v[i] = i + 5; //como o valor de i é 0, com 5 vira 6.
		}
		
		imprimir(v);
		
		
		
	}

}
