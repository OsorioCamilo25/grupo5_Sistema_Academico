package model.service;

import model.domain.Calificacion;
import model.domain.Materia;
import model.domain.Matricula;
import model.structures.ListaSimple;

public class MatriculaService {
     public Calificacion agregarCalificacion(Matricula matricula, Materia materia,
                                             double notaParcial1, double notaParcial2,
                                             double notaFinal, String observaciones) {
        return matricula.registrarCalificacion(materia, notaParcial1, notaParcial2,
                notaFinal, observaciones);
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
}
