package services;

import entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AtencionMedicoServiceTest {

    private AtencionMedicoService atencionMedicoService;
    private Contenedor contenedor;

    @BeforeEach
    void setUp() {
        contenedor = Contenedor.getInstancia();
        atencionMedicoService = AtencionMedicoService.getInstancia();
        initializeData();
    }

    private void initializeData() {
        ObraSocial osep = new ObraSocial("OSEP");
        contenedor.getObraSocialDAO().agregarObraSocial(osep);
        Especialidad neurologia = new Especialidad("Neurología");
        contenedor.getEspecialidadDAO().agregarEspecialidad(neurologia);
        Medicamento medicamento1 = new Medicamento(1, "Amoxicilina");
        Medicamento medicamento2 = new Medicamento(2, "Loratadina");
        contenedor.getMedicamentoDAO().agregarMedicamento(medicamento1);
        contenedor.getMedicamentoDAO().agregarMedicamento(medicamento2);
        Paciente paciente = new Paciente("María Rodríguez", 24384987, osep);
        contenedor.getPacienteDAO().agregarPaciente(paciente);
        List<ObraSocial> obrasSociales = new ArrayList<>();
        obrasSociales.add(osep);
        Medico medico = new Medico("Juan Rodríguez", 16789425, neurologia, obrasSociales, true);
        contenedor.getMedicoDAO().agregarMedico(medico);
        Turno turno = new Turno(1, paciente, medico, true);
        contenedor.getTurnoDAO().agregarTurno(turno);
    }

    @Test
    void finalizarTurno() {
        String resultado = atencionMedicoService.finalizarTurno(1);
        assertEquals("Turno finalizado", resultado);
    }

    @Test
    void generarReceta() {
        Receta receta = atencionMedicoService.generarReceta(1);

        if (receta != null) {
            assertNotNull(receta.getMedicamentos());
            assertTrue(receta.getMedicamentos().size() >= 1 && receta.getMedicamentos().size() <= 4);
        }
    }

    @Test
    void obtenerMedicamentoAleatorio() {
        Medicamento medicamento = atencionMedicoService.obtenerMedicamentoAleatorio();
        assertNotNull(medicamento);
        assertTrue(medicamento.getId() == 1 || medicamento.getId() == 2);
    }
}
