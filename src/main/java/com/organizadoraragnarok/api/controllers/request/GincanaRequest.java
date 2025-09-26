package com.organizadoraragnarok.api.controllers.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class GincanaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String regulamentoOficial;

    @NotNull
    private Boolean ativo;

    @NotBlank
    private String dataMesAno;
}
