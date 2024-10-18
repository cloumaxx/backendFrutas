package com.prueba.backendFrutas.domain.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public record FrutaDto(

        UUID id,
        String nombre,
        double  precio,

        Timestamp fechaCreacion,
        Timestamp fechaModificacion
) {
}
