package org.educa.service;

import org.educa.dao.ReservasDao;
import org.educa.dao.ReservasDaoImpl;
import org.educa.entity.ClienteEntity;
import org.educa.entity.ReservaEntity;

import java.sql.SQLException;
import java.util.List;

public class ReservaService {

    ReservasDao dao = new ReservasDaoImpl();

    public List<ReservaEntity> findByCliente(ClienteEntity cliente) throws SQLException {
        List<ReservaEntity> listaReservas = dao.findByCliente(cliente.getIdCliente());
        return listaReservas;
    }

    public void save(ReservaEntity reserva) throws SQLException {
    }
}
