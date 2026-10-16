package model.domain;

import model.structures.Cola;

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

    public Cola<Estudiante> getFila(){
        return fila;
    }
}
