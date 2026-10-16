package model.structures;

import java.util.NoSuchElementException;

public class Cola<T> {
    private Nodo<T> frente;   
    private Nodo<T> fin;      
    private int tamano;   

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamano = 0;
    }

    public void enqueue(T elemento) {
        if(elemento == null){
            throw new IllegalArgumentException("No se puede encolar un elemento nulo");
        }
        Nodo<T> nuevo = new Nodo<>(elemento);
        if(isEmpty()){
            this.frente = nuevo;
        } else {
            this.fin.setSiguiente(nuevo);
        }
        this.fin = nuevo;
        this.tamano++;
    }
 
    public T dequeue() {
        if(isEmpty()){
            throw new NoSuchElementException("La cola está vacía");
        }
        T dato = this.frente.getDato();
        this.frente = this.frente.getSiguiente();
        if(this.frente == null){
            this.fin = null;
        }
        this.tamano--;
        return dato;
    }
 
    public T front() {
        if(isEmpty()){
            throw new NoSuchElementException("La cola está vacía");
        }
        return this.frente.getDato(); 
    }
 
    public boolean isEmpty() {
        return frente == null; 
    }
 
    public int size() {
        return tamano; 
    }
}