package dao;

import dao.interfaces.MedicamentoDAO;
import entidades.Medicamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicamentoDAOImp implements MedicamentoDAO {
    private Map<Integer, Medicamento> medicamentos;

    public MedicamentoDAOImp() {
        medicamentos = new HashMap<>();
    }

    @Override
    public String agregarMedicamento(Medicamento medicamento) {
        medicamentos.put(medicamento.getId(), medicamento);
        return "Medicamento agregado con exito";
    }

    @Override
    public Medicamento obtenerMedicamento(int id) {
        return medicamentos.get(id);
    }

    @Override
    public List<Medicamento> listarMedicamentos() {
        return new ArrayList<>(medicamentos.values());
    }

    @Override
    public String eliminarMedicamento(int id) {
        medicamentos.remove(id);
        return "Medicamento eliminado con exito";
    }

    @Override
    public String actualizarMedicamento(Medicamento medicamento) {
        medicamentos.put(medicamento.getId(), medicamento);
        return "Medicamento actualizado con exito";
    }
}