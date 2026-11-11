
public class Pessoa {
	private String nome;
	private int cpf;
	private String corDeCabelo;
	
	
	//construtor
	public Pessoa(String nome, int cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	//método para atribuir cor de cabelo:
	public void setCordeCabelo(String corDeCabelo) {
		this.corDeCabelo = corDeCabelo;
	}
	
	//método para retornar cor de cabelo
	public String getCordeCabelo() {
		return corDeCabelo;
	}
	
	//retornar nome
	public String getnome() {
		return nome;
	}
	
	//retornar cpf
	public int getcpf() {
		return cpf;
	}
}
