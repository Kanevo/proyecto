package com.proyecto.controller;

import com.proyecto.dto.UsuarioDto;
import com.proyecto.entity.Usuario;
import com.proyecto.service.UsuarioService;
import com.proyecto.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UsuarioDto usuarioDto) {
        // Buscar el usuario en la base de datos
        Usuario foundUsuario = usuarioService.findByUsername(usuarioDto.getUsername());

        // Verificar si el usuario existe y si la contraseña es correcta
        if (foundUsuario != null && passwordEncoder.matches(usuarioDto.getPassword(), foundUsuario.getPassword())) {
            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}