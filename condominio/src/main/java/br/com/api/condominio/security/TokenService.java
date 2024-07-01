package br.com.api.condominio.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.api.condominio.Model.Condominio;
import br.com.api.condominio.Model.Morador;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String chave;

    public String gerarToken(Object user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(chave);
            String email = "";
            if (user instanceof Condominio) {
                email = ((Condominio) user).getEmail();
            } else if (user instanceof Morador) {
                email = ((Morador) user).getEmail();
            }

            String token = JWT.create()
                    .withIssuer("condominio")
                    .withSubject(email)
                    .withExpiresAt(this.tempoLimiteToken())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao autenticar");
        }
    }

    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(chave);
            return JWT.require(algorithm)
                    .withIssuer("condominio")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant tempoLimiteToken(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
