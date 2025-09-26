package com.organizadoraragnarok.api.controllers;

import com.organizadoraragnarok.api.controllers.request.GincanaRequest;
import com.organizadoraragnarok.api.controllers.response.GincanaResponse;
import com.organizadoraragnarok.api.services.GincanaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gincanas")
@RequiredArgsConstructor
public class GincanaController {

    private final GincanaService gincanaService;

    @PostMapping
    public ResponseEntity<GincanaResponse> criar(@RequestBody GincanaRequest request) {
        GincanaResponse response = gincanaService.criar(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GincanaResponse> editar(@PathVariable UUID id, @RequestBody GincanaRequest request) {
        GincanaResponse response = gincanaService.editar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        gincanaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<GincanaResponse>> listar() {
        List<GincanaResponse> lista = gincanaService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GincanaResponse> buscarPorId(@PathVariable UUID id) {
        GincanaResponse response = gincanaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }
}
