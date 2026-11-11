
public class ContagemAlunos {

	public static void main(String[] args) {
		int[][] sala = new int[7][6];
		sala[0][0] = 1;
		
		int pessoasPresentes = 0;
		for(int i = 0; i <sala.length; i++) {
			for (int j = 0; j < sala.length; j++) {
				pessoasPresentes += sala [i][j];
			}
		}
		System.out.println("São" + pessoasPresentes + " pessoas presentes");
	}

}
