package com.dk.segurity;
import java.io.Serializable;
import java.security.Key;
import java.util.*;
import java.util.function.Function;


import com.dk.dao.UserDao;
import com.dk.modelo.Role;
import com.dk.modelo.Usuario;
import com.dk.servicio.UserService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil implements Serializable {



    private static String  ACCESS_TOKEN_SECRET   = "4QHQ8lREFfYcaRHxdb9zURb2rf8e7Ud";
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    public static String createToken(String email, String password, Role role){
        long expirationTime = JWT_TOKEN_VALIDITY * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", email);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(Collections.singletonMap("role", role.toString()))
                .addClaims(extra)//TODO
                .signWith(SignatureAlgorithm.HS512, ACCESS_TOKEN_SECRET.getBytes())
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token, UserDao dao){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();

           Usuario user = dao.getUsuarioByName(email);

           //TODO return new UsernamePasswordAuthenticationToken(email,null, Collections.emptyList());
            List<GrantedAuthority> mutableList = new ArrayList<>();
            mutableList.add(new SimpleGrantedAuthority("ROLE_"+ user.getRol().toString()));
            return new UsernamePasswordAuthenticationToken(email,null, mutableList);
        }catch (JwtException e){
            System.out.println(" ===== Sin acceso ====");

            return null;

        }
    }


}
