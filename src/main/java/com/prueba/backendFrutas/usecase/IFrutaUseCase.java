package com.prueba.backendFrutas.usecase;

import com.prueba.backendFrutas.domain.dto.CreateFrutaDto;
import com.prueba.backendFrutas.domain.dto.FrutaDto;
import com.prueba.backendFrutas.domain.dto.UpdateFrutaDto;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface IFrutaUseCase {
    public Slice<FrutaDto> findAll(int page, int size);
    public FrutaDto findByNombre(String nombre);

    public FrutaDto save(CreateFrutaDto createFrutaDto);
    public FrutaDto update(UUID id, UpdateFrutaDto updateFrutaDto);
    public FrutaDto findById(String idFruta);
    public String deleteById(String id);
}
