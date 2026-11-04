package org.educa.service;

import org.educa.dao.AsientoDao;
import org.educa.dao.AsientoDaoImpl;
import org.educa.entity.AsientoEntity;
import org.educa.entity.VueloEntity;

import java.sql.SQLException;
import java.util.List;

public class AsientoService {

    private static final AsientoDao dao = new AsientoDaoImpl();

    public List<AsientoEntity> findDisponibles(VueloEntity vuelo) throws SQLException {
        return dao.findDisponibles(vuelo);
    }
}
