package org.educa.service;

import org.educa.dao.VueloDao;
import org.educa.dao.VueloDaoImpl;
import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class VueloService {
    private static final VueloDao dao = new VueloDaoImpl();

    public List<VueloEntity> findByOrigen(AeropuertoEntity origen) throws SQLException {
        return dao.getVuelosPorOrigen(origen);
    }

    public BigDecimal getPrecioBase(VueloEntity vuelo) {
        return null;
    }
}
