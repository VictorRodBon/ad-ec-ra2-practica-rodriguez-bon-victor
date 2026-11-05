package org.educa.dao;

import org.educa.entity.ClienteEntity;

public interface ClienteDao {
    /**
     * Busca un cliente en la base de datos mediante su DNI.
     *
     * @param dni DNI del cliente a buscar.
     * @return {@link ClienteEntity} encontrado, o {@code null} si no existe.
     */
    ClienteEntity findByDni(String dni);

    /**
     * Almacena un cliente en la base de datos.
     *
     * @param cliente {@link ClienteEntity} a guardar en la base de datos.
     */
    void saveCliente(ClienteEntity cliente);
}
