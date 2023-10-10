package com.cormus.architecture.app.infra.security;

import com.cormus.architecture.app.domain.entity.Usuario;
import com.cormus.architecture.app.domain.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    SecurityTokenService securityTokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenJwt = _tokenRecuperar(request);
        if(tokenJwt != null) {
            //Tell Spring that the user is logged in
            String email = securityTokenService.getTokenSubject(tokenJwt);
            UserDetails usuario = usuarioRepository.findByEmail(email);

            if(usuario == null){
                throw new RuntimeException("Usuário referente ao JWT token não encontrado.");
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        //Call next filter
        filterChain.doFilter(request, response);
    }

    private String _tokenRecuperar(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if(authorization != null){
            authorization = authorization.replace("Bearer ", "");
        }
        return authorization;
    }

}
