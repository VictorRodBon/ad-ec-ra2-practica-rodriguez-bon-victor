package org.educa.dao;

import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;

import java.util.List;

public interface VueloDao {
    List<VueloEntity> getVuelosPorOrigen(AeropuertoEntity origen);
}
