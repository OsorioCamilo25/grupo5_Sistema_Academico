package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.domain.Materia;
import model.domain.PlanDeEstudios;
import model.structures.ListaSimple;
import model.structures.Nodo;
import model.structures.NodoDoble;

public class PlandeEstudioService {
    
   private final PlanDeEstudios plan;
private final ListaSimple<Materia> materiasRegistradas;
private String estadoCarga;

public PlandeEstudioService(String programa) {
    plan = new PlanDeEstudios(programa);
    materiasRegistradas = new ListaSimple<>();

    estadoCarga = cargarMateriasRegistradas()
            + "\n" + cargarPlanDeEstudios();
}

public String consultarEstadoCarga() {
    return estadoCarga;
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
    public String guardarMateriasRegistradas() {
        File archivo = new File("data/materias.txt");
        File carpeta = archivo.getParentFile();

        try {
            if (!carpeta.isDirectory() && !carpeta.mkdirs()) {
                return "No se pudo crear data/.";
            }

            try (BufferedWriter escritor =
                    new BufferedWriter(new FileWriter(archivo))) {

                Nodo<Materia> actual = materiasRegistradas.getHead();

                while (actual != null) {
                    Materia materia = actual.getDato();

                    escritor.write(String.join(";",
                            materia.getCodigo(),
                            materia.getNombre(),
                            String.valueOf(materia.getCreditos())));

                    escritor.newLine();
                    actual = actual.getSiguiente();
                }
            }

            return "Se guardaron "
                    + materiasRegistradas.getTamano()
                    + " materias registradas.";

        } catch (IOException e) {
            return "No se pudo guardar materias: " + e.getMessage();
        }
    }

    public String cargarMateriasRegistradas() {
        while (plan.getMaterias().getHead() != null) {
            plan.getMaterias().eliminar(plan.getMaterias().getHead());
        }
        while (!materiasRegistradas.estaVacia()) materiasRegistradas.eliminarInicio();
        int omitidas = 0;
        try (BufferedReader lector = new BufferedReader(
                new FileReader("data/materias.txt"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(";", -1);
                if (campos.length != 3) {
                    omitidas++;
                    continue;
                }
                try {
                    registrarMateria(campos[0], campos[1], Integer.parseInt(campos[2]));
                } catch (IllegalArgumentException e) {
                    omitidas++;
                    continue;
                }
            }
            return "Se cargaron " + materiasRegistradas.getTamano() + " materiasregistradas"
                    + ". Lineas omitidas: " + omitidas;
        } catch (FileNotFoundException e) {
            return "No se encontro data/materias.txt; la estructura queda vacia.";
        } catch (IOException e) {
            return "No se pudo leer data/materias.txt: " + e.getMessage();
        }
    }

    public String guardarPlanDeEstudios() {
        File archivo = new File("data/plan.txt");
        File carpeta = archivo.getParentFile();
        try {
            if (!carpeta.isDirectory() && !carpeta.mkdirs()) {
                return "No se pudo crear data/.";
            }
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
                NodoDoble<Materia> actual = plan.getMaterias().getHead();
                while (actual != null) {
                    escritor.write(actual.getDato().getCodigo());
                    escritor.newLine();
                    actual = actual.getSiguiente();
                }
            }
            return "Se guardaron " + plan.getMaterias().size() + " materias en el plan de estudios.";
        } catch (IOException e) {
            return "No se pudo guardar plan de estudios: " + e.getMessage();
        }
    }

    public String cargarPlanDeEstudios() {
        while (plan.getMaterias().getHead() != null) {
            plan.getMaterias().eliminar(plan.getMaterias().getHead());
        }
        int omitidas = 0;
        try (BufferedReader lector = new BufferedReader(new FileReader("data/plan.txt"))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    omitidas++;
                    continue;
                }
                try {
                    agregarMateriaAlPlan(linea.trim());
                } catch (IllegalArgumentException e) {
                    omitidas++;
                    continue;
                }
            }
            return "Se cargaron " + plan.getMaterias().size() + " materias al plan de estudios. Lineas omitidas: " + omitidas;
        } catch (FileNotFoundException e) {
            return "No se encontro data/plan.txt; la estructura queda vacia.";
        } catch (IOException e) {
            return "No se pudo leer data/plan.txt: " + e.getMessage();
        }
    }

    public boolean estaMateriaEnPlan(String codigo) {
        NodoDoble<Materia> actual = plan.getMaterias().getHead();
        while (actual != null) {
            if (actual.getDato().getCodigo().equals(codigo)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public boolean hayMateriaAnterior() {
        try {
            plan.getMaterias().anterior();
            plan.getMaterias().siguiente(); 
            return true;
        } catch (java.util.NoSuchElementException e) {
            return false;
        }
    }

    public boolean hayMateriaSiguiente() {
        try {
            plan.getMaterias().siguiente();
            plan.getMaterias().anterior(); 
            return true;
        } catch (java.util.NoSuchElementException e) {
            return false;
        }
    }

}

