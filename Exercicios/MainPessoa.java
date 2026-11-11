
public class MainPessoa {

	public static void main(String[] args) {
		Pessoa p1 = new Pessoa("Ana", 1234568910);

	p1.setCordeCabelo("Ruivo");	
	System.out.println(p1.getCordeCabelo());
	
	p1.setCordeCabelo("Preto");
	System.out.println(p1.getCordeCabelo());
	
	System.out.println(p1.getnome());
	
	System.out.println(p1.getcpf());
	}

}
