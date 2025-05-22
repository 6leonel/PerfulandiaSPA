package controller;

import model.Sale;
import service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public class SaleController {

    private SaleService saleService;

    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    public ResponseEntity<Sale> getSaleById(Long id) {
        Sale sale = saleService.getSaleById(id);
        if (sale != null) {
            return ResponseEntity.ok(sale);
        }
        return ResponseEntity.notFound().build();
    }

    public Sale createSale(Sale sale) {
        return saleService.createSale(sale);
    }

    public ResponseEntity<Void> deleteSale(Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
