package com.dk.segurity;

import com.dk.modelo.Security.AuthRequest;
import com.dk.modelo.Security.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {

    public Authentication attempAuthentication(HttpServletRequest request, HttpServletResponse response){

        AuthRequest authCredentials = new AuthRequest();
        try{
            authCredentials = new ObjectMapper().readValue(request.getReader(),AuthRequest.class);
        }catch (IOException e){

        }

        UsernamePasswordAuthenticationToken userNamePat = new UsernamePasswordAuthenticationToken(
                authCredentials.getEmail(),
                authCredentials.getPassword(),
                //TODO  Arrays.asList(authCredentials.getRole())
                Collections.emptyList()
        );



        return getAuthenticationManager().authenticate(userNamePat);
    }

    protected void SuccessfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication AuthResult) throws IOException {

        UserDetailsImpl userDetails = (UserDetailsImpl) AuthResult.getPrincipal();
        String token = JWTUtil.createToken(userDetails.getNombre(),userDetails.getUsername(),userDetails.getRole());

        response.addHeader("Authorization", "Beaver"+token);
        response.getWriter().flush();

    }


}
