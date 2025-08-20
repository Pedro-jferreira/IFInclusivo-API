package com.example.IfGoiano.IfCoders.security;

import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    // tempo em milissegundos (configurável em application.properties)
    @Value("${jwt.auth.expiration}")
    private long authExpiration;

    @Value("${jwt.email.expiration}")
    private long emailExpiration;

    // =============================================================
    // TOKEN PARA AUTENTICAÇÃO (LOGIN NORMAL)
    // =============================================================
    public String generateAuthToken(UsuarioEntity usuario) {
        return Jwts.builder()
                .setSubject(usuario.getLogin())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("type", "AUTH")
                .setExpiration(new Date(System.currentTimeMillis() + authExpiration))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    // =============================================================
    // TOKEN PARA CONFIRMAR E-MAIL
    // =============================================================
    public String generateEmailVerificationToken(UsuarioEntity usuario) {
        return Jwts.builder()
                .setSubject(usuario.getLogin())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("type", "EMAIL_VERIFICATION")
                .setExpiration(new Date(System.currentTimeMillis() + emailExpiration))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    // =============================================================
    // VALIDAÇÕES ÚTEIS
    // =============================================================
    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractTokenType(String token) {
        return extractAllClaims(token).get("type", String.class);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}
