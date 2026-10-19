package view;

import model.domain.Estudiante;
import service.EstudianteService;
import utils.ConsoleUtils;

public class MenuColaView {
    
    private final EstudianteService estudianteService;

    public MenuColaView(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = ConsoleUtils.leerEntero("Elija una opción: ");
            switch (opcion) {
                case 1 -> registrarLlegada();
                case 2 -> atender();
                case 3 -> consultarSiguiente();
                case 4 -> verSiHayEnEspera();
                case 5 -> contar();
                case 6 -> listar();
                case 0 -> System.out.println("Saliendo del menú de la fila de matrícula...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println("\n--- FILA DE SOLICITUDES DE MATRÍCULA ---");
        System.out.println("1. Registrar llegada de estudiante (enqueue)");
        System.out.println("2. Atender la siguiente solicitud (dequeue)");
        System.out.println("3. Ver de quién es el siguiente turno (front)");
        System.out.println("4. ¿Hay estudiantes en la fila? (isEmpty)");
        System.out.println("5. Cantidad de estudiantes en espera (size)");
        System.out.println("6. Listar la fila (Parte 7)");
        System.out.println("0. Salir");
    }

    private void registrarLlegada() {
        String identificacion = ConsoleUtils.leerTexto("Identificación del estudiante: ");
        
        // TODO 1 resuelto
        estudianteService.registrarSolicitud(identificacion);
        System.out.println("El estudiante ha sido registrado en la fila exitosamente.");
    }

    private void atender() {
        // TODO 2 resuelto: chequeo preventivo sin usar try/catch
        if (estudianteService.sinSolicitudesEnEspera()) {
            System.out.println("No hay estudiantes en espera para ser atendidos.");
            return;
        }
        
        Estudiante atendido = estudianteService.procesarSiguienteSolicitud();
        System.out.println("Se está atendiendo la solicitud de: " + atendido.getNombre());
    }

    private void consultarSiguiente() {
        // TODO 3 resuelto
        if (estudianteService.sinSolicitudesEnEspera()) {
            System.out.println("No hay estudiantes en espera.");
            return;
        }
        
        Estudiante siguiente = estudianteService.consultarSiguienteSolicitud();
        System.out.println("El siguiente turno en la fila es para: " + siguiente.getNombre());
    }

    private void verSiHayEnEspera() {
        // TODO 4 resuelto
        if (estudianteService.sinSolicitudesEnEspera()) {
            System.out.println("Estado de la fila: No hay estudiantes en espera.");
        } else {
            System.out.println("Estado de la fila: Sí hay estudiantes esperando turno.");
        }
    }

    private void contar() {
        // TODO 5 resuelto
        int cantidad = estudianteService.contarSolicitudesEnEspera();
        System.out.println("Cantidad de estudiantes actualmente en la fila: " + cantidad);
    }

    private void listar() {
        // TODO 6: se completa en la Parte 7, cuando exista listarEnEspera en el Service
    }
}