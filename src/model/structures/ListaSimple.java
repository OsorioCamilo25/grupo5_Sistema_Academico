package model.structures;

import java.util.NoSuchElementException;

public class ListaSimple<T> implements OperacionesEstructuras<T> {
    private Nodo<T> head;
    private int tamano;

    public ListaSimple() {
        this.head = null;
        this.tamano = 0;
    }

    public boolean estaVacia() {
        return head == null;
    }

    public int getTamano() {
        return tamano;
    }

    public void insertarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(head);
        head = nuevo;
        tamano++;
    }

    public void insertarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            head = nuevo;
        } else {
            Nodo<T> actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamano++;
    }

    public void insertarEnPosicion(int indice, T dato) {
        if (indice < 0 || indice > tamano) {
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + indice);
        }
        if (indice == 0) {
            this.insertarInicio(dato);
            return;
        }
        if (indice == tamano) {
            this.insertarFinal(dato);
            return;
        }

        Nodo<T> anterior = head;
        for (int i = 0; i < indice - 1; i++) {
            anterior = anterior.getSiguiente();
        }

        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(anterior.getSiguiente());
        anterior.setSiguiente(nuevo);
        tamano++;
    }

    @Override
    public void crear(int indice, T dato) {
        insertarEnPosicion(indice, dato);
    }

    @Override
    public T buscarPorIndice(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + indice);
        }
        Nodo<T> actual = head;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    @Override
    public T buscarPorValor(T dato) {
        Nodo<T> actual = head;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        System.out.println("Dato no encontrado");
        return null;
    }

    public void eliminarInicio() {
        if (estaVacia()) {
            throw new NoSuchElementException("No hay elementos para eliminar");
        }
        head = head.getSiguiente();
        tamano--;
    }

    public void eliminarFinal() {
        if (estaVacia()) {
            throw new NoSuchElementException("No hay elementos para eliminar");
        }
        if (head.getSiguiente() == null) {
            head = null;
            tamano--;
            return;
        }
        Nodo<T> actual = head;
        while (actual.getSiguiente().getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(null);
        tamano--;
    }

    public Nodo<T> getHead() {
        return head;
    }

    @Override
    public void actualizar(int indice, T dato) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + indice);
        }
        Nodo<T> datoActual = this.head;
        for (int i = 0; i < indice; i++) {
            datoActual = datoActual.getSiguiente();
        }
        datoActual.setDato(dato);
    }

    public boolean eliminarAlInicio() {
        if (this.estaVacia()) {
            System.out.println("No hay elementos para eliminar");
            return false;
        }
        this.head = this.head.getSiguiente();
        this.tamano--;
        return true;
    }

    public boolean eliminarAlFinal() {
        if (this.estaVacia()) {
            System.out.println("No hay elementos para eliminar");
            return false;
        }
        if (this.head.getSiguiente() == null) {
            this.head = null;
            tamano--;
            return true;
        }

        Nodo<T> datoActual = this.head;
        while (datoActual.getSiguiente().getSiguiente() != null) {
            datoActual = datoActual.getSiguiente();
        }
        datoActual.setSiguiente(null);
        this.tamano--;
        return true;
    }

    public boolean eliminarPorValor(T dato) {
        if (this.estaVacia()) {
            System.out.println("No hay elementos para eliminar");
            return false;
        }
        Nodo<T> datoAnterior = this.head;
        Nodo<T> datoSiguiente = datoAnterior.getSiguiente();
        while (datoSiguiente != null) {
            if (datoSiguiente.getDato().equals(dato)) {
                datoAnterior.setSiguiente(datoSiguiente.getSiguiente());
                tamano--;
                return true;
            }
            datoAnterior = datoSiguiente;
            datoSiguiente = datoSiguiente.getSiguiente();
        }
        return false;
    }

    @Override
    public boolean eliminar(T dato) {
        return eliminarPorValor(dato);
    }
}