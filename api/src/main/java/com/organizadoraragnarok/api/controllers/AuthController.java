package com.organizadoraragnarok.api.controllers;

import com.organizadoraragnarok.api.config.GenerateTokenService;
import com.organizadoraragnarok.api.controllers.request.AuthRequest;
import com.organizadoraragnarok.api.controllers.response.AuthResponse;
import com.organizadoraragnarok.api.domain.UsuarioEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GenerateTokenService generateTokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest dadosAcesso) {
        var senha = new UsernamePasswordAuthenticationToken(dadosAcesso.getEmail(), dadosAcesso.getSenha());
        var auth = this.authenticationManager.authenticate(senha);

        var token = generateTokenService.generateToken((UsuarioEntity) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token));
    }

}
