package dao;

import entidades.Especialidad;
import entidades.Medico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Contenedor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MedicoDAOImpTest {

    private Contenedor contenedor;

    @BeforeEach
    void setUp() {
        contenedor = Contenedor.getInstancia();
    }

    @Test
    void agregarMedico() {
        Especialidad especialidad = new Especialidad("Cardiología");
        Medico medico = new Medico("Rodrigo Alvarez", 30406898, especialidad, null, true);
        String resultado = contenedor.getMedicoDAO().agregarMedico(medico);
        assertEquals("Medico agregado con éxito", resultado);
    }

    @Test
    void obtenerMedico() {
        Especialidad especialidad = new Especialidad("Hematología");
        Medico medico = new Medico("Rodrigo Alvarez", 30406898, especialidad, null, true);
        contenedor.getMedicoDAO().agregarMedico(medico);
        Medico medicoObtenido = contenedor.getMedicoDAO().obtenerMedico(30406898);
        assertEquals(medico, medicoObtenido);
    }

    @Test
    void listarMedicos() {
        Especialidad especialidad1 = new Especialidad("Hematología");
        Especialidad especialidad2 = new Especialidad("Geriatría");
        Medico medico1 = new Medico("Rodrigo Alvarez", 30406898, especialidad1, null, true);
        Medico medico2 = new Medico("Liam Gallagher", 30406897, especialidad2, null, false);
        contenedor.getMedicoDAO().agregarMedico(medico1);
        contenedor.getMedicoDAO().agregarMedico(medico2);
        List<Medico> medicos = contenedor.getMedicoDAO().listarMedicos();
        assertEquals(2, medicos.size());
    }

    @Test
    void eliminarMedico() {
        Especialidad especialidad = new Especialidad("Hematología");
        Medico medico = new Medico("Rodrigo Alvarez", 30406898, especialidad, null, true);
        contenedor.getMedicoDAO().agregarMedico(medico);
        String resultado = contenedor.getMedicoDAO().eliminarMedico(30406898);
        assertEquals("Medico eliminado con éxito", resultado);
        assertNull(contenedor.getMedicoDAO().obtenerMedico(30406898));
    }

    @Test
    void actualizarMedico() {
        Especialidad especialidad = new Especialidad("Hematología");
        Medico medico = new Medico("Rodrigo Alvarez", 30406898, especialidad, null, true);
        contenedor.getMedicoDAO().agregarMedico(medico);
        medico.setNombre("Rodrigo Matias Alvarez");
        String resultado = contenedor.getMedicoDAO().actualizarMedico(medico);
        assertEquals("Medico actualizado con éxito", resultado);
        Medico medicoActualizado = contenedor.getMedicoDAO().obtenerMedico(30406898);
        assertEquals("Rodrigo Matias Alvarez", medicoActualizado.getNombre());
    }

    @Test
    void limpiarDatos() {
        Especialidad especialidad = new Especialidad("Cardiología");
        Medico medico = new Medico("Rodrigo Alvarez", 30406898, especialidad, null, true);
        contenedor.getMedicoDAO().agregarMedico(medico);
        assertFalse(contenedor.getMedicoDAO().listarMedicos().isEmpty());
        String resultado = contenedor.getMedicoDAO().limpiarDatos();
        assertEquals("Medicos eliminados con éxito", resultado);
        assertTrue(contenedor.getMedicoDAO().listarMedicos().isEmpty());
    }
}