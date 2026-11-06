package org.educa.service;

import org.educa.dao.AeropuertoDao;
import org.educa.dao.AeropuertoDaoImpl;
import org.educa.entity.AeropuertoEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AeropuertoService {
    private static final AeropuertoDao  dao = new AeropuertoDaoImpl();

    /**
     * Obtiene todos los aeropuertos almacenados en la base de datos.
     *
     * @return lista de {@link AeropuertoEntity}.
     * @throws SQLException si falla la consulta en la base de datos.
     */
    public List<AeropuertoEntity> findAll() throws SQLException {
        return dao.getAeropuertos();
    }
}
