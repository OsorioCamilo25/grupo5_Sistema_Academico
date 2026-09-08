package model.domain;

public abstract class Persona implements RolAcademico {
    private String identificacion;
    private String nombre;
    private String correo; 

    public Persona(String identificacion, String nombre, String correo){
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }
    
    @Override
    public String datosResumen() {
        return "Identificación: " + identificacion + ", Nombre: " + nombre + ", Correo: " + correo;
    } 

}
