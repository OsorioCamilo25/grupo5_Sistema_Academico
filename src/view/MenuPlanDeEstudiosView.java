package view;

import service.PlandeEstudioService;
import utils.ConsoleUtils;

public class MenuPlanDeEstudiosView {
    
    private final PlandeEstudioService planService;

    public MenuPlanDeEstudiosView(PlandeEstudioService planService) {
        this.planService = planService;
        System.out.println("\n--- ESTADO DE CARGA DE ARCHIVOS ---");
        System.out.println(planService.consultarEstadoCarga());
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = ConsoleUtils.leerEntero("Elija una opción: ");
            switch (opcion) {
                case 1 -> registrarEnPlan();
                case 2 -> System.out.println(planService.listarMaterias());
                case 3 -> System.out.println(planService.listarMateriasInverso());
                case 4 -> hojearAnterior();
                case 5 -> hojearSiguiente();
                case 6 -> retirarMateria();
                case 7 -> guardarArchivos();
                case 0 -> {
                    guardarArchivos();
                    System.out.println("Saliendo del menú del Plan de Estudios...");
                }
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println("\n--- MENÚ DEL PLAN DE ESTUDIOS (LISTA DOBLE) ---");
        System.out.println("1. Agregar materia al plan (Insertar al final)");
        System.out.println("2. Listar del primer semestre al último");
        System.out.println("3. Listar del último semestre al primero");
        System.out.println("4. Hojear materia anterior");
        System.out.println("5. Hojear materia siguiente");
        System.out.println("6. Retirar la materia que estoy mirando");
        System.out.println("7. Guardar en archivos");
        System.out.println("0. Guardar y volver al menú principal");
    }

private void registrarEnPlan() {
        String codigo = ConsoleUtils.leerTexto("Código de la materia a agregar al plan: ");
        
        if (codigo == null || codigo.isBlank()) {
            System.out.println("Error: El código no puede estar vacío.");
            return;
        }
        if (!planService.existeMateriaRegistrada(codigo)) {
            System.out.println("-> La materia no existe en el sistema general. Vamos a registrarla primero.");
            
            String nombre = ConsoleUtils.leerTexto("Nombre de la nueva materia: ");
            if (nombre == null || nombre.isBlank()) {
                System.out.println("Error: El nombre es obligatorio. Registro cancelado.");
                return;
            }

            int creditos = ConsoleUtils.leerEntero("Cantidad de créditos: ");
            if (creditos <= 0) {
                System.out.println("Error: Los créditos deben ser mayores a cero. Registro cancelado.");
                return;
            }
            System.out.println(planService.registrarMateria(codigo, nombre, creditos));
        }
        if (planService.estaMateriaEnPlan(codigo)) {
            System.out.println("Error: La materia ya se encuentra dentro del plan de estudios.");
            return;
        }
        String resultado = planService.agregarMateriaAlPlan(codigo);
        System.out.println(resultado);
    }
    private void hojearAnterior() {
        if (planService.contarMaterias() == 0) {
            System.out.println("El plan de estudios está vacío.");
            return;
        }
        if (!planService.hayMateriaAnterior()) {
            System.out.println("No hay una materia anterior (estás al inicio).");
            return;
        }
        
        System.out.println("Materia hojeada (Anterior): \n" + planService.verMateriaAnterior());
    }

    private void hojearSiguiente() {
        if (planService.contarMaterias() == 0) {
            System.out.println("El plan de estudios está vacío.");
            return;
        }
        if (!planService.hayMateriaSiguiente()) {
            System.out.println("No hay una materia siguiente (estás al final).");
            return;
        }
        
        System.out.println("Materia hojeada (Siguiente): \n" + planService.verMateriaSiguiente());
    }

    private void retirarMateria() {
        if (planService.contarMaterias() == 0) {
            System.out.println("El plan de estudios está vacío, no hay nada que retirar.");
            return;
        }
        try {
            System.out.println(planService.retirarMateriaDelPlan());
        } catch (java.util.NoSuchElementException e) {
            System.out.println("No hay ninguna materia seleccionada. Utilice hojear primero.");
        }
    }

    private void guardarArchivos() {
        System.out.println(planService.guardarMateriasRegistradas());
        System.out.println(planService.guardarPlanDeEstudios());
    }
}