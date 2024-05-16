package services;

import dao.*;
import dao.interfaces.*;

public class Contenedor {

    private static volatile Contenedor instancia;

    private ObraSocialDAO obraSocialDAO;
    private MedicoDAO medicoDAO;
    private EspecialidadDAO especialidadDAO;
    private PacienteDAO pacienteDAO;
    private TurnoDAO turnoDAO;
    private RecetaDAO recetaDAO;
    private CompraDAO compraDAO;
    private PedidoDAO pedidoDAO;
    private MedicamentoDAO medicamentoDAO;

    private Contenedor() {
    }

    public static Contenedor getInstancia() {
        if (instancia == null) {
            synchronized (Contenedor.class) {
                if (instancia == null) {
                    instancia = new Contenedor();
                }
            }
        }
        return instancia;
    }

    public ObraSocialDAO getObraSocialDAO() {
        if (obraSocialDAO == null) {
            obraSocialDAO = new ObraSocialDAOImp();
        }
        return obraSocialDAO;
    }

    public MedicoDAO getMedicoDAO() {
        if (medicoDAO == null) {
            medicoDAO = new MedicoDAOImp();
        }
        return medicoDAO;
    }

    public EspecialidadDAO getEspecialidadDAO() {
        if (especialidadDAO == null) {
            especialidadDAO = new EspecialidadDAOImp();
        }
        return especialidadDAO;
    }

    public PacienteDAO getPacienteDAO() {
        if (pacienteDAO == null) {
            pacienteDAO = new PacienteDAOImp();
        }
        return pacienteDAO;
    }

    public TurnoDAO getTurnoDAO() {
        if (turnoDAO == null) {
            turnoDAO = new TurnoDAOImp();
        }
        return turnoDAO;
    }

    public RecetaDAO getRecetaDAO() {
        if (recetaDAO == null) {
            recetaDAO = new RecetaDAOImp();
        }
        return recetaDAO;
    }

    public CompraDAO getCompraDAO() {
        if (compraDAO == null) {
            compraDAO = new CompraDAOImp();
        }
        return compraDAO;
    }

    public PedidoDAO getPedidoDAO() {
        if (pedidoDAO == null) {
            pedidoDAO = new PedidoDAOImp();
        }
        return pedidoDAO;
    }

    public MedicamentoDAO getMedicamentoDAO() {
        if (medicamentoDAO == null) {
            medicamentoDAO = new MedicamentoDAOImp();
        }
        return medicamentoDAO;
    }
}
