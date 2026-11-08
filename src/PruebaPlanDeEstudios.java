import service.PlandeEstudioService;
public class PruebaPlanDeEstudios {
    public static void main(String[] args) {
        PlandeEstudioService service =
                new PlandeEstudioService("Desarrollo de Software");

        
        service.registrarMateria("MAT01", "Calculo", 3);
        service.registrarMateria("MAT02", "Programacion", 4);
        service.registrarMateria("MAT03", "Estructura de Datos", 3);
        service.registrarMateria("MAT04", "Fisica", 3);

        
        service.agregarMateriaAlPlan("MAT01");
        service.agregarMateriaAlPlan("MAT02");
        service.agregarMateriaAlPlan("MAT03");
        service.agregarMateriaAlPlan("MAT04");

        
        System.out.println("ORDEN NORMAL:");
        System.out.println(service.listarMaterias());

        System.out.println("ORDEN INVERSO:");
        System.out.println(service.listarMateriasInverso());

        
        System.out.println("PRIMERA CONSULTA HACIA ATRAS:");
        System.out.println(service.verMateriaAnterior());

        System.out.println("SEGUNDA CONSULTA HACIA ATRAS:");
        System.out.println(service.verMateriaAnterior());

        
        System.out.println(service.retirarMateriaDelPlan());

        
        System.out.println("PLAN DESPUES DEL RETIRO:");
        System.out.println(service.listarMaterias());

        System.out.println("Cantidad: " + service.contarMaterias());

        System.out.println("MAT03 sigue registrada: "
                + service.existeMateriaRegistrada("MAT03"));
    
    try {
    service.retirarMateriaDelPlan();
    System.out.println("ERROR: permitio retirar sin seleccion.");
} catch (java.util.NoSuchElementException e) {
    System.out.println("Correcto: " + e.getMessage());
}

// Sin selección, siguiente() selecciona la primera materia.
System.out.println(service.verMateriaSiguiente());

try {
    service.verMateriaAnterior();
    System.out.println("ERROR: permitio retroceder desde la primera.");
} catch (java.util.NoSuchElementException e) {
    System.out.println("Correcto: " + e.getMessage());
}

// Avanzamos hasta la última de las tres materias.
service.verMateriaSiguiente();
service.verMateriaSiguiente();

try {
    service.verMateriaSiguiente();
    System.out.println("ERROR: permitio avanzar desde la ultima.");
} catch (java.util.NoSuchElementException e) {
    System.out.println("Correcto: " + e.getMessage());

}
try {
    service.agregarMateriaAlPlan("NO_EXISTE");
    System.out.println("ERROR: agrego una materia inexistente.");
} catch (IllegalArgumentException e) {
    System.out.println("Correcto: " + e.getMessage());
}
}
}
