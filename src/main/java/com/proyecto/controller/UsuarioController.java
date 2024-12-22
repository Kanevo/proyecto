package com.proyecto.controller;

import com.proyecto.dto.UsuarioDto;
import com.proyecto.entity.Usuario;
import com.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro() {
        return "registrar"; // Retorna la vista del formulario
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@RequestParam String username, @RequestParam String password, Model model) {
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEnabled(true);

        try {
            usuarioService.saveUsuario(usuario);
            model.addAttribute("mensaje", "Usuario registrado exitosamente");
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar el usuario: " + e.getMessage());
        }
        return "redirect:/producto/inicio";
    }
}

