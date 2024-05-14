package dao.interfaces;

import entidades.Medicamento;
import entidades.Pedido;

import java.util.List;

public interface PedidoDAO {
    String agregarPedido(Pedido pedido);
    Pedido obtenerPedido(int id);
    List<Pedido> listarPedidos();
    String verEstado(int id);
    Medicamento mostrarMedicamento(int id);
}