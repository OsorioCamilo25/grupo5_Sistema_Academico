package view;

import utils.ConsoleUtils;
import service.EstudianteService;
import service.MatriculaService;
import model.domain.Estudiante;
import model.domain.Matricula;
import model.domain.Materia;

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
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion en desarrollo o invalida.");
            }
        } while (opcion != 0);
        ConsoleUtils.cerrar();
    }

    private void mostrarMenu() {
        System.out.println("\n--- GESTIÓN DE MATRÍCULAS Y CALIFICACIONES ---");
        System.out.println("1. Agregar matricula a estudiante");
        System.out.println("2. Buscar/Listar matriculas de estudiante");
        System.out.println("3. Eliminar matricula de estudiante");
        System.out.println("4. Agregar calificacion a materia real");
        System.out.println("0. Salir");
    }

    private Estudiante obtenerOCrearEstudiante(String documento) {
        Estudiante estudiante = estudianteService.buscarPorIdentificacion(documento);
        if (estudiante == null) {
            System.out.println("Estudiante nuevo. Registrando datos básicos...");
            String nombre = ConsoleUtils.leerTexto("Nombre: ");
            String correo = ConsoleUtils.leerTexto("Correo: ");
            String codigo = ConsoleUtils.leerTexto("Codigo: ");
            int semestre = ConsoleUtils.leerEntero("Semestre: ");
            estudiante = estudianteService.crearEstudiante(documento, nombre, correo, codigo, semestre);
        }
        return estudiante;
    }

    private void agregarMatricula() {
        String documento = ConsoleUtils.leerTexto("Documento del estudiante: ");
        Estudiante estudiante = obtenerOCrearEstudiante(documento);
        String periodo = ConsoleUtils.leerTexto("Periodo académico (ej. 2026-1): ");
        
        estudianteService.agregarMatricula(estudiante, periodo);
        System.out.println("Matricula agregada exitosamente.");
    }

    private void listarMatriculas() {
        String documento = ConsoleUtils.leerTexto("Documento del estudiante: ");
        Estudiante estudiante = estudianteService.buscarPorIdentificacion(documento);
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        String[] matriculas = estudianteService.listarMatriculasFormateadas(estudiante);
        if (matriculas.length == 0) {
            System.out.println("No tiene matriculas registradas.");
            return;
        }
        
        System.out.println("\n--- Matriculas ---");
        for (String mat : matriculas) {
            System.out.println(mat);
        }
    }

    private void eliminarMatricula() {
        String documento = ConsoleUtils.leerTexto("Documento del estudiante: ");
        Estudiante estudiante = estudianteService.buscarPorIdentificacion(documento);
        if (estudiante == null) return;
        
        String periodo = ConsoleUtils.leerTexto("Ingrese el periodo de la matricula a eliminar: ");
        boolean eliminado = estudianteService.eliminarMatricula(estudiante, periodo);
        System.out.println(eliminado ? "Eliminada exitosamente." : "Error: Matricula no encontrada.");
    }

    private void agregarCalificacion() {
        String documento = ConsoleUtils.leerTexto("Documento del estudiante: ");
        Estudiante estudiante = estudianteService.buscarPorIdentificacion(documento);
        if (estudiante == null) return;
        
        String periodo = ConsoleUtils.leerTexto("Ingrese el periodo de la matricula (ej. 2026-1): ");
        Matricula matricula = estudianteService.buscarMatriculaPorPeriodo(estudiante, periodo);
        
        if (matricula == null) {
            System.out.println("Matricula no encontrada.");
            return;
        }

        System.out.println("--- Datos de la Materia ---");
        String codMateria = ConsoleUtils.leerTexto("Codigo materia: ");
        String nomMateria = ConsoleUtils.leerTexto("Nombre materia: ");
        int creditos = ConsoleUtils.leerEntero("Creditos: ");
        Materia materia = new Materia(codMateria, nomMateria, creditos);
        
        double n1 = ConsoleUtils.leerDecimal("Nota 1: ");
        double n2 = ConsoleUtils.leerDecimal("Nota 2: ");
        double nF = ConsoleUtils.leerDecimal("Nota Final: ");
        String obs = ConsoleUtils.leerTexto("Observaciones: ");
        
        matriculaService.agregarCalificacion(matricula, materia, n1, n2, nF, obs);
        System.out.println("Calificacion y materia real agregadas exitosamente.");
    }
}