package org.educa.service;

import org.educa.dao.AsientoDao;
import org.educa.dao.AsientoDaoImpl;
import org.educa.entity.AeropuertoEntity;
import org.educa.entity.AsientoEntity;
import org.educa.entity.VueloEntity;

import java.sql.SQLException;
import java.util.List;

public class AsientoService {

    private static final AsientoDao dao = new AsientoDaoImpl();

    /**
     * Obtiene los asientos disponibles de un vuelo.
     *
     * @param vuelo {@link VueloEntity} del que se buscan los asientos.
     * @return lista de {@link AsientoEntity} disponibles.
     * @throws SQLException si falla la consulta en la base de datos.
     */
    public List<AsientoEntity> findDisponibles(VueloEntity vuelo) throws SQLException {
        return dao.findDisponibles(vuelo);
    }
}
