package lista6;


public class Ex06 {
    private double raio;
    private Ex02 centro;

    public Ex06(double raio, Ex02 centro) {
        setRaio(raio);
        this.centro = new Ex02(centro);
    }

    public Ex06(double raio) {
        this(raio, new Ex02());
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        if (raio < 0) {
            throw new IllegalArgumentException("Raio negativo");
        }
        this.raio = raio;
    }

    public Ex02 getCentro() {
        return new Ex02(centro);
    }

    public void inflar(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException();
        }
        raio += valor;
    }

    public void inflar() {
        inflar(1);
    }

    public void desinflar(double valor) {
        if (valor < 0 || valor > raio) {
            throw new IllegalArgumentException("Raio inválido");
        }
        raio -= valor;
    }

    public void desinflar() {
        desinflar(1);
    }

    public void mover() {
        centro.mover();
    }

    public void mover(double x, double y) {
        centro.mover(x, y);
    }

    public void mover(Ex02 ponto) {
        centro.mover(ponto);
    }

    public double area() {
        return Math.PI * raio * raio;
    }
}

