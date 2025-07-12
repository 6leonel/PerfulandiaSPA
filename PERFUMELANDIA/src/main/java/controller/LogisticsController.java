package com.perfulandia.controller;

import com.perfulandia.model.Logistics;
import com.perfulandia.service.LogisticsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/logistics")
public class LogisticsController {

    @Autowired
    private LogisticsService logisticsService;

    @Operation(summary = "Listar envíos", description = "Devuelve todos los envíos registrados.")
    @GetMapping
    public List<Logistics> getAllShipments() {
        return logisticsService.getAllShipments();
    }

    @Operation(summary = "Obtener envío por ID", description = "Devuelve los detalles de un envío específico.")
    @GetMapping("/{id}")
    public ResponseEntity<Logistics> getShipmentById(@PathVariable Long id) {
        Logistics logistics = logisticsService.getShipmentById(id);
        return logistics != null ? ResponseEntity.ok(logistics) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Registrar envío", description = "Crea un nuevo envío asociado a una venta.")
    @PostMapping("/create/{saleId}")
    public Logistics createShipment(@PathVariable Long saleId) {
        return logisticsService.createShipment(saleId);
    }

    @Operation(summary = "Actualizar estado del envío", description = "Actualiza el estado logístico de un envío existente.")
    @PutMapping("/{id}/status")
    public ResponseEntity<Logistics> updateShipmentStatus(@PathVariable Long id, @RequestParam String status) {
        Logistics updatedLogistics = logisticsService.updateShipmentStatus(id, status);
        return updatedLogistics != null ? ResponseEntity.ok(updatedLogistics) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar envío", description = "Elimina un envío del sistema.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        logisticsService.deleteShipment(id);
        return ResponseEntity.noContent().build();
    }
}
