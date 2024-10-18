package com.prueba.backendFrutas.controller;

import com.prueba.backendFrutas.domain.dto.CreateFrutaDto;
import com.prueba.backendFrutas.domain.dto.FrutaDto;
import com.prueba.backendFrutas.domain.dto.UpdateFrutaDto;
import com.prueba.backendFrutas.usecase.IFrutaUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/frutas")
public class FrutaController {
    private final IFrutaUseCase frutaUseCase;

    @GetMapping()
    public ResponseEntity<Slice<FrutaDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(frutaUseCase.findAll(page, size), HttpStatus.OK);
    }
    @GetMapping("/{nombre}")
    public ResponseEntity<FrutaDto> findByNombre(
            @PathVariable String nombre
    ) {
        return new ResponseEntity<>(frutaUseCase.findByNombre(nombre), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> findAll(
            @PathVariable String id
    ) {
            String response = frutaUseCase.deleteById(id);
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", response);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<FrutaDto> saveCarrera(
            @Valid @RequestBody CreateFrutaDto createFrutaDto
    ) {
        return new ResponseEntity<>(frutaUseCase.save(createFrutaDto), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FrutaDto> updateFruta(
            @PathVariable UUID id,
            @RequestBody UpdateFrutaDto updateFrutaDto) {
        return new ResponseEntity<>(frutaUseCase.update(id,updateFrutaDto), HttpStatus.OK);
    }
}
