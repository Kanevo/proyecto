package com.proyecto.service;

import com.proyecto.dto.ProductoDto;
import java.util.List;

public interface ProductoService {
    List<ProductoDto> findAllProductos();
    ProductoDto findProductoById(int id);
    Boolean updateProducto(ProductoDto productoDto);
    Boolean registerProducto(ProductoDto productoDto);
    Boolean deleteProducto(int id);
}