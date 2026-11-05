package org.educa.dao;

import org.educa.entity.ReservaEntity;
import org.educa.pool.ConnectionPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservasDaoImpl implements ReservasDao{
    @Override
    public List<ReservaEntity> findByCliente(int id) {
        List<ReservaEntity> reservas = new ArrayList<>();
        String sql = "select * from reservas r where r.id_cliente = ?";
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    ReservaEntity reserva = new ReservaEntity();
                    reserva.setIdReserva(rs.getInt(1));
                    reserva.setCodReserva(rs.getString(2));

                    Timestamp timestamp = rs.getTimestamp(4);
                    LocalDateTime fecha = (timestamp != null) ? timestamp.toLocalDateTime() : null;
                    reserva.setFecReserva(fecha);
                    reserva.setEstado(rs.getString(5));
                    reservas.add(reserva);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }

    @Override
    public int saveReserva(Connection c, ReservaEntity reserva) throws SQLException{
        String sql = "INSERT INTO " +
                "reservas (" +
                    "cod_reserva, " +
                    "id_cliente" +
                ") VALUES (" +
                    "?, " +
                    "?) " +
                "RETURNING id_reserva";

        try (PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, reserva.getCodReserva());
            ps.setInt(2, reserva.getCliente().getIdCliente());

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    int id = rs.getInt(1);
                    reserva.setIdReserva(id);
                    return id;
                }
            }
        }
        throw new SQLException("no se ha podido obtener el id de la reserva insertada");
    }

    @Override
    public boolean existeCodigo(Connection c, String cod) throws SQLException{
        String sql = "SELECT 1 FROM reservas WHERE cod_reserva = ?";
        try(PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, cod);
            try (ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }
    }
}
