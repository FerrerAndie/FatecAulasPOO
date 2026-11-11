package lista6;

public class Ex02 {

    private double x;
    private double y;
    
    /*Construtores sobrecarregados que permitam a inicialização do ponto:
    i) Por default (sem parâmetros) na origem do espaço 2D;
    */
    public Ex02() {
    	x = 0;
    	y = 0;
    }
    
    
    public Ex02(double x, double y) {
    	
    	this.x = x; //guarde no atributo x o valor recebido pelo parâmetro x
        this.y = y;

    }
    
    public Ex02(Ex02 outro) {
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
    
    public void mover(Ex02 outro) {
    	this.x = outro.x;
    	this.y = outro.y;
    }
    
    
    public boolean equals(Ex02 outro) {
        return this.x == outro.x && this.y == outro.y;
    }
    
    public String toString() {
        return "Ponto: (" + x + ", " + y + ")";
    }
    
    //Cálcula dist
    public double distancia(Ex02 outro) {
        double diferencaX = outro.x - this.x;
        double diferencaY = outro.y - this.y;

        double soma = diferencaX * diferencaX
                    + diferencaY * diferencaY;

        return Math.sqrt(soma);
        
        
    }
    
    public Ex02 clone() {
        return new Ex02(this);
    }
}

