package com.dk.controller;
import com.dk.dao.UserDao;
import com.dk.modelo.Role;
import com.dk.modelo.Security.AuthRequest;
import com.dk.modelo.Security.AuthResponse;
import com.dk.modelo.Security.RegisterRequest;
import com.dk.modelo.Security.UserDetailsImpl;
import com.dk.modelo.Usuario;
import com.dk.segurity.JWTUtil;
import com.dk.servicio.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired(required = true)
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;






    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String token = jwtUtil.createToken(userDetails.getNombre(), userDetails.getUsername(),userDetails.getRole());

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest register){
        String pass = passwordEncoder.encode(register.getPassword());

        Usuario user = new Usuario(register.getName(), register.getEmail(), Role.USER, pass);

        try{

            userService.register(user);
            return ResponseEntity.ok("registro exitoso");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha podido efectuar el registro");
        }


    }

}
