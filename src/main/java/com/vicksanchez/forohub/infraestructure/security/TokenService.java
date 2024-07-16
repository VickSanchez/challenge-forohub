package com.vicksanchez.forohub.infraestructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vicksanchez.forohub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String secretJwt;

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretJwt);
            return JWT.create()
                    .withIssuer("forohub")
                    .withSubject(usuario.getLogin())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(generarfechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException();
        }
    }

    private Instant generarfechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }

    public String getSubject(String token){
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretJwt); // valida firma
            verifier = JWT.require(algorithm)
                    .withIssuer("forohub") //  valida el issuer
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception){
            // Invalid signature/claims
        }
        if (verifier.getSubject() == null){
            throw new RuntimeException("Verifier inválido");
        }
        return verifier.getSubject();
    }
}
