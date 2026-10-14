package model.domain;

import model.structures.ListaSimple;
import model.structures.Nodo;

public class Matricula {
    private Estudiante estudiante;
    private String periodo; // Identificador único
    private ListaSimple<Calificacion> calificaciones;

    public Matricula(Estudiante estudiante, String periodo) {
        this.estudiante = estudiante;
        this.periodo = periodo;
        this.calificaciones = new ListaSimple<>();
    }

    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    
    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }

    public Calificacion registrarCalificacion(Materia materia, double notaParcial1,
                                               double notaParcial2, double notaFinal,
                                               String observaciones) {
        Calificacion nuevaCalificacion = new Calificacion(materia, notaParcial1, notaParcial2, notaFinal, observaciones);
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
        if (calificaciones.estaVacia()) return 0.0;
        double suma = 0.0;
        Nodo<Calificacion> actual = calificaciones.getHead();
        while (actual != null) {
            suma += actual.getDato().calcularPromedio();
            actual = actual.getSiguiente();
        }
        return suma / calificaciones.getTamano();
    }

    @Override
    public String toString() {
        return "Matricula [" + periodo + "] | Estudiante: " + estudiante.getNombre() + 
               " | Materias: " + calificaciones.getTamano() + 
               " | Promedio: " + calcularPromedioGeneral();
    }

    @Override 
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matricula otra = (Matricula) obj;
        return estudiante.equals(otra.estudiante) && periodo.equals(otra.periodo);
    }
}