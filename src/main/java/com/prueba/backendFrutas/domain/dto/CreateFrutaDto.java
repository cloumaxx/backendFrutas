package com.prueba.backendFrutas.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.UUID;

public record CreateFrutaDto(
        @NotNull(message = "El campo nombre no puede ser nulo")

        String nombre,
        @NotNull(message = "El campo precio no puede ser nulo")

        double  precio


) {
}
