import model.domain.Materia;
import model.service.MatriculaService;
import view.MenuView;

public class App {
    public static void main(String[] args) throws Exception {
        new MenuView().iniciar();

        Materia javaMateria = new Materia("MAT-01", "Programación II", 3);
MatriculaService service = new MatriculaService();

// Registrar notas (Push)
service.registrarCalificacion(javaMateria, 3.5);
service.registrarCalificacion(javaMateria, 4.2);

// Consultar la última nota sin sacarla (Peek)
System.out.println("Última nota: " + service.consultarUltimaNota(javaMateria)); // Debe imprimir 4.2

// Deshacer nota (Pop)
System.out.println("Nota deshecha: " + service.deshacerUltimaNota(javaMateria)); // Debe imprimir 4.2
System.out.println("Nota actual tras deshacer: " + service.consultarUltimaNota(javaMateria)); // Debe imprimir 3.5
    }
}