package model.service;

import model.domain.Calificacion;
import model.domain.Materia;
import model.domain.Matricula;
import model.structures.ListaSimple;
import model.structures.Pila;

public class MatriculaService {
    //public Calificacion agregarCalificacion(Matricula matricula, Materia materia,
    //                                         double notaParcial1, double notaParcial2,
    //                                         double notaFinal, String observaciones) {
    //
    //    materia.getCalificacionesRecientes().push(nuevaCalificacion);
//
    //    return matricula.registrarCalificacion(materia, notaParcial1, notaParcial2,
    //            notaFinal, observaciones);
    //}

    public Calificacion buscarCalificacion(Matricula matricula, Calificacion calificacion) {
        return matricula.getCalificaciones().buscarPorValor(calificacion);
    }

    public boolean eliminarCalificacion(Matricula matricula, Calificacion calificacion) {
        return matricula.eliminarCalificacion(calificacion);
    }

    public ListaSimple<Calificacion> listarCalificaciones(Matricula matricula) {
        return matricula.getCalificaciones();
    }
    public void registrarCalificacion(Materia materia, double notaParcial1, double notaParcial2,
                 double notaFinal, String observaciones){
        Calificacion calificacion = new Calificacion(materia, notaParcial1, notaParcial2, notaFinal, observaciones);
        materia.getCalificacionesRecientes().push(calificacion);
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
