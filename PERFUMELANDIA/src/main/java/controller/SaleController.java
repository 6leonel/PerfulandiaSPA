package controller;

import model.Sale;
import service.SaleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

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

    @Operation(summary = "Obtener venta por ID", description = "Devuelve una venta específica por su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        Sale sale = saleService.getSaleById(id);
        return sale != null ? ResponseEntity.ok(sale) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Registrar venta", description = "Registra una nueva venta en el sistema.")
    @PostMapping
    public Sale createSale(@RequestBody Sale sale) {
        return saleService.createSale(sale);
    }

    @Operation(summary = "Eliminar venta", description = "Elimina una venta por su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
