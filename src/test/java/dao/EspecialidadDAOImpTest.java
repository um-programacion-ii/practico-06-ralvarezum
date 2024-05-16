package dao;

import entidades.Especialidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Contenedor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EspecialidadDAOImpTest {

    private Contenedor contenedor;

    @BeforeEach
    void setUp() {
        contenedor = Contenedor.getInstancia();
    }

    @Test
    void agregarEspecialidad() {
        Especialidad especialidad = new Especialidad("Neurología");
        String resultado = contenedor.getEspecialidadDAO().agregarEspecialidad(especialidad);
        assertEquals("Especialidad agregada con éxito", resultado);
    }

    @Test
    void obtenerEspecialidad() {
        Especialidad especialidad = new Especialidad("Neurología");
        contenedor.getEspecialidadDAO().agregarEspecialidad(especialidad);
        Especialidad especialidadObtenida = contenedor.getEspecialidadDAO().obtenerEspecialidad("Neurología");
        assertEquals(especialidad, especialidadObtenida);
    }

    @Test
    void listarEspecialidades() {
        Especialidad especialidad1 = new Especialidad("Neurología");
        Especialidad especialidad2 = new Especialidad("Geriatría");
        contenedor.getEspecialidadDAO().agregarEspecialidad(especialidad1);
        contenedor.getEspecialidadDAO().agregarEspecialidad(especialidad2);
        List<Especialidad> especialidades = contenedor.getEspecialidadDAO().listarEspecialidades();
        assertEquals(2, especialidades.size());
    }

    @Test
    void eliminarEspecialidad() {
        Especialidad especialidad = new Especialidad("Neurología");
        contenedor.getEspecialidadDAO().agregarEspecialidad(especialidad);
        String resultado = contenedor.getEspecialidadDAO().eliminarEspecialidad("Neurología");
        assertEquals("Especialidad eliminada con éxito", resultado);
        assertNull(contenedor.getEspecialidadDAO().obtenerEspecialidad("Neurología"));
    }

    @Test
    void actualizarEspecialidad() {
        Especialidad especialidad = new Especialidad("Hematología");
        contenedor.getEspecialidadDAO().agregarEspecialidad(especialidad);
        especialidad.setNombre("Hematología y hemoterapia");
        String resultado = contenedor.getEspecialidadDAO().actualizarEspecialidad(especialidad);
        assertEquals("Especialidad actualizada con éxito", resultado);
        Especialidad especialidadActualizada = contenedor.getEspecialidadDAO()
                .obtenerEspecialidad("Hematología y hemoterapia");
        assertEquals("Hematología y hemoterapia", especialidadActualizada.getNombre());
    }
}