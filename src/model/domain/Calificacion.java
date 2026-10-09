package model.domain;


public class Calificacion {

   
    private double notaParcial1;
    private double notaParcial2;
    private double notaFinal;
    private String observaciones;

    
    private Materia materia;

   
    public Calificacion(Materia materia, double notaParcial1, double notaParcial2,
                 double notaFinal, String observaciones) {
        this.materia = materia;
        this.notaParcial1 = notaParcial1;
        this.notaParcial2 = notaParcial2;
        this.notaFinal = notaFinal;
        this.observaciones = observaciones;
    }

    

    public double getNotaParcial1() {
        return notaParcial1;
    }

    public void setNotaParcial1(double notaParcial1) {
        this.notaParcial1 = notaParcial1;
    }

    public double getNotaParcial2() {
        return notaParcial2;
    }

    public void setNotaParcial2(double notaParcial2) {
        this.notaParcial2 = notaParcial2;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    
    public double calcularPromedio() {
        return (notaParcial1 + notaParcial2 + notaFinal) / 3.0;
    }

    
    @Override
    public String toString() {
        return "Calificacion [materia=" + materia.getNombre() +
                ", notaParcial1=" + notaParcial1 +
                ", notaParcial2=" + notaParcial2 +
                ", notaFinal=" + notaFinal +
                ", promedio=" + calcularPromedio() +
                ", observaciones=" + observaciones + "]";
    }

    @Override
    public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Calificacion otra = (Calificacion) obj;
    return materia != null && materia.equals(otra.materia)
            && Double.compare(notaParcial1, otra.notaParcial1) == 0
            && Double.compare(notaParcial2, otra.notaParcial2) == 0
            && Double.compare(notaFinal, otra.notaFinal) == 0;
    }

    @Override
    public int hashCode() {
    int result = materia != null ? materia.hashCode() : 0;
    result = 31 * result + Double.hashCode(notaParcial1);
    result = 31 * result + Double.hashCode(notaParcial2);
    result = 31 * result + Double.hashCode(notaFinal);
    return result;
    }
}
