package org.educa.dao;

import org.educa.entity.AeropuertoEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AeropuertoDaoImpl implements AeropuertoDao{
    @Override
    public List<AeropuertoEntity> getAeropuertos() {
        List<AeropuertoEntity> aeropuertos = new ArrayList<>();

        String sql = "select * from aeropuertos a";

        try(Connection connection= ConnectionPool.getDataSource().getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                aeropuertos.add(new AeropuertoEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                        )
                );
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return aeropuertos;
    }
}
