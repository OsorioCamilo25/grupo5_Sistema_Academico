package model.structures;

public class Pila<T> {
    private Nodo<T> tope;
    private int tamano;

    public Pila(){
        this.tope = null;
        this.tamano = 0;
    }

    public void push(T valor) {
        if(valor == null){
            throw new IllegalArgumentException("El valor no puede ser nulo");
        }
        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.setSiguiente(this.tope);
        this.tope = nuevo;
        this.tamano++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        T valor = this.tope.getDato();
        this.tope = this.tope.getSiguiente();
        this.tamano--;
        return valor;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return this.tope.getDato();
    }

    public boolean isEmpty() {
        return tope == null;
    }

    public int size() {
        return tamano;
    }
}
