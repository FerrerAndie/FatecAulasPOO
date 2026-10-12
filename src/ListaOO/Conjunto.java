package ListaOO;


import java.util.Arrays;

public class Conjunto {
    private String[] itens = new String[0];

    public boolean contem(String texto) {
        for (String item : itens) {
            if (item.equals(texto)) {
                return true;
            }
        }

        return false;
    }

    public boolean adicionar(String texto) {
        if (texto == null || contem(texto)) {
            return false;
        }

        itens = Arrays.copyOf(itens, itens.length + 1);
        itens[itens.length - 1] = texto;

        return true;
    }

    public Conjunto uniao(Conjunto outro) {
        Conjunto resultado = new Conjunto();

        for (String item : itens) {
            resultado.adicionar(item);
        }

        for (String item : outro.itens) {
            resultado.adicionar(item);
        }

        return resultado;
    }

    public Conjunto inter(Conjunto outro) {
        Conjunto resultado = new Conjunto();

        for (String item : itens) {
            if (outro.contem(item)) {
                resultado.adicionar(item);
            }
        }

        return resultado;
    }

    public Conjunto menos(Conjunto outro) {
        Conjunto resultado = new Conjunto();

        for (String item : itens) {
            if (!outro.contem(item)) {
                resultado.adicionar(item);
            }
        }

        return resultado;
    }

    @Override
    public String toString() {
        return Arrays.toString(itens);
    }
}
