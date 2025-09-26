package com.organizadoraragnarok.api.services;

import com.organizadoraragnarok.api.controllers.request.TarefaRequest;
import com.organizadoraragnarok.api.controllers.response.TarefaResponse;
import com.organizadoraragnarok.api.domain.GincanaEntity;
import com.organizadoraragnarok.api.domain.Setor;
import com.organizadoraragnarok.api.domain.TarefaEntity;
import com.organizadoraragnarok.api.mapper.TarefaMapper;
import com.organizadoraragnarok.api.repository.GincanaRepository;
import com.organizadoraragnarok.api.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final GincanaRepository gincanaRepository;

    public TarefaResponse criar(TarefaRequest request) {
        GincanaEntity gincana = gincanaRepository.findById(request.getGincanaId())
                .orElseThrow(() -> new EntityNotFoundException("Gincana não encontrada"));
        TarefaEntity entity = TarefaMapper.toEntity(request, gincana);
        entity = tarefaRepository.save(entity);
        return TarefaMapper.toResponse(entity);
    }

    public TarefaResponse editar(UUID id, TarefaRequest request) {
        TarefaEntity entity = tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada"));
        GincanaEntity gincana = gincanaRepository.findById(request.getGincanaId())
                .orElseThrow(() -> new EntityNotFoundException("Gincana não encontrada"));

        entity.setNome(request.getNome());
        entity.setUrl(request.getUrl());
        entity.setDataHoraPublicacao(request.getDataHoraPublicacao());
        entity.setSetor(request.getSetor());
        entity.setGincana(gincana);

        entity = tarefaRepository.save(entity);
        return TarefaMapper.toResponse(entity);
    }

    public void excluir(UUID id) {
        if (!tarefaRepository.existsById(id)) {
            throw new EntityNotFoundException("Tarefa não encontrada");
        }
        tarefaRepository.deleteById(id);
    }

    public Page<TarefaResponse> listar(UUID gincanaId, Pageable pageable) {
        OffsetDateTime agoraBrasilia = OffsetDateTime.now(ZoneId.of("America/Sao_Paulo"));
        return tarefaRepository.findByGincanaIdAndDataHoraPublicacaoLessThanEqual(gincanaId, agoraBrasilia, pageable)
                .map(TarefaMapper::toResponse);
    }

    public Page<TarefaResponse> listarPorGincanaESetor(UUID gincanaId, String setor, Pageable pageable) {
        OffsetDateTime agoraBrasilia = OffsetDateTime.now(ZoneId.of("America/Sao_Paulo"));
        Setor setorEnum = Setor.valueOf(setor);
        return tarefaRepository.findByGincanaIdAndSetorAndDataHoraPublicacaoLessThanEqual(gincanaId, setorEnum, agoraBrasilia, pageable)
                .map(TarefaMapper::toResponse);
    }

    public Page<TarefaResponse> listarTodasPorGincana(UUID gincanaId, Pageable pageable) {
        return tarefaRepository.findByGincanaId(gincanaId, pageable)
                .map(TarefaMapper::toResponse);
    }

    public Page<TarefaResponse> listarTodasPorGincanaESetor(UUID gincanaId, String setor, Pageable pageable) {
        Setor setorEnum = Setor.valueOf(setor);
        return tarefaRepository.findByGincanaIdAndSetor(gincanaId, setorEnum, pageable)
                .map(TarefaMapper::toResponse);
    }
}
