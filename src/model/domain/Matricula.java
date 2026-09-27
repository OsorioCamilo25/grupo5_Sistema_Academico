package model.domain;

import model.structures.ListaSimple;
import model.structures.Nodo;
public class Matricula {
 
    private Estudiante estudiante;
 
    private ListaSimple<Calificacion> calificaciones;
 
    public Matricula() {
        this.calificaciones = new ListaSimple<>();
    }
 
    public Matricula(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.calificaciones = new ListaSimple<>();
    }
 
    public Estudiante getEstudiante() {
        return estudiante;
    }
 
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
 
    public Calificacion registrarCalificacion(Materia materia, double notaParcial1,
                                               double notaParcial2, double notaFinal,
                                               String observaciones) {
 
        Calificacion nuevaCalificacion = new Calificacion(materia, notaParcial1,
                notaParcial2, notaFinal, observaciones);
 
        calificaciones.insertarFinal(nuevaCalificacion);
 
        return nuevaCalificacion;
    }
 
    public boolean eliminarCalificacion(Calificacion calificacion) {
        return calificaciones.eliminarPorValor(calificacion);
    }
 
    public ListaSimple<Calificacion> getCalificaciones() {
        return calificaciones;
    }
 
    public double calcularPromedioGeneral() {
 
        if (calificaciones.estaVacia()) {
            return 0.0;
        }
 
        double sumaPromedios = 0.0;
        Nodo<Calificacion> actual = calificaciones.getHead();
        while (actual != null) {
        sumaPromedios = sumaPromedios + actual.getDato().calcularPromedio();
        actual = actual.getSiguiente();
    }
 
        return sumaPromedios / calificaciones.getTamano();
    }
 
    @Override
    public String toString() {
        
        String nombreEstudiante = (estudiante != null) ? estudiante.getNombre() : "Sin asignar";
 
        return "Matricula del estudiante: " + nombreEstudiante +
                " | Cantidad de materias: " + calificaciones.getTamano() +
                " | Promedio general: " + calcularPromedioGeneral();
    }

    @Override 
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matricula otra = (Matricula) obj;
        return estudiante != null && estudiante.equals(otra.estudiante);
    }
}