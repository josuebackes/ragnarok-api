package com.organizadoraragnarok.api.controllers.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class AuthRequest {

    @NotNull
    @Email
    private String email;

    @NotBlank
    private String senha;

}
