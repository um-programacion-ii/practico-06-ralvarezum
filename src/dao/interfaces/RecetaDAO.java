package dao.interfaces;

import entidades.Receta;

import java.util.List;

public interface RecetaDAO {
    String agregarReceta(Receta receta);
    Receta obtenerReceta(int id);
    List<Receta> listarRecetas();
}