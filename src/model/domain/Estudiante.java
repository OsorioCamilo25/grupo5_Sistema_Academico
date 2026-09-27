package model.domain;
import model.structures.ListaSimple;

public class Estudiante extends Persona{
    private String codigo;
    private int semestreActual;
    private ListaSimple<Matricula> matricula;

    public Estudiante(String identificacion, String nombre, String correo, String codigo, int semestreActual){
        super(identificacion, nombre, correo);
        this.codigo = codigo;
        this.semestreActual = semestreActual;
        this.matricula = new ListaSimple<>();

    }
    
    @Override 
    public String identificarRol() {
        return "Estudiante";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código no puede estar vacío");
        }
        this.codigo = codigo;
    }

    public int getSemestreActual() {
        return semestreActual;
    }

    public void setSemestreActual(int semestreActual) {
    if (semestreActual <= 0) {
        throw new IllegalArgumentException("El semestre debe ser mayor a 0");
    }
    this.semestreActual = semestreActual;
}

     public ListaSimple<Matricula> getMatricula(){
        return matricula;
    }
    
    @Override
    public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Estudiante otro = (Estudiante) obj;
    return codigo != null && codigo.equals(otro.codigo);
    }

        @Override   
        public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
          }
}
