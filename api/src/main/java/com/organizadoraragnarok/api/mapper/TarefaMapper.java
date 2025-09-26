package com.organizadoraragnarok.api.mapper;

import com.organizadoraragnarok.api.controllers.request.TarefaRequest;
import com.organizadoraragnarok.api.controllers.response.TarefaResponse;
import com.organizadoraragnarok.api.domain.GincanaEntity;
import com.organizadoraragnarok.api.domain.TarefaEntity;

public class TarefaMapper {
    public static TarefaEntity toEntity(TarefaRequest request, GincanaEntity entity) {
        return TarefaEntity.builder()
                .nome(request.getNome()) // NOVO CAMPO
                .url(request.getUrl())
                .dataHoraPublicacao(request.getDataHoraPublicacao())
                .setor(request.getSetor())
                .gincana(entity)
                .build();
    }

    public static TarefaResponse toResponse(TarefaEntity entity) {
        return TarefaResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome()) // NOVO CAMPO
                .url(entity.getUrl())
                .dataHoraPublicacao(entity.getDataHoraPublicacao())
                .setor(entity.getSetor())
                .gincanaId(entity.getGincana().getId())
                .build();
    }
}
