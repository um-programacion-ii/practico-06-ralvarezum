package dao;

import dao.interfaces.MedicoDAO;
import entidades.Medico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicoDAOImp implements MedicoDAO {
    private Map<Integer, Medico> medicos;

    public MedicoDAOImp() {
        medicos = new HashMap<>();
    }

    @Override
    public String agregarMedico(Medico medico) {
        medicos.put(medico.getDocumento(), medico);
        return "Medico agregado con exito";
    }

    @Override
    public Medico obtenerMedico(int documento) {
        return medicos.get(documento);
    }

    @Override
    public List<Medico> listarMedicos() {
        return new ArrayList<>(medicos.values());
    }

    @Override
    public String eliminarMedico(int documento) {
        medicos.remove(documento);
        return "Medico eliminado con exito";
    }

    @Override
    public String actualizarMedico(Medico medico) {
        medicos.put(medico.getDocumento(), medico);
        return "Medico actualizado con exito";
    }

    @Override
    public String limpiarDatos() {
        medicos.clear();
        return "Medicos eliminados con exito";
    }
}