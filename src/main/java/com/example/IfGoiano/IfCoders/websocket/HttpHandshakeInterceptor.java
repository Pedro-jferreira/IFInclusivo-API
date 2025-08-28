package com.example.IfGoiano.IfCoders.websocket;

import com.example.IfGoiano.IfCoders.security.CustomUserDetailsService;
import com.example.IfGoiano.IfCoders.security.TokenService;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class HttpHandshakeInterceptor implements HandshakeInterceptor {
    private final TokenService tokenService;
    private final CustomUserDetailsService userDetailsService;

    public HttpHandshakeInterceptor(TokenService tokenService, CustomUserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest servletRequest) {
            String token = servletRequest.getServletRequest().getParameter("token");

            if (token != null && tokenService.isTokenValid(token)) {
                String username = tokenService.extractUsername(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // não precisa setar o SecurityContext aqui, porque ele some depois
                attributes.put("username", userDetails.getUsername());

                return true;
            }
        }
        return false; // handshake negado se token inválido
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
    }
}
