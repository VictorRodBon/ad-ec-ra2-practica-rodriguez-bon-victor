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



    public void save(Connection connection, BilleteEntity billete) throws SQLException {
        dao.insertar(connection, billete);
    }
}
