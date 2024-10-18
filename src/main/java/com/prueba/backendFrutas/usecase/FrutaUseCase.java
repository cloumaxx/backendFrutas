package com.prueba.backendFrutas.usecase;

import com.prueba.backendFrutas.domain.Fruta;
import com.prueba.backendFrutas.domain.dto.CreateFrutaDto;
import com.prueba.backendFrutas.domain.dto.FrutaDto;
import com.prueba.backendFrutas.domain.dto.UpdateFrutaDto;
import com.prueba.backendFrutas.domain.mapper.FrutaMapper;
import com.prueba.backendFrutas.repository.FrutaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class FrutaUseCase implements  IFrutaUseCase {
    private final FrutaRepository frutaRepository;
    @Override
    public Slice<FrutaDto> findAll(int page, int size) {
        try {
            Pageable pagination = PageRequest.of(page, size);
            Slice<Fruta> carreras = frutaRepository.findAll(pagination);
            return FrutaMapper.toCarrerasSlice(carreras);
        } catch (Exception e) {
            log.error("Error encontrando todas las Frutas. " + e.getMessage());
            throw new RuntimeException("Error encontrando todas las Frutas. " + e.getMessage());
        }
    }
    @Override
    public FrutaDto findByNombre(String nombre) {
        nombre=nombre.toLowerCase();
        Optional<Fruta> fruta = frutaRepository.findByNombre(nombre);
        if (fruta.isEmpty()) {
            throw new RuntimeException("No se ha encontrado la fruta");
        }
        return FrutaMapper.toFrutaDto(fruta.get());
    }
    @Override
    public FrutaDto findById(String idFruta) {
        UUID id = UUID.fromString(idFruta);

        Optional<Fruta> fruta = frutaRepository.findById(id);
        if (fruta.isEmpty()) {
            throw new RuntimeException("No se ha encontrado la fruta");
        }
        return FrutaMapper.toFrutaDto(fruta.get());
    }
    @Override
    public String deleteById(String idFruta) {
        UUID id = UUID.fromString(idFruta);

        Optional<Fruta> fruta = frutaRepository.findById(id);
        if (fruta.isEmpty()) {
            throw new RuntimeException("No se ha encontrado la fruta");
        }
        frutaRepository.deleteById(id);
        return "Eliminado exitosamente";
    }
    @Override
    public FrutaDto save(CreateFrutaDto createFrutaDto) {
        boolean existfruta = frutaRepository.existsByNombre(
                createFrutaDto.nombre().toLowerCase()
        );

        if (existfruta) {
            throw new RuntimeException("Ya se encuentra registrada una fruta con nombre: " + createFrutaDto.nombre());
        }
        Fruta fruta = FrutaMapper.createFrutaDtoToFruta(createFrutaDto);
        fruta = frutaRepository.save(fruta);

        return FrutaMapper.toFrutaDto(fruta);
    }
    @Override
    public FrutaDto update(UUID id, UpdateFrutaDto updateFrutaDto) {
        Fruta frutaExistente = frutaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fruta no encontrada con ID: " + id));

        String nombreEnMinusculas = updateFrutaDto.nombre().toLowerCase();

        boolean existeOtraFruta = frutaRepository.existsByNombre(nombreEnMinusculas);

        if (existeOtraFruta && !frutaExistente.getNombre().equals(nombreEnMinusculas)) {
            throw new RuntimeException("Ya se encuentra registrada una fruta con nombre: " + nombreEnMinusculas);
        }

        // Actualizar los campos de la fruta existente
        frutaExistente.setNombre(nombreEnMinusculas);
        frutaExistente.setPrecio(updateFrutaDto.precio());

        // Guardar la entidad actualizada
        frutaExistente = frutaRepository.save(frutaExistente);

        return FrutaMapper.toFrutaDto(frutaExistente);
    }
}
