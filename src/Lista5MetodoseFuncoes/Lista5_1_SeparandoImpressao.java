/*
Divida a lógica criando métodos:
mostrarTitulo()mostrarMenu()
 */
package Lista5MetodoseFuncoes;


	
public class Lista5_1_SeparandoImpressao {
	
	public static void mostrarTitulo(){
		System.out.println("==============="); 
		System.out.println("    SISTEMA    "); 
		System.out.println("===============");
		System.out.println();
	}
	
	public static void mostrarMenu(){
		System.out.println("1- Escolha"); 
		System.out.println("2- Sair");
		System.out.println();
	}
	
	public static void main(String[] args) {
		mostrarTitulo();
		mostrarMenu();

	}

}

	