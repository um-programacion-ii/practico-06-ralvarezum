package services;

import entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FarmaciaGestionServiceTest {

    private FarmaciaGestionService farmaciaGestionService;
    private Contenedor contenedor;
    private Farmacia farmacia;

    @BeforeEach
    void setUp() {
        contenedor = Contenedor.getInstancia();
        farmaciaGestionService = FarmaciaGestionService.getInstancia();
        farmacia = Farmacia.getInstancia();
        initializeData();
    }

    private void initializeData() {
        agregarMedicamentosAFarmacia();

        contenedor.getCompraDAO().limpiarDatos();
        contenedor.getPedidoDAO().limpiarDatos();
    }

    private void agregarMedicamentosAFarmacia() {
        Medicamento medicamento1 = new Medicamento(1, "Omeprazol");
        Medicamento medicamento2 = new Medicamento(2, "Paracetamol");
        Medicamento medicamento3 = new Medicamento(3, "Amoxicilina");
        contenedor.getMedicamentoDAO().agregarMedicamento(medicamento1);
        contenedor.getMedicamentoDAO().agregarMedicamento(medicamento2);
        contenedor.getMedicamentoDAO().agregarMedicamento(medicamento3);

        farmacia.agregarStock(medicamento1, 6);
        farmacia.agregarStock(medicamento2, 5);
        farmacia.agregarStock(medicamento3, 0);
    }

    private Receta crearRecetaDePrueba() {
        Paciente paciente = new Paciente("Gloria Alvarez", 27980456, null);
        Medico medico = new Medico("Tania Alvarez", 42367819, null, null, true);
        List<Medicamento> medicamentos = new ArrayList<>();
        medicamentos.add(contenedor.getMedicamentoDAO().obtenerMedicamento(1));
        medicamentos.add(contenedor.getMedicamentoDAO().obtenerMedicamento(2));
        return new Receta(1, medicamentos, paciente, medico);
    }

    @Test
    void iniciarCompra() {
        Receta receta = crearRecetaDePrueba();
        String resultado = farmaciaGestionService.iniciarCompra(receta.getPaciente(), receta);
        assertEquals("Compra agregada con éxito", resultado);
    }

    @Test
    void finalizarCompra() {
        Receta receta = crearRecetaDePrueba();
        Compra compra = new Compra(1, receta.getPaciente(), receta);
        contenedor.getCompraDAO().agregarCompra(compra);
        String resultado = farmaciaGestionService.finalizarCompra(1);
        assertEquals("Compra finalizada", resultado);
    }

    @Test
    void reducirStockFarmacia() {
        Paciente paciente = new Paciente("Juan Perez", 12345678, null);
        Medico medico = new Medico("Ana García", 87654321, null, null, true);
        Medicamento medicamento1 = contenedor.getMedicamentoDAO().obtenerMedicamento(1);
        Medicamento medicamento2 = contenedor.getMedicamentoDAO().obtenerMedicamento(2);
        Medicamento medicamento3 = contenedor.getMedicamentoDAO().obtenerMedicamento(3);

        List<Medicamento> medicamentosReceta = Arrays.asList(medicamento1, medicamento2, medicamento3);
        Receta receta = new Receta(1, medicamentosReceta, paciente, medico);

        farmaciaGestionService.reducirStockFarmacia(receta);

        Map<Medicamento, Integer> stockFarmacia = farmacia.getStock();
        assertEquals(5, stockFarmacia.get(medicamento1));
        assertEquals(4, stockFarmacia.get(medicamento2));

        List<Pedido> pedidos = contenedor.getPedidoDAO().listarPedidos();
        assertEquals(1, pedidos.size());
    }

    @Test
    void solicitarMedicamentoADrogueria() {
        Medicamento medicamento3 = contenedor.getMedicamentoDAO().obtenerMedicamento(3);
        farmaciaGestionService.solicitarMedicamentoADrogueria(medicamento3);

        List<Pedido> pedidos = contenedor.getPedidoDAO().listarPedidos();
        assertEquals(1, pedidos.size());
        assertEquals(medicamento3, pedidos.get(0).getMedicamento());
        assertEquals(5, pedidos.get(0).getCantidad());

        assertEquals(5, farmacia.getStock().get(medicamento3));
    }
}
