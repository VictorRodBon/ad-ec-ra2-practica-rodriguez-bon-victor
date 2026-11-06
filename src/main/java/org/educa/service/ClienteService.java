package org.educa.service;

import org.educa.dao.ClienteDao;
import org.educa.dao.ClienteDaoImpl;
import org.educa.entity.ClienteEntity;

import java.sql.SQLException;

public class ClienteService {

    private static final ClienteDao clienteDao = new ClienteDaoImpl();

    /**
     * Busca un cliente en la base de datos mediante su DNI.
     *
     * @param dni DNI del cliente a buscar.
     * @return {@link ClienteEntity} encontrado, o {@code null} si no existe.
     * @throws SQLException si falla la consulta en la base de datos.
     */
    public ClienteEntity findByDNI(String dni) throws SQLException {
        ClienteEntity cliente = clienteDao.findByDni(dni);
        return cliente;
    }

    /**
     * Almacena un {@link ClienteEntity} en la base de datos.
     *
     * @param cliente {@link ClienteEntity} a guardar en la base de datos.
     * @throws SQLException si falla la inserción en la base de datos.
     */
    public void saveCliente(ClienteEntity cliente) throws SQLException {
        clienteDao.saveCliente(cliente);
    }
}
