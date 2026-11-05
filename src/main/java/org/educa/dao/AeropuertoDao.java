package org.educa.dao;

import org.educa.entity.AeropuertoEntity;

import java.util.List;

public interface AeropuertoDao {
    /**
     * Obtiene una lista con todos los aeropuertos almacenados en la base de datos.
     *
     * @return lista de {@link AeropuertoEntity}.
     */
    List<AeropuertoEntity> getAeropuertos();
}
