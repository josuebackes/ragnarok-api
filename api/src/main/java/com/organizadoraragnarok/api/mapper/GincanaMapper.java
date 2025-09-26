package com.organizadoraragnarok.api.mapper;

import com.organizadoraragnarok.api.controllers.request.GincanaRequest;
import com.organizadoraragnarok.api.controllers.response.GincanaResponse;
import com.organizadoraragnarok.api.domain.GincanaEntity;
import lombok.Builder;

@Builder
public class GincanaMapper {

    public static GincanaEntity toEntity(GincanaRequest request) {
        return GincanaEntity.builder()
                .nome(request.getNome())
                .regulamentoOficial(request.getRegulamentoOficial())
                .ativo(request.getAtivo())
                .dataMesAno(request.getDataMesAno())
                .build();
    }

    public static GincanaResponse toResponse(GincanaEntity entity) {
        return GincanaResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .regulamentoOficial(entity.getRegulamentoOficial())
                .ativo(entity.getAtivo())
                .dataMesAno(entity.getDataMesAno())
                .build();
    }
}
