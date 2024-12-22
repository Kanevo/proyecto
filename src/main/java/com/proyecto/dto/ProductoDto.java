package com.proyecto.dto;

public record ProductoDto(
        Integer id,
        String nombre,
        String descripcion,
        Double precio,
        Integer stock) {
}
