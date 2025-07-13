package com.perfulandia.controller;

import com.perfulandia.model.Producto;
import com.perfulandia.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos() {
        List<Producto> productos = productoService.listarTodos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Producto>> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.buscarPorId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<Producto> recurso = EntityModel.of(producto);

        recurso.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ProductoController.class).obtenerProductoPorId(id)
        ).withSelfRel());

        recurso.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ProductoController.class).listarTodos()
        ).withRel("todos-los-productos"));

        return ResponseEntity.ok(recurso);
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevo = productoService.guardar(producto);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto actualizado = productoService.actualizar(id, producto);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        boolean eliminado = productoService.eliminar(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
