package com.organizadoraragnarok.api.services;

import com.organizadoraragnarok.api.controllers.request.GincanaRequest;
import com.organizadoraragnarok.api.controllers.response.GincanaResponse;
import com.organizadoraragnarok.api.domain.GincanaEntity;
import com.organizadoraragnarok.api.mapper.GincanaMapper;
import com.organizadoraragnarok.api.repository.GincanaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GincanaService {

    private final GincanaRepository gincanaRepository;

    public GincanaResponse criar(GincanaRequest request) {
        GincanaEntity entity = GincanaMapper.toEntity(request);
        entity = gincanaRepository.save(entity);
        return GincanaMapper.toResponse(entity);
    }

    public GincanaResponse editar(UUID id, GincanaRequest request) {
        GincanaEntity entity = gincanaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gincana não encontrada"));

        entity.setNome(request.getNome());
        entity.setRegulamentoOficial(request.getRegulamentoOficial());
        entity.setAtivo(request.getAtivo());
        entity.setDataMesAno(request.getDataMesAno());

        entity = gincanaRepository.save(entity);
        return GincanaMapper.toResponse(entity);
    }

    public void excluir(UUID id) {
        if (!gincanaRepository.existsById(id)) {
            throw new EntityNotFoundException("Gincana não encontrada");
        }
        gincanaRepository.deleteById(id);
    }

    public List<GincanaResponse> listar() {
        return gincanaRepository.findAll().stream()
                .map(GincanaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public GincanaResponse buscarPorId(UUID id) {
        GincanaEntity entity = gincanaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gincana não encontrada"));
        return GincanaMapper.toResponse(entity);
    }
}
