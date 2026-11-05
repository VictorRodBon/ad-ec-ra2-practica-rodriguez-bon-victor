package org.educa.service;

import org.educa.dao.PasajeroDao;
import org.educa.dao.PasajeroDaoImpl;
import org.educa.entity.PasajeroEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class PasajeroService {

    private static final PasajeroDao dao = new PasajeroDaoImpl();

    /**
     * Almacena un {@link PasajeroEntity} en la base de datos.
     *
     * @param connection conexión con la base de datos.
     * @param pasajero {@link PasajeroEntity} a guardar en la base de datos.
     * @throws SQLException si falla la inserción en la base de datos.
     */
    public void save(Connection connection, PasajeroEntity pasajero) throws SQLException {
        dao.insertar(connection, pasajero);
    }
}
