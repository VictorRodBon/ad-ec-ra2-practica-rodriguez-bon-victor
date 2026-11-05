package org.educa.dao;

import org.educa.entity.PasajeroEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface PasajeroDao {
    void insertar(Connection c, PasajeroEntity pasajero) throws SQLException;
}
