package com.FCarrena.demoparkapi.web.controller;

import com.FCarrena.demoparkapi.jwt.JwtToken;
import com.FCarrena.demoparkapi.jwt.JwtUserDatailService;
import com.FCarrena.demoparkapi.web.dto.UsuarioLoginDto;
import com.FCarrena.demoparkapi.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AutenticacaoController {

    private final JwtUserDatailService detailsService;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/auth")
    public ResponseEntity<?>auntenticar(@RequestBody @Valid UsuarioLoginDto dto, HttpServletRequest request){
        log.info("Processo de autenticação pelo login {}", dto.getUsername());
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

            authenticationManager.authenticate(authenticationToken);

            JwtToken token = detailsService.getTokenAuthenticated(dto.getUsername());

            return ResponseEntity.ok(token);
        } catch (AuthenticationException ex) {
            log.warn("Bad Credentials from username '{}'", dto.getUsername());
        }
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais Inválidas"));
    }

    }

