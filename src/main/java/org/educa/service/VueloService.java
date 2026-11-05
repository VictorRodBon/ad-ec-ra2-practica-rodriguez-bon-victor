package org.educa.service;

import org.educa.dao.VueloDao;
import org.educa.dao.VueloDaoImpl;
import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

public class VueloService {
    private static final VueloDao dao = new VueloDaoImpl();

    /**
     * Obtiene los vuelos que parten de un aeropuerto indicado.
     *
     * @param origen {@link AeropuertoEntity} de origen.
     * @return lista de {@link VueloEntity}.
     * @throws SQLException si falla la consulta en la base de datos.
     */
    public List<VueloEntity> findByOrigen(AeropuertoEntity origen) throws SQLException {
        return dao.getVuelosPorOrigen(origen);
    }

    /**
     * Obtiene el precio base de un vuelo.
     *
     * @param vuelo {@link VueloEntity} del que se calcula el precio base.
     * @return precio base del vuelo.
     */
    public BigDecimal getPrecioBase(VueloEntity vuelo) {
        BigDecimal precioBase=null;

        Long duration = Duration.between(vuelo.getFecSalida(), vuelo.getFecLlegada()).toMinutes();

        precioBase= new BigDecimal("0.75").multiply(new BigDecimal(duration));

        return precioBase;
    }
}
