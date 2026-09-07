package model.domain;

public class Profesor extends Persona {
    private String codigo;
    private String departamento;

    
    public Profesor(String codigo, String departamento) {
        super(identificacion,nombre,correo);
        this.codigo = codigo;
        this.departamento = departamento;
    }

    @Override
    public String identificarRol() {
        return "Profesor del departamento de " + departamento + " con código " + codigo;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        if (departamento == null || departamento.isBlank()) {
            throw new IllegalArgumentException("El departamento no puede estar vacío");
        }
        this.departamento = departamento;
    }

    
}
