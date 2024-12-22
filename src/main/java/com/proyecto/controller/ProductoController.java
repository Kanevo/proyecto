package com.proyecto.controller;

import com.proyecto.dto.ProductoDto;
import com.proyecto.entity.Producto;
import com.proyecto.service.ProductoService;
import com.proyecto.service.impl.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        List<ProductoDto> productos = productoService.findAllProductos();
        model.addAttribute("productos", productos);
        return "producto";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Integer id, Model model) {
        ProductoDto productoDto = productoService.findProductoById(id);
        model.addAttribute("producto", productoDto);
        return "producto_detalle";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        ProductoDto productoDto = productoService.findProductoById(id);
        model.addAttribute("producto", productoDto);
        return "producto_editar";
    }

    @PostMapping("/editar-confirmar")
    public String editarConfirmar(@ModelAttribute ProductoDto productoDto) {
        productoService.updateProducto(productoDto);
        return "redirect:/producto/inicio";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new ProductoDto(null, "", "", 0.0, 0));
        return "producto_crear";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute ProductoDto productoDto) {
        productoService.registerProducto(productoDto);
        return "redirect:/producto/inicio";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        productoService.deleteProducto(id);
        return "redirect:/producto/inicio";
    }
}