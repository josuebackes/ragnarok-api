package com.organizadoraragnarok.api.controllers.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class GincanaResponse {

    private UUID id;

    private String nome;

    private String regulamentoOficial;

    private Boolean ativo;

    private String dataMesAno;
}
