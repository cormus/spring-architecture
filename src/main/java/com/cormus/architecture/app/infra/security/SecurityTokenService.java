package com.cormus.architecture.app.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cormus.architecture.app.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class SecurityTokenService {


    @Value("${app.api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API cormus.com")
                    .withSubject(usuario.getEmail())
                    .withClaim("nome", usuario.getNome())
                    .withExpiresAt(_dataExpiracaoToken())
                    .sign(algorithm);

        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao tentar gerar o token JWT.", exception);
        }
    }

    public String getTokenSubject(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API cormus.com")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado.", exception);
        }
    }

    private Instant _dataExpiracaoToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
