package org.educa.dao;

import org.educa.entity.BilleteEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface BilleteDao {
    /**
     * Almacena un {@link BilleteEntity} en la base de datos.
     *
     * @param c conexión con la base de datos.
     * @param billete {@link BilleteEntity} a guardar en la base de datos.
     * @throws SQLException si falla la inserción en la base de datos.
     */
    void insertar (Connection c, BilleteEntity billete) throws SQLException;
}
