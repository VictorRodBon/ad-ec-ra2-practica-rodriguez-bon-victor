package org.educa.dao;

import org.educa.entity.ClienteEntity;
import org.educa.entity.ReservaEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ReservasDao {
    /**
     * Obtiene las reservas de un cliente a partir de su identificador.
     *
     * @param id identificador del {@link ClienteEntity}.
     * @return lista de {@link ReservaEntity} del cliente.
     */
    List<ReservaEntity> findByCliente(int id);

    /**
     * Almacena una nueva {@link ReservaEntity} en la base de datos.
     *
     * @param c conexión con la base de datos.
     * @param reserva {@link ReservaEntity} a guardar en la base de datos.
     * @return identificador de la reserva creada.
     * @throws SQLException si falla la inserción en la base de datos.
     */
    int saveReserva(Connection c, ReservaEntity reserva) throws SQLException;

    /**
     * Comprueba si ya existe en la base de datos una reserva con el código indicado.
     *
     * @param c conexión con la base de datos.
     * @param cod código de reserva a comprobar.
     * @return {@code true} si ya existe una reserva con ese código; {@code false} en caso contrario.
     * @throws SQLException si falla la consulta en la base de datos.
     */
    boolean existeCodigo(Connection c, String cod) throws SQLException;
}
