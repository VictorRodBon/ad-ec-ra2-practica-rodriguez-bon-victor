package org.educa.dao;

import org.educa.entity.BilleteEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface BilleteDao {
    void insertar (Connection c, BilleteEntity billete) throws SQLException;
}
