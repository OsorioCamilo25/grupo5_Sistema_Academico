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
        this.codigo = codigo;
    }

    public int getSemestreActual() {
        return semestreActual;
    }

    public void setSemestreActual(int semestreActual) {
        this.semestreActual = semestreActual;
    }
    
}
