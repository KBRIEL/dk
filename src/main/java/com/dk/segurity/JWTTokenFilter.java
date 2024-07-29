package com.dk.segurity;
import java.io.IOException;

import com.dk.dao.UserDao;
import com.dk.servicio.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JWTTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userDetailsService;

    @Autowired
    private JWTUtil jwtTokenUtil;
    @Autowired
    private UserDao dao;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            //se quita el Bearer del token
            String token = requestTokenHeader.replace("Bearer","");

            UsernamePasswordAuthenticationToken usernamePat = JWTUtil.getAuthentication(token,dao);
            SecurityContextHolder.getContext().setAuthentication(usernamePat);


        }
        chain.doFilter(request, response);
    }


}
