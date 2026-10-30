package model.domain;
import  model.structures.ListaDoble;

public class PlanDeEstudios {
    private String programa;
    private ListaDoble<Materia> materias;

    public PlanDeEstudios(String programa){
        if (programa == null || programa.isBlank()) {
            throw new IllegalArgumentException("El nombre del plan de estidos no puede estar vacio");
        }
        this.programa = programa;
        this.materias = new ListaDoble<Materia>();
    }

    
    public String getPrograma() {
        return programa;
    }

    public ListaDoble<Materia> getMaterias() {
        return materias;
    }
    
}

