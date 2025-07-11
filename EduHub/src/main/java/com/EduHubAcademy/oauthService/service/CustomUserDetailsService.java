package com.EduHubAcademy.oauthService.service;

import com.EduHubAcademy.oauthService.model.User;
import com.EduHubAcademy.oauthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /*
     Este metodo Carga un usuario por su nombre de usuario desde la base de datos.
     Y spring Security usa esta clase durante el proceso de autenticación.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca usuario en la base de datos
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Aca convierto a un objeto que Spring Security entienda
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,  // CuentaNoExpirada
                true,  // credencialesNoExpiradas
                true,  // CuentaNoBloqueada
                Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
        );
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void save(User user) {
        // Encripta la contraseña antes de guardar
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
