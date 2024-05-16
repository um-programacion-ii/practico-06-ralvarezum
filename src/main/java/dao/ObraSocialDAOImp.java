package dao;

import dao.interfaces.ObraSocialDAO;
import entidades.ObraSocial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObraSocialDAOImp implements ObraSocialDAO {
    private Map<String, ObraSocial> obrassociales;

    public ObraSocialDAOImp() {
        obrassociales = new HashMap<>();
    }

    @Override
    public String agregarObraSocial(ObraSocial obrasocial) {
        obrassociales.put(obrasocial.getNombre(), obrasocial);
        return "Obra Social agregada con éxito";
    }

    @Override
    public ObraSocial obtenerObraSocial(String nombre) {
        return obrassociales.get(nombre);
    }

    @Override
    public List<ObraSocial> listarObrasSociales() {
        return new ArrayList<>(obrassociales.values());
    }

    @Override
    public String eliminarObraSocial(String nombre) {
        obrassociales.remove(nombre);
        return "Obra Social eliminada con éxito";
    }

    @Override
    public String actualizarObraSocial(ObraSocial obrasocial) {
        obrassociales.put(obrasocial.getNombre(), obrasocial);
        return "Obra Social actualizada con éxito";
    }
}