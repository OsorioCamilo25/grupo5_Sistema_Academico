package model.structures;

import java.util.NoSuchElementException;
public class ListaDoble<T> {
    private NodoDoble<T> head;
    private NodoDoble<T> tail;
    private NodoDoble<T> actual;
    private int tamano;


    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return tamano;
    }

    public NodoDoble<T> getHead() {
        return head;
    }

    public void insertarAlInicio(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento no puede ser null");
        }
        NodoDoble<T> nuevo = new NodoDoble<>(elemento);
        if (isEmpty()) {
            tail = nuevo;
        } else {
            head.setAnterior(nuevo);
            nuevo.setSiguiente(head);
        }
        head = nuevo;
        tamano++;
    }    

    public void insertarAlFinal(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento no puede ser null");
        }
        NodoDoble<T> nuevo = new NodoDoble<>(elemento);
        if (isEmpty()) {
            head = nuevo;
        } else {
            tail.setSiguiente(nuevo);
            nuevo.setAnterior(tail);
        }
        tail = nuevo;
        tamano++;
    }    

    public T irAlInicio() {
        if (isEmpty()) {
            throw new NoSuchElementException("No hay elementos");
        }
        actual = head;
        return actual.getDato();
    }

    public T irAlFinal() {
        if (isEmpty()) {
            throw new NoSuchElementException("No hay elementos");
        }
        actual = tail;
        return actual.getDato();
    }

    public T siguiente() {
        if (actual == null) {
            return irAlInicio();
        }
        if (actual.getSiguiente() == null) {
            throw new NoSuchElementException("No hay elemento siguiente");
        }
        actual = actual.getSiguiente();
        return actual.getDato();
    }

    public T anterior() {
        if (actual == null) {
            return irAlFinal();
        }
        if (actual.getAnterior() == null) {
            throw new NoSuchElementException("No hay elemento anterior");
        }
        actual = actual.getAnterior();
        return actual.getDato();
    }

    public T retirarActual() {
        if (actual == null) {
            throw new NoSuchElementException("No hay elemento seleccionado");
        }
        return eliminar(actual);
    }

    public boolean hayAnterior() {
        return !isEmpty() && (actual == null || actual.getAnterior() != null);
    }

    public boolean haySiguiente() {
        return !isEmpty() && (actual == null || actual.getSiguiente() != null);
    }

    public boolean hayActual() {
        return actual != null;
    }

    public String recorrerAdelante() {
        StringBuilder sb = new StringBuilder();
        NodoDoble<T> nodo = head;
        while (nodo != null) {
            sb.append(nodo.getDato()).append("\n");
            nodo = nodo.getSiguiente();
        }
        return sb.toString();
    }

    public String recorrerAtras() {
        StringBuilder sb = new StringBuilder();
        NodoDoble<T> nodo = tail;
        while (nodo != null) {
            sb.append(nodo.getDato()).append("\n");
            nodo = nodo.getAnterior();
        }
        return sb.toString();
    }

    public NodoDoble<T> buscar(T dato) {
        NodoDoble<T> nodo = head;
        while (nodo != null) {
            if (nodo.getDato().equals(dato)) {
                return nodo;
            }
            nodo = nodo.getSiguiente();
        }
        return null;
    }

    public T eliminar(NodoDoble<T> nodo) {
        if (nodo == null) {
            throw new IllegalArgumentException("El nodo a eliminar no puede ser null");
        }
        NodoDoble<T> antes = nodo.getAnterior();
        NodoDoble<T> despues = nodo.getSiguiente();
        if (antes == null) {
            head = despues;
        } else {
            antes.setSiguiente(despues);
        }
        if (despues == null) {
            tail = antes;
        } else {
            despues.setAnterior(antes);
        }
        nodo.setSiguiente(null);
        nodo.setAnterior(null);
        if (nodo == actual) {
            actual = null;
        }
        tamano--;
        return nodo.getDato();
    }

} 
