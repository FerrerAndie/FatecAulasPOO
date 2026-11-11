package lista6;

public class Main {

    public static void main(String[] args) {

        Ex01 ctd = new Ex01();

        ctd.incrementar();
        ctd.incrementar();
        ctd.incrementar();

        System.out.println(ctd.getValor());

        ctd.zerar();

        System.out.println(ctd.getValor());
    }
}