package com.prueba.backendFrutas.domain.mapper;

import com.prueba.backendFrutas.domain.Fruta;
import com.prueba.backendFrutas.domain.dto.CreateFrutaDto;
import com.prueba.backendFrutas.domain.dto.FrutaDto;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.Collections;

public class FrutaMapper {

    // Mapper de Fruta a FrutaDto
    public static FrutaDto toFrutaDto(Fruta fruta) {
        return new FrutaDto(
                fruta.getId(),
                fruta.getNombre(),
                fruta.getPrecio(),
                fruta.getFechaCreacion(),
                fruta.getFechaModificacion()
        );
    }

    // Mapper de FrutaDto a Fruta
    public static Fruta toFruta(FrutaDto frutaDto) {
        Fruta fruta = new Fruta();
        fruta.setId(frutaDto.id());
        fruta.setNombre(frutaDto.nombre());
        fruta.setPrecio(frutaDto.precio());
        fruta.setFechaCreacion(frutaDto.fechaCreacion());
        fruta.setFechaModificacion(frutaDto.fechaModificacion());
        return fruta;
    }
    public static Slice<FrutaDto> toCarrerasSlice(Slice<Fruta> carreras) {
        if (carreras == null) return new SliceImpl<>(Collections.emptyList());
        return carreras.map(FrutaMapper::toFrutaDto);
    }
    public  static  Fruta createFrutaDtoToFruta(CreateFrutaDto createFrutaDto){
        return Fruta.builder()
                .nombre(createFrutaDto.nombre().toLowerCase())
                .precio(createFrutaDto.precio())
                .build();
    }
}
