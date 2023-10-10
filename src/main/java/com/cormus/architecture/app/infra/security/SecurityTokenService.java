package com.cormus.architecture.app.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
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
                    .withExpiresAt(dataExpiracaoToken())
                    .sign(algorithm);

        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao tentar gerar o token JWT", exception);
        }
    }

    private Instant dataExpiracaoToken() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
