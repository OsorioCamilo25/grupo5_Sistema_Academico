package model.structures;

public class NodoDoble<T> {
    private T dato;
    private NodoDoble<T> siguiente;
    private NodoDoble<T> anterior;

    public NodoDoble(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public NodoDoble<T> getSiguiente() {
        return siguiente;
    }

    public NodoDoble<T> getAnterior() {
        return anterior;
    }

    public void setSiguiente(NodoDoble<T> siguiente) {
        this.siguiente = siguiente;
    }

    public void setAnterior(NodoDoble<T> anterior) {
        this.anterior = anterior;
    }
}
