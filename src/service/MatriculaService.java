package service;

import model.domain.Calificacion;
import model.domain.Materia;
import model.domain.Matricula;
import model.structures.ListaSimple;

public class MatriculaService {

    public Calificacion agregarCalificacion(Matricula matricula, Materia materia, 
                                            double notaParcial1, double notaParcial2,
                                            double notaFinal, String observaciones) {
        Calificacion nuevaCalificacion = matricula.registrarCalificacion(materia, notaParcial1, notaParcial2, notaFinal, observaciones);
        materia.getCalificacionesRecientes().push(nuevaCalificacion);
        
        return nuevaCalificacion;
    }

    public Calificacion buscarCalificacion(Matricula matricula, Calificacion calificacion) {
        return matricula.getCalificaciones().buscarPorValor(calificacion);
    }

    public boolean eliminarCalificacion(Matricula matricula, Calificacion calificacion) {
        return matricula.eliminarCalificacion(calificacion);
    }

    public ListaSimple<Calificacion> listarCalificaciones(Matricula matricula) {
        return matricula.getCalificaciones();
    }

    public Calificacion deshacerUltimaNota(Materia materia) {
        return materia.getCalificacionesRecientes().pop();
    }

    public Calificacion consultarUltimaNota(Materia materia) {
        return materia.getCalificacionesRecientes().peek();
    }

    public boolean sinNotasPorDeshacer(Materia materia) {
        return materia.getCalificacionesRecientes().isEmpty();
    }

    public int contarNotas(Materia materia) {
        return materia.getCalificacionesRecientes().size();
    }  

    public String listarHistorialNotas(Materia materia) {
        model.structures.Pila<Calificacion> original = materia.getCalificacionesRecientes();
        
        if (original.isEmpty()) {
            return "No hay calificaciones en el historial por deshacer.";
        }

        model.structures.Pila<Calificacion> auxiliar = new model.structures.Pila<>();
        StringBuilder sb = new StringBuilder();
        int contador = 1;

        while (!original.isEmpty()) {
            Calificacion nota = original.pop();
            sb.append(contador).append(". ").append(nota.toString()).append("\n");
            auxiliar.push(nota);
            contador++;
        }

        while (!auxiliar.isEmpty()) {
            original.push(auxiliar.pop());
        }

        return sb.toString();
    }
}