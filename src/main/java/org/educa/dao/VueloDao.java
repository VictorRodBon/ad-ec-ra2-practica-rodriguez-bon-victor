package org.educa.dao;

import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;

import java.util.List;

public interface VueloDao {
    /**
     * Obtiene los {@link VueloEntity} que salen de un {@link AeropuertoEntity} indicado.
     *
     * @param origen {@link AeropuertoEntity} de origen.
     * @return lista de {@link VueloEntity}.
     */
    List<VueloEntity> getVuelosPorOrigen(AeropuertoEntity origen);
}
