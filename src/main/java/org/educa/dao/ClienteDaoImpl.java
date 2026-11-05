package org.educa.dao;

import org.educa.entity.ClienteEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDaoImpl implements ClienteDao{
    @Override
    public ClienteEntity findByDni(String dni) {
        ClienteEntity cliente = null;
        String sql = "SELECT * FROM clientes c WHERE c.dni = ?";

        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
        ){
            ps.setString(1,dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new ClienteEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    @Override
    public void saveCliente(ClienteEntity cliente) {
        String sql = "INSERT INTO clientes " +
                "(dni, nombre, p_apellido, s_apellido, email, telefono) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING id_cliente";
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getPrimerApellido());
            ps.setString(4, cliente.getSegundoApellido());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getTelefono());

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    cliente.setIdCliente(rs.getInt(1));
                }else{
                    throw new SQLException("No se ha podido insertar el usuario" );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar el cliente en la base de datos", e);
        }
    }
}
