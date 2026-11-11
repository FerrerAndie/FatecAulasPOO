package lista6;

public class Ex04 {
    private double a;
    private double b;

    public Ex04(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public Ex04(Ex02 p1, Ex02 p2) {
        if (p1.getX() == p2.getX()) {
            throw new IllegalArgumentException(
                "Não é possível representar uma reta vertical como y = ax + b."
            );
        }

        this.a = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
        this.b = p1.getY() - this.a * p1.getX();
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double calcularY(double x) {
        return a * x + b;
    }

    public boolean pertence(Ex02 ponto) {
        return Math.abs(ponto.getY() - calcularY(ponto.getX())) < 0.000001;
    }

    public Ex02 intersecao(Ex04 outra) {
        if (this.a == outra.a) {
            return null;
        }

        double x = (outra.b - this.b) / (this.a - outra.a);
        double y = calcularY(x);

        return new Ex02(x, y);
    }

    @Override
    public String toString() {
        return "y = " + a + "x + " + b;
    }
    
    public class IntersecaoRetas {

        public static Ex02 intersecao(Ex04 r1, Ex04 r2) {

            if (r1.getA() == r2.getA()) {
                return null;
            }

            double x = (r2.getB() - r1.getB()) / (r1.getA() - r2.getA());
            double y = r1.calcularY(x);

            return new Ex02(x, y);
        }
    }

}

