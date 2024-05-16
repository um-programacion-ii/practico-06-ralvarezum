package services;

import entidades.Medicamento;
import entidades.Receta;
import entidades.Turno;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AtencionMedicoService {

    private static volatile AtencionMedicoService instancia;
    private final Contenedor contenedor;
    private final Random random;

    private AtencionMedicoService() {
        contenedor = Contenedor.getInstancia();
        random = new Random();
    }

    public static AtencionMedicoService getInstancia() {
        if (instancia == null) {
            synchronized (AtencionMedicoService.class) {
                if (instancia == null) {
                    instancia = new AtencionMedicoService();
                }
            }
        }
        return instancia;
    }

    public String finalizarTurno(int idTurno) {
        Turno turno = contenedor.getTurnoDAO().obtenerTurno(idTurno);
        if (turno != null) {
            turno.setEstado(true);
            return contenedor.getTurnoDAO().verEstado(idTurno);
        }
        return "Turno no encontrado";
    }

    public Receta generarReceta(int idTurno) {
        Turno turno = contenedor.getTurnoDAO().obtenerTurno(idTurno);

        if (turno != null && random.nextBoolean()) {
            List<Medicamento> medicamentos = generarListaMedicamentosAleatorios();
            int idReceta = generarIdReceta();
            return new Receta(idReceta, medicamentos, turno.getPaciente(), turno.getMedico());
        } else {
            return null;
        }
    }

    private List<Medicamento> generarListaMedicamentosAleatorios() {
        List<Medicamento> medicamentos = new ArrayList<>();
        int cantidadMedicamentos = random.nextInt(4) + 1;
        for (int i = 0; i < cantidadMedicamentos; i++) {
            medicamentos.add(obtenerMedicamentoAleatorio());
        }
        return medicamentos;
    }

    private int generarIdReceta() {
        return contenedor.getRecetaDAO().listarRecetas().size() + 1;
    }

    public Medicamento obtenerMedicamentoAleatorio() {
        List<Medicamento> medicamentos = contenedor.getMedicamentoDAO().listarMedicamentos();
        if (medicamentos.isEmpty()) {
            return null;
        }
        int indice = random.nextInt(medicamentos.size());
        return medicamentos.get(indice);
    }
}
