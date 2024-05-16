package dao;

import entidades.Medico;
import entidades.Paciente;
import entidades.Receta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Contenedor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecetaDAOImpTest {

    private Contenedor contenedor;

    @BeforeEach
    void setUp() {
        contenedor = Contenedor.getInstancia();
    }

    @Test
    void agregarReceta() {
        Paciente paciente = new Paciente("Pepe Pepito", 32567894, null);
        Medico medico = new Medico("Juan Pepito", 34567820, null, null, true);
        Receta receta = new Receta(1, null, paciente, medico);
        String resultado = contenedor.getRecetaDAO().agregarReceta(receta);
        assertEquals("Receta agregada con éxito", resultado);
    }

    @Test
    void obtenerReceta() {
        Paciente paciente = new Paciente("Pepe Pepito", 32567894, null);
        Medico medico = new Medico("Juan Pepito", 34567820, null, null, true);
        Receta receta = new Receta(1, null, paciente, medico);
        contenedor.getRecetaDAO().agregarReceta(receta);
        Receta recetaObtenida = contenedor.getRecetaDAO().obtenerReceta(1);
        assertEquals(receta, recetaObtenida);
    }

    @Test
    void listarRecetas() {
        Paciente paciente = new Paciente("Pepe Pepito", 32567894, null);
        Medico medico = new Medico("Juan Pepito", 34567820, null, null, true);
        Receta receta1 = new Receta(1, null, paciente, medico);
        Receta receta2 = new Receta(2, null, paciente, medico);
        contenedor.getRecetaDAO().agregarReceta(receta1);
        contenedor.getRecetaDAO().agregarReceta(receta2);
        List<Receta> recetas = contenedor.getRecetaDAO().listarRecetas();
        assertEquals(2, recetas.size());
    }
}