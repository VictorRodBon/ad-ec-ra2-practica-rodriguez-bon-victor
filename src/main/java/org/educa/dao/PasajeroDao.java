package org.educa.dao;

import org.educa.entity.PasajeroEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface PasajeroDao {
    /**
     * Almacena un {@link PasajeroEntity} en la base de datos.
     *
     * @param c conexión con la base de datos.
     * @param pasajero {@link PasajeroEntity} a guardar en la base de datos.
     * @throws SQLException si falla la inserción en la base de datos.
     */
    void insertar(Connection c, PasajeroEntity pasajero) throws SQLException;
}
