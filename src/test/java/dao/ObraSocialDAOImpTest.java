package dao;

import entidades.ObraSocial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Contenedor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ObraSocialDAOImpTest {

    private Contenedor contenedor;

    @BeforeEach
    void setUp() {
        contenedor = Contenedor.getInstancia();
    }

    @Test
    void agregarObraSocial() {
        ObraSocial obraSocial = new ObraSocial("OSEP");
        String resultado = contenedor.getObraSocialDAO().agregarObraSocial(obraSocial);
        assertEquals("Obra Social agregada con éxito", resultado);
    }

    @Test
    void obtenerObraSocial() {
        ObraSocial obraSocial = new ObraSocial("OSEP");
        contenedor.getObraSocialDAO().agregarObraSocial(obraSocial);
        ObraSocial obraSocialObtenida = contenedor.getObraSocialDAO().obtenerObraSocial("OSEP");
        assertEquals(obraSocial, obraSocialObtenida);
    }

    @Test
    void listarObrasSociales() {
        ObraSocial obraSocial1 = new ObraSocial("OSEP");
        ObraSocial obraSocial2 = new ObraSocial("OSDE");
        contenedor.getObraSocialDAO().agregarObraSocial(obraSocial1);
        contenedor.getObraSocialDAO().agregarObraSocial(obraSocial2);
        List<ObraSocial> obrasSociales = contenedor.getObraSocialDAO().listarObrasSociales();
        assertEquals(2, obrasSociales.size());
    }

    @Test
    void eliminarObraSocial() {
        ObraSocial obraSocial = new ObraSocial("OSEP");
        contenedor.getObraSocialDAO().agregarObraSocial(obraSocial);
        String resultado = contenedor.getObraSocialDAO().eliminarObraSocial("OSEP");
        assertEquals("Obra Social eliminada con éxito", resultado);
        assertNull(contenedor.getObraSocialDAO().obtenerObraSocial("OSEP"));
    }

    @Test
    void actualizarObraSocial() {
        ObraSocial obraSocial = new ObraSocial("OSEP");
        contenedor.getObraSocialDAO().agregarObraSocial(obraSocial);
        obraSocial.setNombre("OSEP 410");
        String resultado = contenedor.getObraSocialDAO().actualizarObraSocial(obraSocial);
        assertEquals("Obra Social actualizada con éxito", resultado);
        ObraSocial obraSocialActualizada = contenedor.getObraSocialDAO().obtenerObraSocial("OSEP 410");
        assertEquals("OSEP 410", obraSocialActualizada.getNombre());
    }
}