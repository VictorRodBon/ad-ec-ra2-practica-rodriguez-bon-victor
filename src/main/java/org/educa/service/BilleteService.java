package org.educa.service;

import org.educa.dao.BilleteDao;
import org.educa.dao.BilleteDaoImpl;
import org.educa.entity.BilleteEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BilleteService {

    private static final BilleteDao dao = new BilleteDaoImpl();
    private static final VueloService vueloService = new VueloService();

    /**
     * Calcula y asigna el precio final de un billete.
     * <p>
     * Parte del precio base del vuelo (0,75 € por minuto), le suma el suplemento
     * del asiento y aplica, en este orden:
     * <ul>
     *   <li>descuento infantil del 20 % si el pasajero tiene menos de 12 años
     *       en la fecha de salida del vuelo;</li>
     *   <li>descuento del 10 % si el cliente tiene más de tres reservas previas.</li>
     * </ul>
     * El precio final se redondea a dos decimales.
     *
     * @param billete     {@link BilleteEntity} al que se asigna el precio.
     * @param numReservas cantidad de reservas previas del cliente.
     */
    public void calculatePrecio(BilleteEntity billete, int numReservas) {
        // llamar a calcular precio

        BigDecimal precioFinal =vueloService.getPrecioBase(billete.getVuelo());

        if (billete.getAsiento() != null && billete.getAsiento().getSuplemento() != null) {
            precioFinal = precioFinal.add(billete.getAsiento().getSuplemento());
        }

        LocalDate fechaSalida = billete.getVuelo().getFecSalida().toLocalDate();
        long edad = ChronoUnit.YEARS.between(billete.getPasajero().getFecNac(), fechaSalida);
        if (edad < 12) {
            precioFinal = precioFinal.multiply(new BigDecimal("0.8"));
        }

        if (numReservas > 3) {
            precioFinal = precioFinal.multiply(new BigDecimal("0.9"));
        }

        billete.setPrecio(precioFinal.setScale(2, RoundingMode.HALF_UP));
    }


    /**
     * Almacena un {@link BilleteEntity} en la base de datos.
     *
     * @param connection conexión con la base de datos.
     * @param billete {@link BilleteEntity} a guardar en la base de datos.
     * @throws SQLException si falla la inserción en la base de datos.
     */
    public void save(Connection connection, BilleteEntity billete) throws SQLException {
        dao.insertar(connection, billete);
    }
}
