package com.perfulandia.controller;

import com.perfulandia.model.Inventory;
import com.perfulandia.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Operation(
            summary = "Listar todos los productos",
            description = "Devuelve una lista completa del inventario actual."
    )
    @GetMapping
    public List<Inventory> getAllItems() {
        return inventoryService.getAllItems();
    }

    @Operation(
            summary = "Obtener producto por ID",
            description = "Devuelve los datos de un producto específico según su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto encontrado",
                            content = @Content(schema = @Schema(implementation = Inventory.class))),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getItemById(@PathVariable Long id) {
        Inventory item = inventoryService.getItemById(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Crear un nuevo producto",
            description = "Registra un nuevo ítem en el inventario.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            }
    )
    @PostMapping
    public Inventory createItem(@RequestBody Inventory item) {
        return inventoryService.createItem(item);
    }

    @Operation(
            summary = "Actualizar producto por ID",
            description = "Actualiza la información de un producto existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto actualizado"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateItem(@PathVariable Long id, @RequestBody Inventory itemDetails) {
        Inventory updatedItem = inventoryService.updateItem(id, itemDetails);
        return updatedItem != null ? ResponseEntity.ok(updatedItem) : ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Eliminar producto por ID",
            description = "Elimina un ítem del inventario según su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
