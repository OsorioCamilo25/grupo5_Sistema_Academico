package service;

import model.domain.Calificacion;
import model.domain.Materia;
import model.domain.Matricula;
import model.structures.ListaSimple;

public class MatriculaService {

    // Este es el método exacto que MenuView está buscando y necesita
    public Calificacion agregarCalificacion(Matricula matricula, Materia materia, 
                                            double notaParcial1, double notaParcial2,
                                            double notaFinal, String observaciones) {
                                                
        // 1. Registramos la calificación en la lista de la matrícula
        Calificacion nuevaCalificacion = matricula.registrarCalificacion(materia, notaParcial1, notaParcial2, notaFinal, observaciones);
        
        // 2. Apilamos la calificación en el historial de la materia (Pila)
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

}