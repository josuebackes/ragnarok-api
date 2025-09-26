package com.organizadoraragnarok.api.controllers.response;

import com.organizadoraragnarok.api.domain.Setor;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TarefaResponse {

    private UUID id;

    private String nome;

    private String url;

    private OffsetDateTime dataHoraPublicacao;

    private Setor setor;

    private UUID gincanaId;
}