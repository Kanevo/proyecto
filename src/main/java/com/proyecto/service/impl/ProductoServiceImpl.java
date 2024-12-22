package com.proyecto.service.impl;

import com.proyecto.dto.ProductoDto;
import com.proyecto.entity.Producto;
import com.proyecto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.service.ProductoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDto> findAllProductos() {
        List<ProductoDto> productos = new ArrayList<>();
        Iterable<Producto> iterable = productoRepository.findAll();
        iterable.forEach(producto -> productos.add(new ProductoDto(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock()
        )));
        return productos;
    }

    @Override
    public ProductoDto findProductoById(int id) {
        Optional<Producto> optional = productoRepository.findById(id);
        return optional.map(producto -> new ProductoDto(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock()
        )).orElse(null);
    }

    @Override
    public Boolean updateProducto(ProductoDto productoDto) {
        Optional<Producto> optional = productoRepository.findById(productoDto.id());
        return optional.map(producto -> {
            producto.setNombre(productoDto.nombre());
            producto.setDescripcion(productoDto.descripcion());
            producto.setPrecio(productoDto.precio());
            producto.setStock(productoDto.stock());
            productoRepository.save(producto);
            return true;
        }).orElse(false);
    }

    @Override
    public Boolean registerProducto(ProductoDto productoDto) {
        Producto producto = new Producto();
        producto.setNombre(productoDto.nombre());
        producto.setDescripcion(productoDto.descripcion());
        producto.setPrecio(productoDto.precio());
        producto.setStock(productoDto.stock());
        productoRepository.save(producto);
        return true;
    }

    @Override
    public Boolean deleteProducto(int id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
