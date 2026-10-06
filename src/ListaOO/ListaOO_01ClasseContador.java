package ListaOO;

public class ListaOO_01ClasseContador {

    public static void main(String[] args) {

        Contador ctd = new Contador();

        ctd.incrementar();
        ctd.incrementar();
        ctd.incrementar();

        System.out.println(ctd.getValor());

        ctd.zerar();

        System.out.println(ctd.getValor());
    }
}