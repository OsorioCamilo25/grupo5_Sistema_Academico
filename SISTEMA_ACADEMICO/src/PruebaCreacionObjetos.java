import model.domain.Estudiante;
import model.domain.Profesor;
import model.domain.Persona;
import model.domain.Matricula;

public class PruebaCreacionObjetos {
    public static void main(String[] args) {
        Estudiante estudiante = new Estudiante("123", "Valentina Zapata", "valenz@itm.edu.co", "E1", 4);
        Profesor profesor = new Profesor("456", "Andres Restrepo", "andres@itm.edu.co", "P1", "Sistemas");


        Matricula matricula = new Matricula();
        
        Persona[] personas = { estudiante, profesor };

        for (Persona persona : personas) {
          
            System.out.println(persona.datosResumen() + " | Rol: " + persona.identificarRol());
        }
    }
}