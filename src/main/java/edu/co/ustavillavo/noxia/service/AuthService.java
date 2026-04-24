package edu.co.ustavillavo.noxia.service;

import edu.co.ustavillavo.noxia.dto.AuthResponse;
import edu.co.ustavillavo.noxia.dto.LoginRequest;
import edu.co.ustavillavo.noxia.dto.RegisterRequest;
import edu.co.ustavillavo.noxia.model.Usuario;
import edu.co.ustavillavo.noxia.repository.UsuarioRepository;
import edu.co.ustavillavo.noxia.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse registrar(RegisterRequest request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El usuario ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(request.getRol() != null ? request.getRol() : Usuario.Rol.USER);

        usuarioRepository.save(usuario);

        String token = jwtService.generarToken(usuario.getUsername());
        return new AuthResponse(token, usuario.getUsername(), usuario.getRol().name());
    }

    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        String token = jwtService.generarToken(usuario.getUsername());
        return new AuthResponse(token, usuario.getUsername(), usuario.getRol().name());
    }
}