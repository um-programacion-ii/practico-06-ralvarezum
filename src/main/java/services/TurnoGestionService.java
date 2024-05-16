package services;

import entidades.Medico;
import entidades.Paciente;
import entidades.Turno;

import java.util.List;
import java.util.stream.Collectors;

public class TurnoGestionService {

    private static volatile TurnoGestionService instancia;
    private final Contenedor contenedor;

    private TurnoGestionService() {
        contenedor = Contenedor.getInstancia();
    }

    public static TurnoGestionService getInstancia() {
        if (instancia == null) {
            synchronized (TurnoGestionService.class) {
                if (instancia == null) {
                    instancia = new TurnoGestionService();
                }
            }
        }
        return instancia;
    }

    public List<Medico> listarMedicosParticulares() {
        return contenedor.getMedicoDAO().listarMedicos().stream()
                .filter(Medico::isParticular)
                .collect(Collectors.toList());
    }

    public List<Medico> listarMedicosPorEspecialidadYObraSocial(String especialidad, String obraSocial) {
        return contenedor.getMedicoDAO().listarMedicos().stream()
                .filter(medico -> medico.getEspecialidad().getNombre().equals(especialidad) &&
                        medico.getObraSociales().stream()
                                .anyMatch(os -> os.getNombre().equals(obraSocial)))
                .collect(Collectors.toList());
    }

    public String solicitarTurno(Paciente paciente, Medico medico, boolean conObraSocial) {
        int id = generarIdTurno();
        Turno turno = new Turno(id, paciente, medico, conObraSocial);
        return contenedor.getTurnoDAO().agregarTurno(turno);
    }

    private int generarIdTurno() {
        return contenedor.getTurnoDAO().listarTurnos().size() + 1;
    }
}
