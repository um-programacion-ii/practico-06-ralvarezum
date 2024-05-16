package dao;

import entidades.Compra;
import entidades.Medico;
import entidades.Paciente;
import entidades.Receta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Contenedor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompraDAOImpTest {

    private Contenedor contenedor;

    @BeforeEach
    void setUp() {
        contenedor = Contenedor.getInstancia();
    }

    @Test
    void agregarCompra() {
        Paciente paciente = new Paciente("Pedro Gimenez", 23893854, null);
        Medico medico = new Medico("Juan Miguel", 17698256, null, null, true);
        Receta receta = new Receta(1, null, paciente, medico);
        Compra compra = new Compra(1, paciente, receta);

        String resultado = contenedor.getCompraDAO().agregarCompra(compra);

        assertEquals("Compra agregada con éxito", resultado);
    }

    @Test
    void obtenerCompra() {
        Paciente paciente = new Paciente("Pedro Gimenez", 23893854, null);
        Medico medico = new Medico("Juan Miguel", 17698256, null, null, true);
        Receta receta = new Receta(1, null, paciente, medico);
        Compra compra = new Compra(1, paciente, receta);

        contenedor.getCompraDAO().agregarCompra(compra);
        Compra compraObtenida = contenedor.getCompraDAO().obtenerCompra(1);

        assertEquals(compra, compraObtenida);
    }

    @Test
    void listarCompras() {
        Paciente paciente = new Paciente("Pedro Gimenez", 23893854, null);
        Medico medico = new Medico("Juan Miguel", 17698256, null, null, true);
        Receta receta = new Receta(1, null, paciente, medico);
        Compra compra1 = new Compra(1, paciente, receta);
        Compra compra2 = new Compra(2, paciente, receta);

        contenedor.getCompraDAO().agregarCompra(compra1);
        contenedor.getCompraDAO().agregarCompra(compra2);

        List<Compra> compras = contenedor.getCompraDAO().listarCompras();

        assertEquals(2, compras.size());
    }

    @Test
    void verEstado() {
        Paciente paciente = new Paciente("Pedro Gimenez", 23893854, null);
        Medico medico = new Medico("Juan Miguel", 17698256, null, null, true);
        Receta receta = new Receta(1, null, paciente, medico);
        Compra compra = new Compra(1, paciente, receta);

        contenedor.getCompraDAO().agregarCompra(compra);
        String estado = contenedor.getCompraDAO().verEstado(1);

        assertEquals("Compra en proceso", estado);
    }

    @Test
    void limpiarDatos() {
        Paciente paciente = new Paciente("Pedro Gimenez", 23893854, null);
        Medico medico = new Medico("Juan Miguel", 17698256, null, null, true);
        Receta receta = new Receta(1, null, paciente, medico);
        Compra compra = new Compra(1, paciente, receta);

        contenedor.getCompraDAO().agregarCompra(compra);

        assertFalse(contenedor.getCompraDAO().listarCompras().isEmpty());

        String resultado = contenedor.getCompraDAO().limpiarDatos();

        assertEquals("Compras eliminadas con éxito", resultado);
        assertTrue(contenedor.getCompraDAO().listarCompras().isEmpty());
    }
}
