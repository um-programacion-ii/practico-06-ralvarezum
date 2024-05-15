package dao;

import dao.interfaces.TurnoDAO;
import entidades.Turno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurnoDAOImp implements TurnoDAO {
    private Map<Integer, Turno> turnos;

    public TurnoDAOImp() {
        turnos = new HashMap<>();
    }

    @Override
    public String agregarTurno(Turno turno) {
        turnos.put(turno.getId(), turno);
        return "Turno agregado con exito";
    }

    @Override
    public Turno obtenerTurno(int id) {
        return turnos.get(id);
    }

    @Override
    public List<Turno> listarTurnos() {
        return new ArrayList<>(turnos.values());
    }

    @Override
    public String verEstado(int id) {
        Turno turno = turnos.get(id);
        boolean estado = turno.isEstado();
        String result = null;
        if (estado) {
            result = "Turno finalizado";
        } else {
            result = "Turno en proceso";
        }
        return result;
    }
}