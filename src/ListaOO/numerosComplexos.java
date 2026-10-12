package ListaOO;

public class numerosComplexos {
	
	public class NumeroComplexo {
	    private double real;
	    private double imaginaria;
	
	public NumeroComplexo(double real, double imaginaria) {
		this.real = real;
		this.imaginaria = imaginaria;
		}
	
	public double getReal() {
	    return real;
	}
	
	public double getImaginaria() {
	    return imaginaria;
	}
	
	public void setReal(double real) {
	    this.real = real;
	}
	
	public void setImaginaria(double imaginaria) {
	    this.imaginaria = imaginaria;
	}
	
	public void somar(NumeroComplexo outro) {
	    this.real = this.real + outro.real;
	    this.imaginaria = this.imaginaria + outro.imaginaria;
	}
	
	public void subtrair(NumeroComplexo outro) {
	    this.real = this.real - outro.real;
	    this.imaginaria = this.imaginaria - outro.imaginaria;
	}
	
	public void multiplicar(NumeroComplexo outro) {
	    double novaReal = this.real * outro.real
	                    - this.imaginaria * outro.imaginaria;

	    double novaImaginaria = this.real * outro.imaginaria
	                          + this.imaginaria * outro.real;

	    this.real = novaReal;
	    this.imaginaria = novaImaginaria;
	}
	
	
	public void dividir(NumeroComplexo outro) {
	    double denominador = outro.real * outro.real
	                       + outro.imaginaria * outro.imaginaria;

	    if (denominador == 0) {
	        throw new ArithmeticException("Não é possível dividir por zero.");
	    }

	    double novaReal = (this.real * outro.real
	                     + this.imaginaria * outro.imaginaria)
	                     / denominador;

	    double novaImaginaria = (this.imaginaria * outro.real
	                           - this.real * outro.imaginaria)
	                           / denominador;

	    this.real = novaReal;
	    this.imaginaria = novaImaginaria;
	}
	
	public boolean equals(NumeroComplexo outro) {
	    return this.real == outro.real
	        && this.imaginaria == outro.imaginaria;
	}
	
	public String toString() {
	    if (imaginaria < 0) {
	        return real + " - " + Math.abs(imaginaria) + "i";
	    }

	    return real + " + " + imaginaria + "i";
	}
	
	public double modulo() {
	    return Math.sqrt(real * real + imaginaria * imaginaria);
	}
	}
	
}


