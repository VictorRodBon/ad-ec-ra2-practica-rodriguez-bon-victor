package org.educa.dao;

import org.educa.entity.ReservaEntity;

import java.util.List;

public interface ReservasDao {
    List<ReservaEntity> findByCliente(int id);
    void saveReserva(ReservaEntity reserva);
}
