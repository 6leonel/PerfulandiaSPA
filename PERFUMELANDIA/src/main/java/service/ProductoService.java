package com.perfulandia.service;

import com.perfulandia.model.Producto;
import com.perfulandia.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizar(Long id, Producto productoActualizado) {
        Optional<Producto> optional = productoRepository.findById(id);
        if (optional.isPresent()) {
            Producto producto = optional.get();
            producto.setNombre(productoActualizado.getNombre());
            // Agrega más campos aquí si los tienes (ej: precio, stock, etc.)
            return productoRepository.save(producto);
        }
        return null;
    }

    public boolean eliminar(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
