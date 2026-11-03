package org.educa.service;

import org.educa.dao.ClienteDao;
import org.educa.dao.ClienteDaoImpl;
import org.educa.entity.ClienteEntity;

import java.sql.SQLException;

public class ClienteService {

    ClienteDao clienteDao = new ClienteDaoImpl();

    public ClienteEntity findByDNI(String dni) throws SQLException {
        ClienteEntity cliente = clienteDao.findByDni(dni);
        return cliente;
    }

    public void saveCliente(ClienteEntity cliente) throws SQLException {
        clienteDao.saveCliente(cliente);
    }
}
