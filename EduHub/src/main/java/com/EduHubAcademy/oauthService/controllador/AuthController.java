package com.EduHubAcademy.oauthService.controllador;

import com.EduHubAcademy.oauthService.dto.AuthRequest;
import com.EduHubAcademy.oauthService.dto.AuthResponse;
import com.EduHubAcademy.oauthService.dto.RegisterRequest;
import com.EduHubAcademy.oauthService.model.User;
import com.EduHubAcademy.oauthService.security.JwtUtil;
import com.EduHubAcademy.oauthService.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    /* Autentico al usuario con AuthenticationManager.
       Cargo sus detalles con CustomUserDetailsService.
       Genero un token JWT con JwtUtil.*/

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // Endpoint público /login.
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    //endpoint publico/registro.
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            // Valida si el usuario ya existe (depende de la lógica y repositorio)
            if (userDetailsService.existsByUsername(registerRequest.getUsername())) {
                return ResponseEntity.badRequest().body("El usuario ya existe");
            }

            // para creear nuevo usuario (puedo crear una entidad Usuario)
            User newUser = new User();
            newUser.setUsername(registerRequest.getUsername());
            newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));


            userDetailsService.save(newUser);

            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar usuario");
        }
    }

}


