package model.domain;
import  model.structures.ListaDoble;

public class PlanDeEstudios {
    private String programa;
    private ListaDoble<Materia> materias;

    public PlanDeEstudios(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del plan de estudios no puede estar vacío");
        }
        this.programa = nombre;
        this.materias = new ListaDoble<Materia>();
    }


    public String getPrograma() {
        return programa;
    }
    public ListaDoble<Materia> getMaterias() {
        return materias;
    }
}
