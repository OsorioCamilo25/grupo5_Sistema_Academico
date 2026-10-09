package model.domain;

import model.structures.Pila;

public class Materia {
 
  
    private String codigo;
    private String nombre;
    private int creditos;

    private Pila<Calificacion> calificaciones;
 
    
    public Materia(String codigo, String nombre, int creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;

        this.calificaciones = new Pila<>();
    }
 
    public Pila<Calificacion> getCalificacionesRecientes(){
        return calificaciones;
    }
 
    public String getCodigo() {
        return codigo;
    }
 
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public int getCreditos() {
        return creditos;
    }
 
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
 
    
    @Override
    public String toString() {
        return "Materia [codigo=" + codigo + ", nombre=" + nombre + ", creditos=" + creditos + "]";
    }
}