package service;

import model.domain.Estudiante;
import model.domain.Matricula;
import model.structures.ListaSimple;
import model.structures.Nodo;
import model.domain.ProcesoMatricula;
import model.structures.Cola;

public class EstudianteService {
    
    private ListaSimple<Estudiante> estudiantes = new ListaSimple<>();
    private ProcesoMatricula procesomatricula = new ProcesoMatricula("ProcesoMatricula 1");

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

    private Estudiante buscarEstudianteOFallar(String identificacion) {
        Estudiante estudiante = buscarPorIdentificacion(identificacion);
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante " + identificacion + " no existe");
        }
        return estudiante;
    }

    public void registrarSolicitud(String identificacion){
        Estudiante estudiante = buscarEstudianteOFallar(identificacion);
        procesomatricula.getFila().enqueue(estudiante);
    }
    
    public Estudiante procesarSiguienteSolicitud(){
        return procesomatricula.getFila().dequeue();
    }

    public Estudiante consultarSiguienteSolicitud(){
        return procesomatricula.getFila().front();
    }

    public boolean sinSolicitudesEnEspera() {
        return procesomatricula.getFila().isEmpty();
    }

    public int contarSolicitudesEnEspera() {
        return procesomatricula.getFila().size();
    }

    public String listarSolicitudesEnEspera() {
        model.structures.Cola<model.domain.Estudiante> fila = procesomatricula.getFila();
        if (fila.isEmpty()) {
            return "No hay estudiantes en espera.";
        }
        StringBuilder sb = new StringBuilder();
        int total = fila.size();
        for (int i = 0; i < total; i++) {
            model.domain.Estudiante estudiante = fila.dequeue();
            sb.append(i + 1).append(". ").append(estudiante.toString()).append("\n");
            fila.enqueue(estudiante);
        }
        return sb.toString();
    }
}