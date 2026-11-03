package org.educa.service;

import org.educa.dao.AeropuertoDao;
import org.educa.dao.AeropuertoDaoImpl;
import org.educa.entity.AeropuertoEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AeropuertoService {
    static private final AeropuertoDao  dao = new AeropuertoDaoImpl();

    public List<AeropuertoEntity> findAll() throws SQLException {
        return dao.getAeropuertos();
    }
}
