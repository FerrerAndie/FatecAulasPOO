/*
1) Escreva em Java uma classe Contador, que encapsule um valor usado para
contagem de itens ou eventos. A classe deve oferecer métodos que devem:
a) zerar;
b) incrementar;
c) Retornar o valor do contador.
 */

package lista6;

public class Ex01 {

    private int valor;

    public void zerar() {
        valor = 0;
    }

    public void incrementar() {
        valor++;
    }

    public int getValor() {
        return valor;
    }
}