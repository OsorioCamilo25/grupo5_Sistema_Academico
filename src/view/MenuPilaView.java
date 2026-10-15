package view;

import model.domain.Calificacion;
import model.domain.Materia;
import service.MatriculaService;
import utils.ConsoleUtils;

public class MenuPilaView {
    
    private final MatriculaService matriculaService;
    private final Materia materiaPrueba; 

    public MenuPilaView(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
        this.materiaPrueba = new Materia("MAT-PILA", "Materia de Prueba Pila", 3);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = ConsoleUtils.leerEntero("Elija una opción: ");
            switch (opcion) {
                case 1 -> registrar();
                case 2 -> deshacer();
                case 3 -> consultarUltima();
                case 4 -> verSiHayOperaciones();
                case 5 -> contar();
                case 6 -> listar();
                case 0 -> System.out.println("Saliendo del menú de pilas...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println("\n--- MENÚ PRUEBA DE PILA (MATERIA: " + materiaPrueba.getNombre() + ") ---");
        System.out.println("1. Registrar calificación (push)");
        System.out.println("2. Deshacer última calificación (pop)");
        System.out.println("3. Ver última calificación (peek)");
        System.out.println("4. ¿Hay calificaciones por deshacer? (isEmpty)");
        System.out.println("5. Cantidad de calificaciones en historial (size)");
        System.out.println("6. Listar historial de calificaciones (Parte 7)");
        System.out.println("0. Volver al menú principal");
    }

    private void registrar() {
        double notaFinal = ConsoleUtils.leerDecimal("Ingrese la nota final a registrar: ");
        Calificacion nuevaCalif = new Calificacion(materiaPrueba, 0, 0, notaFinal, "Prueba Pila");
        
        materiaPrueba.getCalificacionesRecientes().push(nuevaCalif);
        System.out.println("Calificación apilada exitosamente.");
    }

    private void deshacer() {
        if (matriculaService.sinNotasPorDeshacer(materiaPrueba)) {
            System.out.println("No hay calificaciones por deshacer.");
            return;
        }
        Calificacion deshecha = matriculaService.deshacerUltimaNota(materiaPrueba);
        System.out.println("Calificación deshecha: " + deshecha.toString());
    }

    private void consultarUltima() {
        if (matriculaService.sinNotasPorDeshacer(materiaPrueba)) {
            System.out.println("No hay calificaciones en el historial.");
            return;
        }
        Calificacion ultima = matriculaService.consultarUltimaNota(materiaPrueba);
        System.out.println("Última calificación registrada: " + ultima.toString());
    }

    private void verSiHayOperaciones() {
        if (matriculaService.sinNotasPorDeshacer(materiaPrueba)) {
            System.out.println("Estado: NO hay calificaciones por deshacer.");
        } else {
            System.out.println("Estado: SÍ hay calificaciones en el historial.");
        }
    }

    private void contar() {
        int cantidad = matriculaService.contarNotas(materiaPrueba);
        System.out.println("Cantidad de calificaciones en el historial: " + cantidad);
    }

    private void listar() {
        String listado = matriculaService.listarHistorialNotas(materiaPrueba);
        System.out.println("\nHistorial de notas:");
        System.out.println(listado);
    }
}