# PRÁCTICA RA2-BASE DE DATOS RELACIONALES

## Apartado 1 (2 puntos)

Cree un programa para realizar reservas de vuelos. Para ello el sistema solicitará el DNI del cliente y comprobará si ya es cliente. Si no lo es, el sistema solicitará los datos del cliente (nombre, primer apellido, segundo apellido, email, telefono) para darlo de alta. Si el cliente ya estaba registrado en el sistema, mostrará una lista con todas sus reservas previas, indicando el código de la reserva, la fecha y su estado.

## Apartado 2 (2 puntos)

Una vez el cliente esté registrado en el sistema se le mostrará el proceso de reserva.

Se le pedirá el aeropuerto de origen, mostrando los aeropuertos que hay en el sistema:
1. MAD - Adolfo Suárez Madrid-Barajas (Madrid)
2. BCN - Josep Tarradellas Barcelona-El Prat (Barcelona)
3. SVQ - San Pablo (Sevilla)
4. BIO - Bilbao (Bilbao)
5. CDG - Charles de Gaulle (París)
6. ...

Una vez se elija el aeropuerto, se mostrarán los vuelos que salen de él y que no estén cancelados, indicando el aeropuerto de destini, la fecha y hora de salida y de llegada, y el precio base del buelo:

1. IB3100 MAD -> BCN (Barcelona) | Salida: 15/10/2026 07:00 | Llegada: 15/10/2026 08:15 (56.25€)
2. IB3450 MAD -> CDG (París) | Salida: 15/10/2026 11:00 | Llegada: 15/10/2026 13:10 (97.50€)
3. IB3620 MAD -> FCO (Roma) | Salida: 16/10/2026 15:30 | Llegada: 16/10/2026 18:05 (116.25€)

El precio base del vuelo se calcula según su duración:
- 0.75€ por cada minuto de vuelo (tiempo transcurrido entre la fecha de salida y la de llegada).

## Apartado 3 (2 puntos)

Una vez elegigo el vuelo se muestran los asientos libres del avión que realiza ese vuelo, es decir, los asientos del avión que todavía no tienen un billete vendido para ese vuelo:

1. 1C - BUSINESS (+120.00€)
2. 1D - BUSINESS (+120.00€)
3. 3A - TURISTA (+0.00€)

Cada asiento tiene un suplemento que depende de su clase:

- TURISTA (0€)
- PREMIUM_ECONOMY (35€)
- BUSINESS (120€)
- FIRST (250€)

## Apartado 4 (4 puntos)

Tras pedir la fecha de nacimiento del pasajero, se muestra el precio final del billete, teniendo en cuenta:

- El precio base del vuelo
- El suplemento del asiento
- Si el pasajero tiene menos de 12 años en la fecha de salida dle vuelo se le hace un descuento infantil del 20%
- Si el cliente tiene más de tres reservas previas, se le hace un descuento del 10%
- Los descuentos se aplican en ese orden y el precio se redondea a 2 decimales.

Si el cliente acepta, se guarda la reserva. En este proceso, el único pasajero de la reserva será el propio cliente. Guardar la reserva indica insertar **en una única transacción**: la reserva (con un código alfanumérico en MAYÚSCULAS generado aleatoriamente, **que no puede coincidir con el de ninguna reserva existente**), el pasajero asociado a esa reserva y su billete. Si falla cualquiera de las inserciones, **no debe quedar nada registrado**.

Ten en cuenta que las columnas _fec_reserva_ y _estado_ de la tabla _reservas_ tienen valores por defecto _fecha actual_ y _CONFIRMADA_.