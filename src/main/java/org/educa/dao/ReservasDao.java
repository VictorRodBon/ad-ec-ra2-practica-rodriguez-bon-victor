package org.educa.dao;

import org.educa.entity.ReservaEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ReservasDao {
    List<ReservaEntity> findByCliente(int id);

    int saveReserva(Connection c, ReservaEntity reserva) throws SQLException;

    boolean existeCodigo(Connection c, String cod) throws SQLException;
}
