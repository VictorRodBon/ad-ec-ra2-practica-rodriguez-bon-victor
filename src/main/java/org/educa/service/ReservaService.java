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

    public List<ReservaEntity> findByCliente(ClienteEntity cliente) throws SQLException {
        List<ReservaEntity> listaReservas = dao.findByCliente(cliente.getIdCliente());
        return listaReservas;
    }

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
