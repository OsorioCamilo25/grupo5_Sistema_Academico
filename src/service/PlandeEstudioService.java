package service;

import model.domain.Materia;
import model.domain.PlanDeEstudios;
import model.structures.ListaSimple;
import model.structures.Nodo;
import model.structures.NodoDoble;

public class PlandeEstudioService {
    
    private final PlanDeEstudios plan;

   
    private final ListaSimple<Materia> materiasRegistradas;

    public PlandeEstudioService(String programa) {
        plan = new PlanDeEstudios(programa);
        materiasRegistradas = new ListaSimple<>();
    }

    
    public String registrarMateria(
            String codigo, String nombre, int creditos) {

        if (codigo == null || codigo.isBlank()
                || nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El codigo y el nombre son obligatorios.");
        }

        if (creditos <= 0) {
            throw new IllegalArgumentException(
                    "Los creditos deben ser positivos.");
        }

        if (buscarMateriaPorCodigo(codigo) != null) {
            throw new IllegalArgumentException(
                    "Ya existe una materia con ese codigo.");
        }

        Materia nueva = new Materia(codigo, nombre, creditos);
        materiasRegistradas.insertarFinal(nueva);

        return "Materia registrada: " + nombre;
    }

    
    private Materia buscarMateriaPorCodigo(String codigo) {
        Nodo<Materia> actual = materiasRegistradas.getHead();

        while (actual != null) {
            Materia materia = actual.getDato();

            if (materia.getCodigo().equals(codigo)) {
                return materia;
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    public String agregarMateriaAlPlan(String codigo) {
        Materia materia = buscarMateriaPorCodigo(codigo);

        if (materia == null) {
            throw new IllegalArgumentException(
                    "No existe una materia con el codigo: " + codigo);
        }

        NodoDoble<Materia> actual = plan.getMaterias().getHead();

        while (actual != null) {
            if (actual.getDato().getCodigo().equals(codigo)) {
                throw new IllegalArgumentException(
                        "La materia ya esta en el plan.");
            }

            actual = actual.getSiguiente();
        }

        plan.getMaterias().insertarAlFinal(materia);

        return "Materia agregada al plan: " + materia.getNombre();
    }

    public String listarMaterias() {
        if (plan.getMaterias().isEmpty()) {
            return "El plan no tiene materias.";
        }

        return plan.getMaterias().recorrerAdelante();
    }

    public String listarMateriasInverso() {
        if (plan.getMaterias().isEmpty()) {
            return "El plan no tiene materias.";
        }

        return plan.getMaterias().recorrerAtras();
    }

    public String verMateriaAnterior() {
        return plan.getMaterias().anterior().toString();
    }

    public String verMateriaSiguiente() {
        return plan.getMaterias().siguiente().toString();
    }

    public String retirarMateriaDelPlan() {
        Materia retirada = plan.getMaterias().retirarActual();

        return "Materia retirada del plan: " + retirada.getNombre();
    }

    public int contarMaterias() {
        return plan.getMaterias().size();
    }

    public boolean existeMateriaRegistrada(String codigo) {
        return buscarMateriaPorCodigo(codigo) != null;
    }
}

