package model.domain;

public class ProcesoMatricula {
    private String semestre;
    private Cola<Estudiante> fila;

    public ProcesoMatricula(String semestre) {
		this.semestre = semestre;
		this.fila = new Cola<>();
	}

	public String getSemestre(){
        return semestre;
    }

    public Cola<T> getFila(){
        return fila;
    }
}
