package services;

import entidades.*;

public class FarmaciaGestionService {

    private static volatile FarmaciaGestionService instancia;
    private final Contenedor contenedor;
    private final Farmacia farmacia;
    private final Drogueria drogueria;

    private FarmaciaGestionService() {
        contenedor = Contenedor.getInstancia();
        farmacia = Farmacia.getInstancia();
        drogueria = Drogueria.getInstancia();
    }

    public static FarmaciaGestionService getInstancia() {
        if (instancia == null) {
            synchronized (FarmaciaGestionService.class) {
                if (instancia == null) {
                    instancia = new FarmaciaGestionService();
                }
            }
        }
        return instancia;
    }

    public String iniciarCompra(Paciente paciente, Receta receta) {
        int idCompra = generarIdCompra();
        Compra compra = new Compra(idCompra, paciente, receta);
        return contenedor.getCompraDAO().agregarCompra(compra);
    }

    public String finalizarCompra(int idCompra) {
        Compra compra = contenedor.getCompraDAO().obtenerCompra(idCompra);
        if (compra != null) {
            compra.setEstado(true);
            reducirStockFarmacia(compra.getReceta());
            return contenedor.getCompraDAO().verEstado(idCompra);
        }
        return "Compra no encontrada";
    }

    public void reducirStockFarmacia(Receta receta) {
        receta.getMedicamentos().forEach(this::gestionarStockMedicamento);
    }

    public void gestionarStockMedicamento(Medicamento medicamento) {
        if (farmacia.hayStockSuficiente(medicamento)) {
            farmacia.reducirStock(medicamento);
        } else {
            solicitarMedicamentoADrogueria(medicamento);
        }
    }

    public void solicitarMedicamentoADrogueria(Medicamento medicamento) {
        int idPedido = generarIdPedido();
        Pedido pedido = new Pedido(idPedido, medicamento, 5);
        contenedor.getPedidoDAO().agregarPedido(pedido);

        int stock = drogueria.entregarStock(pedido);
        pedido.setEstado(true);
        System.out.println(contenedor.getPedidoDAO().verEstado(idPedido));

        farmacia.agregarStock(medicamento, stock);
    }

    private int generarIdCompra() {
        return contenedor.getCompraDAO().listarCompras().size() + 1;
    }

    private int generarIdPedido() {
        return contenedor.getPedidoDAO().listarPedidos().size() + 1;
    }
}
