package org.educa.service;

import org.educa.dao.ReservasDao;
import org.educa.dao.ReservasDaoImpl;
import org.educa.entity.BilleteEntity;
import org.educa.entity.ClienteEntity;
import org.educa.entity.PasajeroEntity;
import org.educa.entity.ReservaEntity;
import org.educa.pool.ConnectionPool;
import org.educa.util.ReservaUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReservaService {

    private static  final ReservasDao dao = new ReservasDaoImpl();
    private static final PasajeroService pasajeroService = new PasajeroService();
    private static final BilleteService billeteService = new BilleteService();

    /**
     * Obtiene las reservas almacenadas en la base de datos de un {@link ClienteEntity}.
     *
     * @param cliente {@link ClienteEntity} del que se buscan las reservas.
     * @return lista de {@link ReservaEntity} del cliente.
     * @throws SQLException si falla la consulta en la base de datos.
     */
    public List<ReservaEntity> findByCliente(ClienteEntity cliente) throws SQLException {
        List<ReservaEntity> listaReservas = dao.findByCliente(cliente.getIdCliente());
        return listaReservas;
    }

    /**
     * Guarda una reserva completa (reserva, pasajero y billete) de forma atómica.
     * <p>
     * Genera un código alfanumérico único de 6 caracteres y, dentro de una única
     * transacción, inserta en este orden: la reserva, el pasajero (el propio
     * cliente) y su billete. Si cualquiera de las inserciones falla se hace
     * rollback y no queda ningún registro en la base de datos.
     *
     * @param reserva {@link ReservaEntity} a guardar, con su billete y pasajero.
     * @throws SQLException si falla cualquier operación contra la base de datos.
     */
    public void save(ReservaEntity reserva) throws SQLException {
        try(Connection c = ConnectionPool.getDataSource().getConnection()){
            c.setAutoCommit(false);

            try{
                String cod;
                do{cod=ReservaUtils.generarCodigoReserva();}while(dao.existeCodigo(c, cod));
                reserva.setCodReserva(cod);

                int idReserva = dao.saveReserva(c,reserva);
                reserva.setIdReserva(idReserva);

                BilleteEntity billete = reserva.getBilletes().getFirst();
                PasajeroEntity pasajero = billete.getPasajero();

                pasajero.setIdReserva(idReserva);

                pasajeroService.save(c, pasajero);

                billeteService.save(c, billete);

                c.commit();
            }catch (SQLException | RuntimeException e){
                c.rollback();
                throw e;
            } finally {
                c.setAutoCommit(true);
            }
        }
    }
}
