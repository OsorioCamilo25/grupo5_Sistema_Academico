import service.EstudianteService;
import service.PlandeEstudioService;

public class PruebaArchivos {
    public static void main(String[] args) {
        PlandeEstudioService plan = new PlandeEstudioService("Desarrollo de Software");
        EstudianteService estudiantes = new EstudianteService();
        System.out.println(plan.consultarEstadoCarga());
        System.out.println(estudiantes.consultarEstadoCarga());

        
        if (args.length > 0 && args[0].equals("registrar")) {
            String[] codigos = {"MAT01", "MAT02", "MAT03"};
            String[] nombres = {"Calculo", "Programacion", "Fisica"};
            for (int i = 0; i < codigos.length; i++) {
                if (!plan.existeMateriaRegistrada(codigos[i])) {
                    plan.registrarMateria(codigos[i], nombres[i], 3);
                }
                
            }
            if (plan.contarMaterias() == 0) {
                for (String codigo : codigos) plan.agregarMateriaAlPlan(codigo);
            }
            if (estudiantes.buscarPorIdentificacion("1001") == null) {
                estudiantes.crearEstudiante("1001", "Ana", "ana@ejemplo.com", "EST01", 4);
            }
            System.out.println(plan.guardarMateriasRegistradas());
            System.out.println(plan.guardarPlanDeEstudios());
            System.out.println(estudiantes.guardarEstudiantes());
        }
        System.out.println("ORDEN NORMAL:\n" + plan.listarMaterias());
        System.out.println("ORDEN INVERSO:\n" + plan.listarMateriasInverso());
        System.out.println("Estudiantes cargados: " + estudiantes.contarEstudiantes());
    }
}
