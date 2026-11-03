package org.educa.dao;

import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;
import org.educa.pool.ConnectionPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VueloDaoImpl implements VueloDao{
    @Override
    public List<VueloEntity> getVuelosPorOrigen(AeropuertoEntity origen) {
        List<VueloEntity> vuelos = new ArrayList<>();
        String sql = "SELECT * FROM vuelos v WHERE v.id_origen = ?";
        try(Connection concection = ConnectionPool.getDataSource().getConnection();
            PreparedStatement ps = concection.prepareStatement(sql)
        ){
            ps.setInt(1, origen.getIdAeropuerto());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LocalDateTime fechaSalida = (rs.getTimestamp(6) != null) ? rs.getTimestamp(6).toLocalDateTime() : null;
                LocalDateTime fechaLlegada = (rs.getTimestamp(7) != null) ? rs.getTimestamp(6).toLocalDateTime() : null;
                AeropuertoEntity destino = new AeropuertoEntity();
                destino.setIdAeropuerto(rs.getInt(4));
                vuelos.add(
                    new VueloEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        origen,
                        destino,
                        rs.getInt(5),
                        fechaSalida,
                        fechaLlegada,
                        rs.getString(8)
                    )
                );
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return vuelos;
    }
}
