package dao;

import entidades.ObraSocial;
import entidades.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Contenedor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PacienteDAOImpTest {

    private Contenedor contenedor;

    @BeforeEach
    void setUp() {
        contenedor = Contenedor.getInstancia();
        contenedor.getPacienteDAO().limpiarDatos();
    }

    @Test
    void agregarPaciente() {
        ObraSocial obraSocial = new ObraSocial("OSDE");
        Paciente paciente = new Paciente("Liam Gallagher", 24560717, obraSocial);
        String resultado = contenedor.getPacienteDAO().agregarPaciente(paciente);
        assertEquals("Paciente agregado con éxito", resultado);
    }

    @Test
    void obtenerPaciente() {
        ObraSocial obraSocial = new ObraSocial("OSDE");
        Paciente paciente = new Paciente("Liam Gallagher", 24560717, obraSocial);
        contenedor.getPacienteDAO().agregarPaciente(paciente);
        Paciente pacienteObtenido = contenedor.getPacienteDAO().obtenerPaciente(24560717);
        assertEquals(paciente, pacienteObtenido);
    }

    @Test
    void listarPacientes() {
        ObraSocial obraSocial1 = new ObraSocial("OSDE");
        ObraSocial obraSocial2 = new ObraSocial("Boreal Salud");
        Paciente paciente1 = new Paciente("Liam Gallagher", 24560717, obraSocial1);
        Paciente paciente2 = new Paciente("Noel Gallagher", 45678234, obraSocial2);
        contenedor.getPacienteDAO().agregarPaciente(paciente1);
        contenedor.getPacienteDAO().agregarPaciente(paciente2);
        List<Paciente> pacientes = contenedor.getPacienteDAO().listarPacientes();
        assertEquals(2, pacientes.size());
    }

    @Test
    void eliminarPaciente() {
        ObraSocial obraSocial = new ObraSocial("OSDE");
        Paciente paciente = new Paciente("Liam Gallagher", 24560717, obraSocial);
        contenedor.getPacienteDAO().agregarPaciente(paciente);
        String resultado = contenedor.getPacienteDAO().eliminarPaciente(24560717);
        assertEquals("Paciente eliminado con éxito", resultado);
        assertNull(contenedor.getPacienteDAO().obtenerPaciente(24560717));
    }

    @Test
    void actualizarPaciente() {
        ObraSocial obraSocial = new ObraSocial("OSDE");
        Paciente paciente = new Paciente("Liam Gallagher", 24560717, obraSocial);
        contenedor.getPacienteDAO().agregarPaciente(paciente);
        paciente.setNombre("William John Paul Gallagher");
        String resultado = contenedor.getPacienteDAO().actualizarPaciente(paciente);
        assertEquals("Paciente actualizado con éxito", resultado);
        Paciente pacienteActualizado = contenedor.getPacienteDAO().obtenerPaciente(24560717);
        assertEquals("William John Paul Gallagher", pacienteActualizado.getNombre());
    }

    @Test
    void limpiarDatos() {
        ObraSocial obraSocial = new ObraSocial("OSDE");
        Paciente paciente = new Paciente("Liam Gallagher", 24560717, obraSocial);
        contenedor.getPacienteDAO().agregarPaciente(paciente);
        assertFalse(contenedor.getPacienteDAO().listarPacientes().isEmpty());
        String resultado = contenedor.getPacienteDAO().limpiarDatos();
        assertEquals("Pacientes eliminados con éxito", resultado);
        assertTrue(contenedor.getPacienteDAO().listarPacientes().isEmpty());
    }
}