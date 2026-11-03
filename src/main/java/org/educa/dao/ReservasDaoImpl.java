package org.educa.dao;

import org.educa.entity.ClienteEntity;
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
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ReservaEntity reserva = new ReservaEntity();
                reserva.setIdReserva(rs.getInt(1));
                reserva.setCodReserva(rs.getString(2));

                Timestamp timestamp = rs.getTimestamp(4); // O usa el nombre de la columna: rs.getTimestamp("nombre_columna")
                LocalDateTime fecha = (timestamp != null) ? timestamp.toLocalDateTime() : null;
                reserva.setFecReserva(fecha);
                reserva.setEstado(rs.getString(5));
                reservas.add(reserva);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }

    @Override
    public void saveReserva(ReservaEntity reserva) {

    }
}
