package entidades;

public class Drogueria {

    private static Drogueria instancia;

    private Drogueria() {
    }

    public static Drogueria getInstancia() {
        if (instancia == null) {
            instancia = new Drogueria();
        }
        return instancia;
    }

    public int entregarStock(Pedido pedido) {
        return pedido.getCantidad();
    }
}