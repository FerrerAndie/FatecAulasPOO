package ListaOO;

public class Ponto2D {

    private double x;
    private double y;
    
    /*Construtores sobrecarregados que permitam a inicialização do ponto:
    i) Por default (sem parâmetros) na origem do espaço 2D;
    */
    public Ponto2D() {
    	x = 0;
    	y = 0;
    }
    
    
    public Ponto2D(double x, double y) {
    	
    	this.x = x; //guarde no atributo x o valor recebido pelo parâmetro x
        this.y = y;

    }
    
    public Ponto2D(Ponto2D outro) {
        this.x = outro.x;
        this.y = outro.y;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public void mover() {
    	x = 0;
    	y = 0;
    }
    
    public void mover(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void mover(Ponto2D outro) {
    	this.x = outro.x;
    	this.y = outro.y;
    }
    
    
    public boolean equals(Ponto2D outro) {
        return this.x == outro.x && this.y == outro.y;
    }
    
    public String toString() {
        return "Ponto: (" + x + ", " + y + ")";
    }
    
    //Cálcula dist
    public double distancia(Ponto2D outro) {
        double diferencaX = outro.x - this.x;
        double diferencaY = outro.y - this.y;

        double soma = diferencaX * diferencaX
                    + diferencaY * diferencaY;

        return Math.sqrt(soma);
        
        
    }
    
    public Ponto2D clone() {
        return new Ponto2D(this);
    }
}

