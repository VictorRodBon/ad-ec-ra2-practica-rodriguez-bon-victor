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

        /*
        SELECT
            v.id_vuelo,
            v.num_vuelo,
            dest.id_aeropuerto  AS id_destino,
            dest.cod_iata as iata_destino,
            dest.nombre as nombre_destino,
            dest.ciudad as ciudad_destino,
            dest.pais as pais_destino,
            v.id_avion,
            v.fec_salida ,
            v.fec_llegada ,
            v.estado
        FROM vuelos v
        INNER JOIN aeropuertos orig ON v.id_origen = orig.id_aeropuerto
        INNER JOIN aeropuertos dest ON v.id_destino = dest.id_aeropuerto
        where v.id_origen = ?;
        */
        String sql = "SELECT\n" +
                "            v.id_vuelo,\n" +
                "            v.num_vuelo,\n" +
                "            dest.id_aeropuerto  AS id_destino,\n" +
                "            dest.cod_iata as iata_destino,\n" +
                "            dest.nombre as nombre_destino,\n" +
                "            dest.ciudad as ciudad_destino,\n" +
                "            dest.pais as pais_destino,\n" +
                "            v.id_avion,\n" +
                "            v.fec_salida ,\n" +
                "            v.fec_llegada ,\n" +
                "            v.estado\n" +
                "        FROM vuelos v\n" +
                "        INNER JOIN aeropuertos orig ON v.id_origen = orig.id_aeropuerto\n" +
                "        INNER JOIN aeropuertos dest ON v.id_destino = dest.id_aeropuerto\n" +
                "        where v.id_origen = ?";
        try(Connection concection = ConnectionPool.getDataSource().getConnection();
            PreparedStatement ps = concection.prepareStatement(sql)
        ){
            ps.setInt(1, origen.getIdAeropuerto());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LocalDateTime fechaSalida = (rs.getTimestamp(9) != null) ? rs.getTimestamp(9).toLocalDateTime() : null;
                LocalDateTime fechaLlegada = (rs.getTimestamp(10) != null) ? rs.getTimestamp(10).toLocalDateTime() : null;
                AeropuertoEntity destino = new AeropuertoEntity();
                destino.setIdAeropuerto(rs.getInt(3));
                destino.setCodIata(rs.getString(4));
                destino.setNombre(rs.getString(5));
                destino.setCiudad(rs.getString(6));
                destino.setPais(rs.getString(7));
                vuelos.add(
                    new VueloEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        origen,
                        destino,
                        rs.getInt(8),
                        fechaSalida,
                        fechaLlegada,
                        rs.getString(11)
                    )
                );
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return vuelos;
    }
}
