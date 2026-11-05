package org.educa.service;

import org.educa.dao.PasajeroDao;
import org.educa.dao.PasajeroDaoImpl;
import org.educa.entity.PasajeroEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class PasajeroService {

    private static final PasajeroDao dao = new PasajeroDaoImpl();

    public void save(Connection connection, PasajeroEntity pasajero) throws SQLException {
        dao.insertar(connection, pasajero);
    }
}
