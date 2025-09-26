package com.organizadoraragnarok.api.controllers.request;

import com.organizadoraragnarok.api.domain.Setor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaRequest {

    @NotBlank
    private String nome; // NOVO CAMPO

    @NotBlank
    private String url;

    @NotNull
    private OffsetDateTime dataHoraPublicacao;

    private Setor setor;

    @NotNull
    private UUID gincanaId;
}
