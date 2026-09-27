package view;

import utils.ConsoleUtils;
import model.service.EstudianteService;
import model.service.MatriculaService;
import model.domain.Estudiante;
import model.domain.Matricula;
import model.domain.Materia;
import model.domain.Calificacion;
import model.structures.ListaSimple;
import model.structures.Nodo;

public class MenuView {
    
    private EstudianteService estudianteService = new EstudianteService();
    private MatriculaService matriculaService = new MatriculaService();

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = ConsoleUtils.leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1 -> agregarMatricula();
                case 2 -> listarMatriculas();
                case 3 -> eliminarMatricula();
                case 4 -> agregarCalificacion();
                case 5 -> listarCalificaciones();
                case 6 -> eliminarCalificacion();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        ConsoleUtils.cerrar();
    }

    private void mostrarMenu() {
        System.out.println("\n--- GESTIÓN DE MATRÍCULAS Y CALIFICACIONES ---");
        System.out.println("1. Agregar matricula a estudiante");
        System.out.println("2. Buscar/Listar matriculas de estudiante");
        System.out.println("3. Eliminar matricula de estudiante");
        System.out.println("----------------------------------------------");
        System.out.println("4. Agregar calificacion a estudiante");
        System.out.println("5. Buscar/Listar calificaciones de estudiante");
        System.out.println("6. Eliminar calificacion de estudiante");
        System.out.println("0. Salir");
    }

    // Busca al estudiante, si no existe te pide los datos para crearlo
    private Estudiante obtenerOCrearEstudiante(String documento) {
        Estudiante estudiante = estudianteService.buscarPorIdentificacion(documento);
        if (estudiante == null) {
            System.out.println("Estudiante nuevo detectado. Registrando datos básicos...");
            String nombre = ConsoleUtils.leerTexto("Nombre: ");
            String correo = ConsoleUtils.leerTexto("Correo: ");
            String codigo = ConsoleUtils.leerTexto("Codigo (ej. COD-001): ");
            int semestre = ConsoleUtils.leerEntero("Semestre actual: ");
            estudiante = estudianteService.crearEstudiante(documento, nombre, correo, codigo, semestre);
        }
        return estudiante;
    }

    private void agregarMatricula() {
        String documento = ConsoleUtils.leerTexto("Documento del estudiante: ");
        Estudiante estudiante = obtenerOCrearEstudiante(documento);
        
        Matricula matricula = new Matricula(estudiante); 
        estudianteService.agregarMatricula(estudiante, matricula);
        System.out.println("Matricula agregada exitosamente a: " + estudiante.getNombre());
    }

    private void listarMatriculas() {
        String documento = ConsoleUtils.leerTexto("Documento del estudiante: ");
        Estudiante estudiante = estudianteService.buscarPorIdentificacion(documento);
        
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        ListaSimple<Matricula> lista = estudianteService.listarMatriculas(estudiante);
        if (lista == null || lista.estaVacia()) {
            System.out.println("El estudiante " + estudiante.getNombre() + " no tiene matriculas registradas.");
            return;
        }
        
        System.out.println("\n--- Lista de Matriculas ---");
        Nodo<Matricula> actual = lista.getHead();
        while (actual != null) {
            System.out.println(actual.getDato().toString());
            actual = actual.getSiguiente();
        }
    }

    private void eliminarMatricula() {
        String documento = ConsoleUtils.leerTexto("Documento del estudiante: ");
        Estudiante estudiante = estudianteService.buscarPorIdentificacion(documento);
        
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }
        
        Matricula matriculaAEliminar = new Matricula(estudiante);
        
        boolean eliminado = estudianteService.eliminarMatricula(estudiante, matriculaAEliminar);
        if (eliminado) {
            System.out.println("Matricula eliminada exitosamente.");
        } else {
            System.out.println("Error al eliminar la matricula.");
        }
    }

    private void agregarCalificacion() {
        String documento = ConsoleUtils.leerTexto("Documento del estudiante: ");
        Estudiante estudiante = estudianteService.buscarPorIdentificacion(documento);
        
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado. Debe agregar una matricula primero.");
            return;
        }

        if (estudiante.getMatricula() == null || estudiante.getMatricula().estaVacia()) {
            System.out.println("Error: El estudiante debe tener al menos una matricula para agregar notas.");
            return;
        }
        
        double nota1 = ConsoleUtils.leerDecimal("Valor nota parcial 1: ");
        double nota2 = ConsoleUtils.leerDecimal("Valor nota parcial 2: ");
        double notaFinal = ConsoleUtils.leerDecimal("Valor nota final: ");
        String observaciones = ConsoleUtils.leerTexto("Observaciones: ");
        
        Matricula matricula = estudiante.getMatricula().getHead().getDato();
        Materia materia = new Materia("000", "Generica", 3);
        
        matriculaService.agregarCalificacion(matricula, materia, nota1, nota2, notaFinal, observaciones);
        System.out.println("Calificacion agregada exitosamente.");
    }

    private void listarCalificaciones() {
        String documento = ConsoleUtils.leerTexto("Documento del estudiante: ");
        Estudiante estudiante = estudianteService.buscarPorIdentificacion(documento);
        
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }
        
        if (estudiante.getMatricula() == null || estudiante.getMatricula().estaVacia()) {
            System.out.println("El estudiante no tiene matriculas activas.");
            return;
        }

        Matricula matricula = estudiante.getMatricula().getHead().getDato();
        ListaSimple<Calificacion> lista = matriculaService.listarCalificaciones(matricula);
        
        if (lista == null || lista.estaVacia()) {
            System.out.println("No hay calificaciones registradas.");
            return;
        }
        
        System.out.println("\n--- Lista de Calificaciones ---");
        Nodo<Calificacion> actual = lista.getHead();
        while (actual != null) {
            System.out.println(actual.getDato().toString());
            actual = actual.getSiguiente();
        }
    }

private void eliminarCalificacion() {
        String documento = ConsoleUtils.leerTexto("Documento del estudiante: ");
        Estudiante estudiante = estudianteService.buscarPorIdentificacion(documento);
        
        if (estudiante == null || estudiante.getMatricula().estaVacia()) {
            System.out.println("Estudiante o matrículas no encontrados.");
            return;
        }

        Matricula matricula = estudiante.getMatricula().getHead().getDato();
        ListaSimple<Calificacion> calificaciones = matricula.getCalificaciones();

        if (calificaciones.estaVacia()) {
            System.out.println("No hay calificaciones registradas para eliminar.");
            return;
        }

        System.out.println("\n--- Calificaciones Registradas ---");
        Nodo<Calificacion> actual = calificaciones.getHead();
        int index = 0;
        while (actual != null) {
            System.out.println("[" + index + "] " + actual.getDato().toString());
            actual = actual.getSiguiente();
            index++;
        }

        int indiceAEliminar = ConsoleUtils.leerEntero("Ingrese el índice de la calificación a eliminar: ");
        try {
            Calificacion califAEliminar = calificaciones.buscarPorIndice(indiceAEliminar);
            boolean eliminado = matriculaService.eliminarCalificacion(matricula, califAEliminar);
            if (eliminado) {
                System.out.println("Calificación eliminada exitosamente.");
            } else {
                System.out.println("No se pudo eliminar la calificación.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índice fuera de rango.");
        }
    }
}