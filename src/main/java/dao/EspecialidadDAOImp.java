package dao;

import dao.interfaces.EspecialidadDAO;
import entidades.Especialidad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EspecialidadDAOImp implements EspecialidadDAO {
    private Map<String, Especialidad> especialidades;

    public EspecialidadDAOImp() {
        especialidades = new HashMap<>();
    }

    @Override
    public String agregarEspecialidad(Especialidad especialidad) {
        especialidades.put(especialidad.getNombre(), especialidad);
        return "Especialidad agregada con éxito";
    }

    @Override
    public Especialidad obtenerEspecialidad(String nombre) {
        return especialidades.get(nombre);
    }

    @Override
    public List<Especialidad> listarEspecialidades() {
        return new ArrayList<>(especialidades.values());
    }

    @Override
    public String eliminarEspecialidad(String nombre) {
        especialidades.remove(nombre);
        return "Especialidad eliminada con éxito";
    }

    @Override
    public String actualizarEspecialidad(Especialidad especialidad) {
        especialidades.put(especialidad.getNombre(), especialidad);
        return "Especialidad actualizada con éxito";
    }
}