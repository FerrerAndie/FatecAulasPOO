
public class MatrizIrregular01 {

	public static void main(String[] args) {
		//matriz:
		int [][] matriz = new int [7][];
		int num = 1;
		
		
		for (int i = 0; i < matriz.length; i++) {
			matriz[i] = new int[i + 1]; // para cada linha acrescento 1 
		}
		
		//prenchimento da matriz:
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = num;
				num += 1;
			}
		}
		
		//mostrar matriz:	
		
		for(int i = 0; i < matriz.length; i++) { 
            System.out.print("["); // Início da linha
            for (int j = 0; j < matriz[i].length; j++) { 
                System.out.print(matriz[i][j]); 
                
                if (j < matriz[i].length - 1) {
                    System.out.print(", ");
                }
            } 
            System.out.println("]");

		}

	}
}
