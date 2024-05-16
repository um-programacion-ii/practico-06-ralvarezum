package services;

import entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TurnoGestionServiceTest {

    private TurnoGestionService turnoGestionService;
    private Contenedor contenedor;

    @BeforeEach
    void setUp() {
        contenedor = Contenedor.getInstancia();
        turnoGestionService = TurnoGestionService.getInstancia();

        contenedor.getObraSocialDAO().agregarObraSocial(new ObraSocial("OSEP"));
        contenedor.getObraSocialDAO().agregarObraSocial(new ObraSocial("OSDE"));
        contenedor.getEspecialidadDAO().agregarEspecialidad(new Especialidad("Neurología"));
        contenedor.getEspecialidadDAO().agregarEspecialidad(new Especialidad("Traumatología"));

        List<ObraSocial> obrasSociales1 = new ArrayList<>();
        obrasSociales1.add(contenedor.getObraSocialDAO().obtenerObraSocial("OSEP"));
        contenedor.getMedicoDAO().agregarMedico(new Medico("Rodrigo Alvarez", 39765987,
                contenedor.getEspecialidadDAO().obtenerEspecialidad("Neurología"), obrasSociales1, true));
        contenedor.getMedicoDAO().agregarMedico(new Medico("Matias Yerumini", 41527564,
                contenedor.getEspecialidadDAO().obtenerEspecialidad("Traumatología"), obrasSociales1, false));

        List<ObraSocial> obrasSociales2 = new ArrayList<>();
        obrasSociales2.add(contenedor.getObraSocialDAO().obtenerObraSocial("OSDE"));
        contenedor.getMedicoDAO().agregarMedico(new Medico("Carlitos Bala", 18368367,
                contenedor.getEspecialidadDAO().obtenerEspecialidad("Neurología"), obrasSociales2, true));
        contenedor.getMedicoDAO().agregarMedico(new Medico("Pino Ocho", 34163830,
                contenedor.getEspecialidadDAO().obtenerEspecialidad("Traumatología"), obrasSociales2, false));

        contenedor.getPacienteDAO().agregarPaciente(new Paciente("Pepe Grillo", 36250093,
                contenedor.getObraSocialDAO().obtenerObraSocial("OSEP")));
    }

    @Test
    void listarMedicosParticulares() {
        List<Medico> medicosParticulares = turnoGestionService.listarMedicosParticulares();

        assertEquals(2, medicosParticulares.size());
        assertTrue(medicosParticulares.stream().anyMatch(m -> m.getNombre().equals("Rodrigo Alvarez")));
        assertTrue(medicosParticulares.stream().anyMatch(m -> m.getNombre().equals("Carlitos Bala")));
    }

    @Test
    void listarMedicosPorEspecialidadYObraSocial() {
        List<Medico> medicos = turnoGestionService.listarMedicosPorEspecialidadYObraSocial("Neurología", "OSEP");

        assertEquals(1, medicos.size());
        assertEquals("Rodrigo Alvarez", medicos.get(0).getNombre());
    }

    @Test
    void solicitarTurno() {
        Paciente paciente = contenedor.getPacienteDAO().obtenerPaciente(36250093);
        Medico medico = contenedor.getMedicoDAO().obtenerMedico(39765987);

        String resultado = turnoGestionService.solicitarTurno(paciente, medico, true);

        assertEquals("Turno agregado con éxito", resultado);
    }
}
