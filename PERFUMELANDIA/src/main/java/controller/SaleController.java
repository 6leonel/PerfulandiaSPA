package com.perfulandia.controller;

import com.perfulandia.model.Sale;
import com.perfulandia.service.SaleService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Operation(summary = "Listar ventas", description = "Devuelve todas las ventas registradas.")
    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @Operation(summary = "Obtener venta por ID", description = "Devuelve los datos de una venta específica.")
    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable Long id) {
        return saleService.getSaleById(id);
    }

    @Operation(summary = "Crear venta", description = "Registra una nueva venta.")
    @PostMapping
    public Sale createSale(@RequestBody Sale sale) {
        return saleService.createSale(sale);
    }

    @Operation(summary = "Eliminar venta", description = "Elimina una venta por ID.")
    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
    }
}
