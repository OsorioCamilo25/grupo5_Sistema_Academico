package model.domain;
 
import java.util.ArrayList;
import java.util.List;
 
public class Matricula {
 
    private Estudiante estudiante;
 
    private List<Calificacion> calificaciones;
 
    public Matricula() {
        this.calificaciones = new ArrayList<>();
    }
 
    public Matricula(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.calificaciones = new ArrayList<>();
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
 
        calificaciones.add(nuevaCalificacion);
 
        return nuevaCalificacion;
    }
 
    public boolean eliminarCalificacion(Calificacion calificacion) {
        return calificaciones.remove(calificacion);
    }
 
    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }
 
    public double calcularPromedioGeneral() {
 
        if (calificaciones.isEmpty()) {
            return 0.0;
        }
 
        double sumaPromedios = 0.0;
 
        for (Calificacion c : calificaciones) {
            sumaPromedios = sumaPromedios + c.calcularPromedio();
        }
 
        return sumaPromedios / calificaciones.size();
    }
 
    @Override
    public String toString() {
        
        String nombreEstudiante = (estudiante != null) ? estudiante.getNombre() : "Sin asignar";
 
        return "Matricula del estudiante: " + nombreEstudiante +
                " | Cantidad de materias: " + calificaciones.size() +
                " | Promedio general: " + calcularPromedioGeneral();
    }
}