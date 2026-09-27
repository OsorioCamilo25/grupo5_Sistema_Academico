package model.service;
import model.domain.Estudiante;
import model.domain.Matricula;
import model.structures.ListaSimple;

public class EstudianteService {
      public void agregarMatricula(Estudiante estudiante, Matricula matricula) {
        estudiante.getMatricula().insertarFinal(matricula);
    }

    public Matricula buscarMatricula(Estudiante estudiante, Matricula matricula) {
        return estudiante.getMatricula().buscarPorValor(matricula);
    }

    public boolean eliminarMatricula(Estudiante estudiante, Matricula matricula) {
        return estudiante.getMatricula().eliminarPorValor(matricula);
    }

    public ListaSimple<Matricula> listarMatriculas(Estudiante estudiante) {
        return estudiante.getMatricula();
    }
}
