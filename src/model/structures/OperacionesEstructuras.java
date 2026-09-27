package model.structures;

public interface OperacionesEstructuras<T> {
    void crear(int indice, T dato);

    T buscarPorIndice(int indice);

    T buscarPorValor(T dato);

    void actualizar(int indice, T dato);

    boolean eliminar(T dato);
}
