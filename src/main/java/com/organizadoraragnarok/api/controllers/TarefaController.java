package com.organizadoraragnarok.api.controllers;

import com.organizadoraragnarok.api.controllers.request.TarefaRequest;
import com.organizadoraragnarok.api.controllers.response.TarefaResponse;
import com.organizadoraragnarok.api.services.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaResponse> criar(@RequestBody TarefaRequest request) {
        return ResponseEntity.ok(tarefaService.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponse> editar(@PathVariable UUID id, @RequestBody TarefaRequest request) {
        return ResponseEntity.ok(tarefaService.editar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        tarefaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{gincanaId}")
    public ResponseEntity<Page<TarefaResponse>> listarPorGincana(
            @PathVariable UUID gincanaId,
            Pageable pageable) {
        return ResponseEntity.ok(tarefaService.listar(gincanaId, pageable));
    }

    @GetMapping("/{gincanaId}/setor/{setor}")
    public ResponseEntity<Page<TarefaResponse>> listarPorGincanaESetor(
            @PathVariable UUID gincanaId,
            @PathVariable String setor,
            Pageable pageable) {
        return ResponseEntity.ok(tarefaService.listarPorGincanaESetor(gincanaId, setor, pageable));
    }

    @GetMapping("/admin")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<TarefaResponse>> listarAdmin(
            @RequestParam UUID gincanaId,
            @RequestParam(required = false) String setor,
            Pageable pageable) {
        if (setor != null) {
            return ResponseEntity.ok(tarefaService.listarTodasPorGincanaESetor(gincanaId, setor, pageable));
        } else {
            return ResponseEntity.ok(tarefaService.listarTodasPorGincana(gincanaId, pageable));
        }
    }
}
