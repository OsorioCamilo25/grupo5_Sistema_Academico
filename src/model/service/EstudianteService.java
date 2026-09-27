package model.service;

import model.domain.Estudiante;
import model.domain.Matricula;
import model.structures.ListaSimple;
import model.structures.Nodo;

public class EstudianteService {
    
    private ListaSimple<Estudiante> estudiantes = new ListaSimple<>();
    public Estudiante crearEstudiante(String identificacion, String nombre, String correo, String codigo, int semestre) {
        Estudiante nuevoEstudiante = new Estudiante(identificacion, nombre, correo, codigo, semestre);
        estudiantes.insertarFinal(nuevoEstudiante);
        return nuevoEstudiante;
    }
    public Estudiante buscarPorIdentificacion(String identificacion) {
        Nodo<Estudiante> actual = estudiantes.getHead();
        while (actual != null) {
            if (actual.getDato().getIdentificacion().equals(identificacion)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

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