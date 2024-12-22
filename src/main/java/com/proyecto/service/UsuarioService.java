package com.proyecto.service;

import com.proyecto.entity.Usuario;

public interface UsuarioService {
    Usuario saveUsuario(Usuario usuario);
    Usuario findByUsername(String username);
}