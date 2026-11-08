package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.domain.Calificacion;
import model.domain.Materia;
import model.domain.Matricula;
import model.domain.PlanDeEstudios;
import model.domain.ProcesoMatricula;
import model.structures.ListaDoble;
import model.structures.ListaSimple;
import model.structures.NodoDoble;

public class MatriculaService {

    private static final String ARCHIVO_MATERIAS = "data/productos.txt";
    private static final String SEPARADOR = ";";
    private final PlanDeEstudios materias = new PlanDeEstudios("Materias");



    public Calificacion agregarCalificacion(Matricula matricula, Materia materia, 
                                            double notaParcial1, double notaParcial2,
                                            double notaFinal, String observaciones) {
        Calificacion nuevaCalificacion = matricula.registrarCalificacion(materia, notaParcial1, notaParcial2, notaFinal, observaciones);
        materia.getCalificacionesRecientes().push(nuevaCalificacion);
        
        return nuevaCalificacion;
    }

    public Calificacion buscarCalificacion(Matricula matricula, Calificacion calificacion) {
        return matricula.getCalificaciones().buscarPorValor(calificacion);
    }

    public boolean eliminarCalificacion(Matricula matricula, Calificacion calificacion) {
        return matricula.eliminarCalificacion(calificacion);
    }

    public ListaSimple<Calificacion> listarCalificaciones(Matricula matricula) {
        return matricula.getCalificaciones();
    }

    public Calificacion deshacerUltimaNota(Materia materia) {
        return materia.getCalificacionesRecientes().pop();
    }

    public Calificacion consultarUltimaNota(Materia materia) {
        return materia.getCalificacionesRecientes().peek();
    }

    public boolean sinNotasPorDeshacer(Materia materia) {
        return materia.getCalificacionesRecientes().isEmpty();
    }

    public int contarNotas(Materia materia) {
        return materia.getCalificacionesRecientes().size();
    }  

    public String listarHistorialNotas(Materia materia) {
        model.structures.Pila<Calificacion> original = materia.getCalificacionesRecientes();
        
        if (original.isEmpty()) {
            return "No hay calificaciones en el historial por deshacer.";
        }

        model.structures.Pila<Calificacion> auxiliar = new model.structures.Pila<>();
        StringBuilder sb = new StringBuilder();
        int contador = 1;

        while (!original.isEmpty()) {
            Calificacion nota = original.pop();
            sb.append(contador).append(". ").append(nota.toString()).append("\n");
            auxiliar.push(nota);
            contador++;
        }

        while (!auxiliar.isEmpty()) {
            original.push(auxiliar.pop());
        }

        return sb.toString();
    }

    private void escribirMaterias() throws IOException {
        File carpeta = new File(ARCHIVO_MATERIAS).getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }


        String texto = "";
        NodoDoble<Materia> actual = materias.getMaterias().getHead();
        while (actual != null) {
            Materia programa = actual.getDato();
            texto += String.join(SEPARADOR,
                    programa.getCodigo(),
                    programa.getNombre(),
                   String.valueOf(programa.getCreditos()))
                    + "\n";
            actual = actual.getSiguiente();
        }

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO_MATERIAS))) {
            escritor.write(texto);
        }

    }

    public String guardarProductos() {
        try {
            escribirMaterias();
            return "Se guardaron " + materias.getMaterias().size() + " productos en " + ARCHIVO_MATERIAS;
        } catch (IOException e) {
            return "No se pudo guardar: " + e.getMessage();
        }
    }

    public String cargarProductos() {
        File archivo = new File(ARCHIVO_MATERIAS);
        if (!archivo.isFile()) {
            return "No existe el archivo: " + ARCHIVO_MATERIAS;
        }

        
        ListaDoble<Materia> leidas = new ListaDoble<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numeroLinea = 0;
            while ((linea = lector.readLine()) != null) {
                numeroLinea++;
                if (linea.isBlank()) continue;
                String[] campos = linea.split(SEPARADOR, -1);
                if (campos.length != 3 || campos[0].isBlank() || campos[1].isBlank()) {
                    return "Formato incorrecto en la linea " + numeroLinea;
                }
                int creditos;
                try {
                    creditos = Integer.parseInt(campos[2].trim());
                } catch (NumberFormatException e) {
                    return "Creditos invalidos en la linea " + numeroLinea;
                }
                if (creditos <= 0) {
                    return "Los creditos deben ser positivos en la linea " + numeroLinea;
                }
                leidas.insertarAlFinal(new Materia(campos[0], campos[1], creditos));
            }
        } catch (IOException e) {
            return "No se pudo cargar: " + e.getMessage();
        }

        while (materias.getMaterias().getHead() != null) {
            materias.getMaterias().eliminar(materias.getMaterias().getHead());
        }
        NodoDoble<Materia> actual = leidas.getHead();
        while (actual != null) {
            materias.getMaterias().insertarAlFinal(actual.getDato());
            actual = actual.getSiguiente();
        }
        return "Se cargaron " + materias.getMaterias().size() + " materias.";
    }
}