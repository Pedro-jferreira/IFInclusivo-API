package com.example.IfGoiano.IfCoders.security;

import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.exception.TokenExpiredException;
import com.example.IfGoiano.IfCoders.exception.TokenInvalidException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.auth.expiration}")
    private long authExpiration;

    @Value("${jwt.email.expiration}")
    private long emailExpiration;
    @Value("${jwt.reset.expiration}")
    private long resetExpiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAuthToken(UsuarioEntity usuario) {
        return Jwts.builder()
                .setSubject(usuario.getLogin())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("type", "AUTH")
                .setExpiration(new Date(System.currentTimeMillis() + authExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateEmailVerificationToken(UsuarioEntity usuario) {
        return Jwts.builder()
                .setSubject(usuario.getLogin())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("type", "EMAIL_VERIFICATION")
                .setExpiration(new Date(System.currentTimeMillis() + emailExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generatePasswordResetToken(UsuarioEntity usuario) {
        return Jwts.builder()
                .setSubject(usuario.getLogin())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("type", "RESET_PASSWORD")
                .setExpiration(new Date(System.currentTimeMillis() + resetExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token); // se não lançar exceção, é válido
            return true;
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("Token expired");
        } catch (JwtException e){
            throw new TokenInvalidException("Token Invalido");
        }
    }
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractTokenType(String token) {
        return extractAllClaims(token).get("type", String.class);
    }

    public String extractUsernameFromExpiredToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // usa Key, não String
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
