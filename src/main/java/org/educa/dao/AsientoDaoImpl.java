package org.educa.dao;

import org.educa.entity.AsientoEntity;
import org.educa.entity.VueloEntity;
import org.educa.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsientoDaoImpl implements AsientoDao{
    @Override
    public List<AsientoEntity> findDisponibles(VueloEntity vuelo) throws SQLException {
        List<AsientoEntity> asientos = new ArrayList<>();
        String sql = "SELECT a.*, v.*\n" +
                "FROM asientos a\n" +
                "INNER JOIN vuelos v ON v.id_avion = a.id_avion\n" +
                "LEFT JOIN billetes b ON a.id_asiento = b.id_asiento AND b.id_vuelo = v.id_vuelo\n" +
                "WHERE v.num_vuelo = ?\n" +
                "  AND b.id_asiento IS NULL;";

        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, vuelo.getNumVuelo());

            try(ResultSet rs = ps.executeQuery()){

                while(rs.next()){
                    asientos.add(
                            new AsientoEntity(
                                    rs.getInt(1),
                                    rs.getInt(2),
                                    rs.getString(3),
                                    rs.getString(4),
                                    getSuplemento(rs.getString(4))
                            )
                    );
                }
            }catch (SQLException e){
                throw e;
            }
        }
        return asientos;
    }

    private BigDecimal getSuplemento(String clase){
        BigDecimal suplemento;
        switch (clase){
            case "TURISTA":
                suplemento=new BigDecimal("0");
                break;
            case "PREMIUM_ECONOMY":
                suplemento=new BigDecimal("35");
                break;
            case "BUSINESS":
                suplemento=new BigDecimal("120");
                break;
            case "FIRST":
                suplemento=new BigDecimal("250");
                break;
            default:
                suplemento=new BigDecimal("0");
                break;
        }
        return suplemento;
    }
}
