package model.domain;

public class Estudiante extends Persona{
    private String codigo;
    private int semestreActual;

    public Estudiante(String identificacion, String nombre, String correo, String codigo, int semestreActual){
        super(identificacion, nombre, correo);
        this.codigo = codigo;
        this.semestreActual = semestreActual;
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
    
}
