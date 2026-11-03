package org.educa.dao;

import org.educa.entity.ClienteEntity;

public interface ClienteDao {
    ClienteEntity findByDni(String dni);
    void saveCliente(ClienteEntity cliente);
}
