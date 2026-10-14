package service; // Asegúrate de haber movido el archivo a la carpeta service

import model.domain.Estudiante;
import model.domain.Matricula;
import model.structures.ListaSimple;
import model.structures.Nodo;

public class EstudianteService {
    
    private ListaSimple<Estudiante> estudiantes = new ListaSimple<>();

    public Estudiante crearEstudiante(String identificacion, String nombre, String correo, String codigo, int semestre) {
        Estudiante nuevo = new Estudiante(identificacion, nombre, correo, codigo, semestre);
        estudiantes.insertarFinal(nuevo);
        return nuevo;
    }

    public Estudiante buscarPorIdentificacion(String identificacion) {
        Nodo<Estudiante> actual = estudiantes.getHead();
        while (actual != null) {
            if (actual.getDato().getIdentificacion().equals(identificacion)) return actual.getDato();
            actual = actual.getSiguiente();
        }
        return null;
    }

    public void agregarMatricula(Estudiante estudiante, String periodo) {
        Matricula matricula = new Matricula(estudiante, periodo);
        estudiante.getMatricula().insertarFinal(matricula);
    }

    public Matricula buscarMatriculaPorPeriodo(Estudiante estudiante, String periodo) {
        Nodo<Matricula> actual = estudiante.getMatricula().getHead();
        while (actual != null) {
            if (actual.getDato().getPeriodo().equals(periodo)) return actual.getDato();
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean eliminarMatricula(Estudiante estudiante, String periodo) {
        Matricula dummy = new Matricula(estudiante, periodo);
        return estudiante.getMatricula().eliminarPorValor(dummy);
    }

    // Método que formatea los datos para la vista sin exponer nodos
    public String[] listarMatriculasFormateadas(Estudiante estudiante) {
        ListaSimple<Matricula> lista = estudiante.getMatricula();
        if (lista.estaVacia()) return new String[0];
        
        String[] datos = new String[lista.getTamano()];
        Nodo<Matricula> actual = lista.getHead();
        int i = 0;
        while (actual != null) {
            datos[i] = actual.getDato().toString();
            actual = actual.getSiguiente();
            i++;
        }
        return datos;
    }
}